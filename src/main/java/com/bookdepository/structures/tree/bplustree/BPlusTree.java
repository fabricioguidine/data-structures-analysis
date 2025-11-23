package com.bookdepository.structures.tree.bplustree;

import com.bookdepository.model.Record;

/**
 * B+ Tree implementation for records.
 * Balanced tree structure optimized for disk storage.
 */
public class BPlusTree {
    private BPlusNode root;
    private int degree;
    private int comparisons;
    private int insertions;

    /**
     * Constructor.
     *
     * @param degree Minimum degree of the tree
     */
    public BPlusTree(int degree) {
        this.degree = degree;
        this.root = new BPlusLeafNode(degree);
        this.comparisons = 0;
        this.insertions = 0;
    }

    /**
     * Resets performance counters.
     */
    public void resetCounters() {
        this.comparisons = 0;
        this.insertions = 0;
    }

    /**
     * Gets comparison count.
     *
     * @return Number of comparisons
     */
    public int getComparisons() {
        return comparisons;
    }

    /**
     * Gets insertion count.
     *
     * @return Number of insertions
     */
    public int getInsertions() {
        return insertions;
    }

    /**
     * Inserts a record into the tree.
     *
     * @param record Record to insert
     */
    public void insert(Record record) {
        if (record == null || record.getId() == null) {
            return;
        }

        String key = record.getId();
        BPlusLeafNode leaf = findLeaf(key);

        if (!leaf.insert(key, record)) {
            // Leaf is full, need to split
            BPlusLeafNode newLeaf = leaf.split();
            String newKey = newLeaf.getMinKey();

            // Insert into parent
            insertIntoParent(leaf, newKey, newLeaf);
            insertions++;
        } else {
            insertions++;
        }
    }

    /**
     * Finds the leaf node where key should be inserted or searched.
     *
     * @param key Key to find
     * @return Leaf node
     */
    private BPlusLeafNode findLeaf(String key) {
        BPlusNode node = root;

        while (!(node instanceof BPlusLeafNode)) {
            BPlusInternalNode internal = (BPlusInternalNode) node;
            int pos = 0;

            while (pos < internal.getKeyCount() && internal.getKey(pos).compareTo(key) <= 0) {
                pos++;
                comparisons++;
            }

            node = internal.getChild(pos);
        }

        return (BPlusLeafNode) node;
    }

    /**
     * Inserts a new key into the parent node after a split.
     *
     * @param leftChild Left child after split
     * @param key New key to insert
     * @param rightChild Right child after split
     */
    private void insertIntoParent(BPlusNode leftChild, String key, BPlusNode rightChild) {
        BPlusNode parent = leftChild.parent;

        if (parent == null) {
            // Create new root
            BPlusInternalNode newRoot = new BPlusInternalNode(degree);
            newRoot.setChild(0, leftChild);
            newRoot.insert(key, rightChild);
            root = newRoot;
            return;
        }

        BPlusInternalNode internal = (BPlusInternalNode) parent;
        int index = internal.insert(key, rightChild);

        if (index == -1) {
            // Parent is full, need to split
            BPlusInternalNode newInternal = internal.split();
            String newKey = internal.getKey(internal.getKeyCount());
            internal.setKeyCount(internal.getKeyCount() - 1);

            insertIntoParent(internal, newKey, newInternal);
        }
    }

    /**
     * Helper to set key count (for splitting).
     */
    private void setKeyCount(BPlusNode node, int count) {
        if (node instanceof BPlusInternalNode) {
            BPlusInternalNode internal = (BPlusInternalNode) node;
            // This would need a setter method
        }
    }

    /**
     * Searches for a record by key.
     *
     * @param key Key to search for
     * @return Record if found, null otherwise
     */
    public Record search(String key) {
        BPlusLeafNode leaf = findLeaf(key);
        Record result = leaf.search(key);
        if (result != null) {
            comparisons++;
        }
        return result;
    }

    /**
     * Gets the height of the tree.
     *
     * @return Tree height
     */
    public int getHeight() {
        return getHeight(root);
    }

    /**
     * Gets height of subtree rooted at node.
     *
     * @param node Root of subtree
     * @return Height of subtree
     */
    private int getHeight(BPlusNode node) {
        if (node instanceof BPlusLeafNode) {
            return 0;
        }

        BPlusInternalNode internal = (BPlusInternalNode) node;
        if (internal.getChild(0) != null) {
            return 1 + getHeight(internal.getChild(0));
        }
        return 0;
    }

    /**
     * Gets the root node.
     *
     * @return Root node
     */
    public BPlusNode getRoot() {
        return root;
    }
}

