package com.bookdepository.structures.hashtable;

import com.bookdepository.model.Author;
import java.util.ArrayList;
import java.util.List;

/**
 * Hash table implementation for authors with frequency tracking.
 * Uses open addressing with double hashing.
 */
public class AuthorHashTable {
    private Author[] table;
    private int size;
    private int capacity;
    private static final double LOAD_FACTOR_THRESHOLD = 0.75;

    /**
     * Constructor with initial capacity.
     *
     * @param initialCapacity Initial capacity of the hash table
     */
    public AuthorHashTable(int initialCapacity) {
        this.capacity = initialCapacity;
        this.table = new Author[capacity];
        this.size = 0;
    }

    /**
     * Default constructor with default capacity.
     */
    public AuthorHashTable() {
        this(16);
    }

    /**
     * Primary hash function.
     *
     * @param key Author ID to hash
     * @return Hash value
     */
    private int hash1(String key) {
        int hash = 0;
        for (char c : key.toCharArray()) {
            hash = (hash * 31 + c) % capacity;
        }
        return Math.abs(hash);
    }

    /**
     * Secondary hash function for double hashing.
     *
     * @param key Author ID to hash
     * @return Hash value
     */
    private int hash2(String key) {
        int hash = 0;
        for (char c : key.toCharArray()) {
            hash = (hash * 37 + c) % capacity;
        }
        // Must be odd and coprime with capacity
        return Math.abs(hash) | 1;
    }

    /**
     * Inserts or updates an author in the hash table.
     * If the author already exists, increments their frequency.
     *
     * @param author Author to insert or update
     * @return true if inserted successfully, false otherwise
     */
    public boolean insertOrIncrement(Author author) {
        if (author == null || author.getId() == null) {
            return false;
        }

        // Check load factor and resize if necessary
        if ((double) size / capacity >= LOAD_FACTOR_THRESHOLD) {
            resize();
        }

        String key = author.getId();
        int h1 = hash1(key);
        int h2 = hash2(key);
        int index = h1;
        int attempt = 0;

        // Open addressing with double hashing
        while (table[index] != null && !table[index].getId().equals(key)) {
            attempt++;
            if (attempt >= capacity) {
                // Table is full
                resize();
                h1 = hash1(key);
                h2 = hash2(key);
                index = h1;
                attempt = 0;
            } else {
                index = (h1 + attempt * h2) % capacity;
            }
        }

        if (table[index] == null) {
            // New author
            table[index] = new Author(author.getId(), author.getName());
            size++;
        } else {
            // Existing author - increment frequency
            table[index].incrementFrequency();
        }
        return true;
    }

    /**
     * Searches for an author by ID.
     *
     * @param id Author ID to search for
     * @return Author if found, null otherwise
     */
    public Author search(String id) {
        if (id == null) {
            return null;
        }

        int h1 = hash1(id);
        int h2 = hash2(id);
        int index = h1;
        int attempt = 0;

        while (table[index] != null && attempt < capacity) {
            if (table[index].getId().equals(id)) {
                return table[index];
            }
            attempt++;
            index = (h1 + attempt * h2) % capacity;
        }

        return null;
    }

    /**
     * Gets all authors from the hash table.
     *
     * @return List of all authors
     */
    public List<Author> getAllAuthors() {
        List<Author> authors = new ArrayList<>();
        for (Author author : table) {
            if (author != null) {
                authors.add(author);
            }
        }
        return authors;
    }

    /**
     * Resizes the hash table to double capacity.
     */
    private void resize() {
        Author[] oldTable = table;
        int oldCapacity = capacity;
        capacity *= 2;
        table = new Author[capacity];
        size = 0;

        // Rehash all elements
        for (Author author : oldTable) {
            if (author != null) {
                // Re-insert with current frequency
                for (int i = 0; i < author.getFrequency(); i++) {
                    if (i == 0) {
                        insertOrIncrement(new Author(author.getId(), author.getName(), 1));
                    } else {
                        Author existing = search(author.getId());
                        if (existing != null) {
                            existing.incrementFrequency();
                        }
                    }
                }
            }
        }
    }

    /**
     * Gets the current size of the hash table.
     *
     * @return Number of authors in the table
     */
    public int size() {
        return size;
    }

    /**
     * Gets the current capacity of the hash table.
     *
     * @return Capacity of the table
     */
    public int capacity() {
        return capacity;
    }
}
