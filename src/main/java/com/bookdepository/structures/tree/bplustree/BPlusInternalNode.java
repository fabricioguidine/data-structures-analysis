package com.bookdepository.structures.tree.bplustree;

/**
 * Internal node in B+ Tree.
 * Stores keys and child pointers.
 */
public class BPlusInternalNode extends BPlusNode {
    private String[] keys;
    private BPlusNode[] children;

    /**
     * Constructor.
     *
     * @param degree Minimum degree
     */
    public BPlusInternalNode(int degree) {
        super(degree);
        this.keys = new String[getMaxKeys()];
        this.children = new BPlusNode[getMaxKeys() + 1];
    }

    @Override
    public String getKey(int index) {
        if (index >= 0 && index < keyCount) {
            return keys[index];
        }
        return null;
    }

    /**
     * Gets child at index.
     *
     * @param index Index
     * @return Child node
     */
    public BPlusNode getChild(int index) {
        if (index >= 0 && index <= keyCount) {
            return children[index];
        }
        return null;
    }

    /**
     * Sets child at index.
     *
     * @param index Index
     * @param child Child node
     */
    public void setChild(int index, BPlusNode child) {
        if (index >= 0 && index <= keyCount) {
            children[index] = child;
            if (child != null) {
                child.parent = this;
            }
        }
    }

    /**
     * Inserts key and child into internal node.
     *
     * @param key Key
     * @param child Child node
     * @return Index where key was inserted
     */
    public int insert(String key, BPlusNode child) {
        if (isFull()) {
            return -1;
        }

        // Find insertion position
        int pos = 0;
        while (pos < keyCount && keys[pos].compareTo(key) < 0) {
            pos++;
        }

        // Shift elements to the right
        for (int i = keyCount; i > pos; i--) {
            keys[i] = keys[i - 1];
            children[i + 1] = children[i];
        }

        keys[pos] = key;
        children[pos + 1] = child;
        if (child != null) {
            child.parent = this;
        }
        keyCount++;
        return pos;
    }

    @Override
    public Record search(String key) {
        // Find appropriate child
        int pos = 0;
        while (pos < keyCount && keys[pos].compareTo(key) <= 0) {
            pos++;
        }

        BPlusNode child = children[pos];
        return child != null ? child.search(key) : null;
    }

    /**
     * Splits the internal node.
     *
     * @return New internal node with half the keys
     */
    public BPlusInternalNode split() {
        BPlusInternalNode newInternal = new BPlusInternalNode(degree);
        int mid = keyCount / 2;

        // Move second half to new internal node
        for (int i = mid + 1; i < keyCount; i++) {
            newInternal.insert(keys[i], children[i + 1]);
        }

        String middleKey = keys[mid];
        keyCount = mid;
        newInternal.setChild(0, children[mid + 1]);

        return newInternal;
    }
}

