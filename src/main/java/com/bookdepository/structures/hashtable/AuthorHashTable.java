package com.bookdepository.structures.hashtable;

import com.bookdepository.model.Author;

import java.util.ArrayList;
import java.util.List;

/**
 * Open-addressing hash table specialised for counting {@link Author} frequencies.
 *
 * <p>Uses double hashing to resolve collisions and tracks load factor so the
 * Part&nbsp;II experiment can report basic performance metrics.
 */
public class AuthorHashTable {

    private static final double LOAD_FACTOR_THRESHOLD = 0.7;
    private static final int MIN_CAPACITY = 16;

    private Author[] table;
    private int size;
    private long collisions;

    /**
     * Creates a hash table sized to comfortably hold {@code expectedSize} entries.
     *
     * @param expectedSize hint for the expected number of unique authors
     */
    public AuthorHashTable(int expectedSize) {
        int capacity = Math.max(MIN_CAPACITY, nextPowerOfTwo((int) (expectedSize / LOAD_FACTOR_THRESHOLD) + 1));
        this.table = new Author[capacity];
        this.size = 0;
        this.collisions = 0;
    }

    /**
     * Inserts {@code author}, or increments the frequency of the existing entry
     * with the same id.
     *
     * @param author author to insert, must be non-null and have a non-empty id
     * @return the entry stored in the table after the operation
     */
    public Author insertOrIncrement(Author author) {
        if (author == null || author.getId() == null || author.getId().isEmpty()) {
            throw new IllegalArgumentException("author and author id are required");
        }
        if ((double) (size + 1) / table.length > LOAD_FACTOR_THRESHOLD) {
            resize();
        }
        int index = primaryHash(author.getId());
        int step = secondaryHash(author.getId());
        for (int probe = 0; probe < table.length; probe++) {
            int slot = (index + probe * step) % table.length;
            if (table[slot] == null) {
                Author entry = new Author(author.getId(), author.getName());
                entry.incrementFrequency();
                table[slot] = entry;
                size++;
                return entry;
            }
            if (table[slot].getId().equals(author.getId())) {
                table[slot].incrementFrequency();
                return table[slot];
            }
            collisions++;
        }
        throw new IllegalStateException("hash table is unexpectedly full");
    }

    /**
     * Looks up an author by id.
     *
     * @param id id to search for
     * @return the stored author or {@code null} if absent
     */
    public Author find(String id) {
        if (id == null || id.isEmpty()) {
            return null;
        }
        int index = primaryHash(id);
        int step = secondaryHash(id);
        for (int probe = 0; probe < table.length; probe++) {
            int slot = (index + probe * step) % table.length;
            if (table[slot] == null) {
                return null;
            }
            if (table[slot].getId().equals(id)) {
                return table[slot];
            }
        }
        return null;
    }

    /**
     * @return all stored authors, in arbitrary order
     */
    public List<Author> getAllAuthors() {
        List<Author> authors = new ArrayList<>(size);
        for (Author entry : table) {
            if (entry != null) {
                authors.add(entry);
            }
        }
        return authors;
    }

    public int size() {
        return size;
    }

    public int capacity() {
        return table.length;
    }

    public long getCollisions() {
        return collisions;
    }

    public double loadFactor() {
        return (double) size / table.length;
    }

    private int primaryHash(String key) {
        return Math.floorMod(key.hashCode(), table.length);
    }

    private int secondaryHash(String key) {
        int hash = key.hashCode();
        return 1 + Math.floorMod(hash, table.length - 1);
    }

    private void resize() {
        Author[] oldTable = table;
        table = new Author[oldTable.length * 2];
        size = 0;
        collisions = 0;
        for (Author entry : oldTable) {
            if (entry == null) {
                continue;
            }
            int index = primaryHash(entry.getId());
            int step = secondaryHash(entry.getId());
            for (int probe = 0; probe < table.length; probe++) {
                int slot = (index + probe * step) % table.length;
                if (table[slot] == null) {
                    table[slot] = entry;
                    size++;
                    break;
                }
                collisions++;
            }
        }
    }

    private static int nextPowerOfTwo(int n) {
        if (n <= 1) {
            return 1;
        }
        int power = 1;
        while (power < n) {
            power <<= 1;
        }
        return power;
    }
}
