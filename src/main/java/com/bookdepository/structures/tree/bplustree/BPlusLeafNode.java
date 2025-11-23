package com.bookdepository.structures.tree.bplustree;

import com.bookdepository.model.Record;

/**
 * Leaf node in B+ Tree.
 * Stores keys and records.
 */
public class BPlusLeafNode extends BPlusNode {
    private String[] keys;
    private Record[] records;
    private BPlusLeafNode next; // Pointer to next leaf for sequential access

    /**
     * Constructor.
     *
     * @param degree Minimum degree
     */
    public BPlusLeafNode(int degree) {
        super(degree);
        this.keys = new String[getMaxKeys() + 1];
        this.records = new Record[getMaxKeys() + 1];
        this.next = null;
    }

    @Override
    public String getKey(int index) {
        if (index >= 0 && index < keyCount) {
            return keys[index];
        }
        return null;
    }

    /**
     * Gets record at index.
     *
     * @param index Index
     * @return Record
     */
    public Record getRecord(int index) {
        if (index >= 0 && index < keyCount) {
            return records[index];
        }
        return null;
    }

    /**
     * Inserts key-value pair into leaf node.
     *
     * @param key Key
     * @param record Record
     * @return true if inserted, false if node is full
     */
    public boolean insert(String key, Record record) {
        if (isFull()) {
            return false;
        }

        // Find insertion position
        int pos = 0;
        while (pos < keyCount && keys[pos].compareTo(key) < 0) {
            pos++;
        }

        // Shift elements to the right
        for (int i = keyCount; i > pos; i--) {
            keys[i] = keys[i - 1];
            records[i] = records[i - 1];
        }

        keys[pos] = key;
        records[pos] = record;
        keyCount++;
        return true;
    }

    @Override
    public Record search(String key) {
        // Binary search in leaf node
        int left = 0;
        int right = keyCount - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            int cmp = keys[mid].compareTo(key);
            if (cmp == 0) {
                return records[mid];
            } else if (cmp < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return null;
    }

    /**
     * Gets the next leaf node.
     *
     * @return Next leaf node
     */
    public BPlusLeafNode getNext() {
        return next;
    }

    /**
     * Sets the next leaf node.
     *
     * @param next Next leaf node
     */
    public void setNext(BPlusLeafNode next) {
        this.next = next;
    }

    /**
     * Splits the leaf node.
     *
     * @return New leaf node with half the keys
     */
    public BPlusLeafNode split() {
        BPlusLeafNode newLeaf = new BPlusLeafNode(degree);
        int mid = keyCount / 2;

        // Move second half to new leaf
        for (int i = mid; i < keyCount; i++) {
            newLeaf.insert(keys[i], records[i]);
        }

        keyCount = mid;
        newLeaf.setNext(this.next);
        this.setNext(newLeaf);

        return newLeaf;
    }

    /**
     * Gets the minimum key.
     *
     * @return Minimum key
     */
    public String getMinKey() {
        return keyCount > 0 ? keys[0] : null;
    }
}

