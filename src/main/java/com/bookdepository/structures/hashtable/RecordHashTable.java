package com.bookdepository.structures.hashtable;

import com.bookdepository.model.Record;

/**
 * Hash table implementation for records using open addressing with double hashing.
 */
public class RecordHashTable {
    private Record[] table;
    private int size;
    private int capacity;
    private static final double LOAD_FACTOR_THRESHOLD = 0.75;

    /**
     * Constructor with initial capacity.
     *
     * @param initialCapacity Initial capacity of the hash table
     */
    public RecordHashTable(int initialCapacity) {
        this.capacity = initialCapacity;
        this.table = new Record[capacity];
        this.size = 0;
    }

    /**
     * Default constructor with default capacity.
     */
    public RecordHashTable() {
        this(16);
    }

    /**
     * Primary hash function.
     *
     * @param key Key to hash
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
     * @param key Key to hash
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
     * Inserts a record into the hash table.
     *
     * @param record Record to insert
     * @return true if inserted successfully, false otherwise
     */
    public boolean insert(Record record) {
        if (record == null || record.getId() == null) {
            return false;
        }

        // Check load factor and resize if necessary
        if ((double) size / capacity >= LOAD_FACTOR_THRESHOLD) {
            resize();
        }

        String key = record.getId();
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
            size++;
        }
        table[index] = record;
        return true;
    }

    /**
     * Searches for a record by ID.
     *
     * @param id Record ID to search for
     * @return Record if found, null otherwise
     */
    public Record search(String id) {
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
     * Resizes the hash table to double capacity.
     */
    private void resize() {
        Record[] oldTable = table;
        int oldCapacity = capacity;
        capacity *= 2;
        table = new Record[capacity];
        size = 0;

        // Rehash all elements
        for (Record record : oldTable) {
            if (record != null) {
                insert(record);
            }
        }
    }

    /**
     * Gets the current size of the hash table.
     *
     * @return Number of records in the table
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

    /**
     * Gets all records from the hash table.
     *
     * @return Array of all records
     */
    public Record[] getAllRecords() {
        java.util.List<Record> records = new java.util.ArrayList<>();
        for (Record record : table) {
            if (record != null) {
                records.add(record);
            }
        }
        return records.toArray(new Record[0]);
    }
}
