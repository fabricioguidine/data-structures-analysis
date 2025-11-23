package com.bookdepository.structures.tree.bplustree;

import com.bookdepository.model.Record;

/**
 * Base class for B+ Tree nodes (internal and leaf).
 */
public abstract class BPlusNode {
    protected int degree; // Minimum degree
    protected int keyCount;
    protected BPlusNode parent;

    /**
     * Constructor.
     *
     * @param degree Minimum degree
     */
    public BPlusNode(int degree) {
        this.degree = degree;
        this.keyCount = 0;
        this.parent = null;
    }

    /**
     * Gets the minimum number of keys.
     *
     * @return Minimum keys
     */
    public int getMinKeys() {
        return degree;
    }

    /**
     * Gets the maximum number of keys.
     *
     * @return Maximum keys
     */
    public int getMaxKeys() {
        return 2 * degree;
    }

    /**
     * Checks if node is full.
     *
     * @return true if full, false otherwise
     */
    public boolean isFull() {
        return keyCount >= getMaxKeys();
    }

    /**
     * Checks if node is underflow.
     *
     * @return true if underflow, false otherwise
     */
    public boolean isUnderflow() {
        return keyCount < getMinKeys();
    }

    /**
     * Gets key count.
     *
     * @return Number of keys
     */
    public int getKeyCount() {
        return keyCount;
    }

    /**
     * Abstract method to get key at index.
     *
     * @param index Index
     * @return Key
     */
    public abstract String getKey(int index);

    /**
     * Abstract method to search for a key.
     *
     * @param key Key to search for
     * @return Record if found (leaf node), null otherwise
     */
    public abstract Record search(String key);
}

