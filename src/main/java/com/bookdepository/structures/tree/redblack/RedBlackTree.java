package com.bookdepository.structures.tree.redblack;

import com.bookdepository.model.Record;

/**
 * Red-Black Tree implementation for records.
 * Self-balancing binary search tree with O(log n) operations.
 */
public class RedBlackTree {
    private RedBlackNode root;
    private int comparisons;
    private int rotations;

    /**
     * Constructor.
     */
    public RedBlackTree() {
        this.root = null;
        this.comparisons = 0;
        this.rotations = 0;
    }

    /**
     * Resets performance counters.
     */
    public void resetCounters() {
        this.comparisons = 0;
        this.rotations = 0;
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
     * Gets rotation count.
     *
     * @return Number of rotations
     */
    public int getRotations() {
        return rotations;
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

        RedBlackNode node = new RedBlackNode(record.getId(), record, RedBlackNode.RED);
        root = insertNode(root, node);
        root.color = RedBlackNode.BLACK; // Root is always black
    }

    /**
     * Recursive insert helper.
     *
     * @param root Current root
     * @param node Node to insert
     * @return New root after insertion
     */
    private RedBlackNode insertNode(RedBlackNode root, RedBlackNode node) {
        if (root == null) {
            return node;
        }

        comparisons++;
        int cmp = node.key.compareTo(root.key);

        if (cmp < 0) {
            root.left = insertNode(root.left, node);
            root.left.parent = root;
        } else if (cmp > 0) {
            root.right = insertNode(root.right, node);
            root.right.parent = root;
        } else {
            // Duplicate key - update record
            root.record = node.record;
            return root;
        }

        // Fix violations
        if (isRed(root.right) && !isRed(root.left)) {
            root = rotateLeft(root);
            rotations++;
        }
        if (isRed(root.left) && isRed(root.left.left)) {
            root = rotateRight(root);
            rotations++;
        }
        if (isRed(root.left) && isRed(root.right)) {
            flipColors(root);
        }

        return root;
    }

    /**
     * Searches for a record by ID.
     *
     * @param key Record ID to search for
     * @return Record if found, null otherwise
     */
    public Record search(String key) {
        return searchNode(root, key);
    }

    /**
     * Recursive search helper.
     *
     * @param node Current node
     * @param key Key to search for
     * @return Record if found, null otherwise
     */
    private Record searchNode(RedBlackNode node, String key) {
        if (node == null) {
            return null;
        }

        comparisons++;
        int cmp = key.compareTo(node.key);

        if (cmp < 0) {
            return searchNode(node.left, key);
        } else if (cmp > 0) {
            return searchNode(node.right, key);
        } else {
            return node.record;
        }
    }

    /**
     * Performs left rotation.
     *
     * @param node Node to rotate
     * @return New root after rotation
     */
    private RedBlackNode rotateLeft(RedBlackNode node) {
        RedBlackNode rightChild = node.right;
        node.right = rightChild.left;
        if (rightChild.left != null) {
            rightChild.left.parent = node;
        }
        rightChild.parent = node.parent;
        rightChild.left = node;
        node.parent = rightChild;
        rightChild.color = node.color;
        node.color = RedBlackNode.RED;
        return rightChild;
    }

    /**
     * Performs right rotation.
     *
     * @param node Node to rotate
     * @return New root after rotation
     */
    private RedBlackNode rotateRight(RedBlackNode node) {
        RedBlackNode leftChild = node.left;
        node.left = leftChild.right;
        if (leftChild.right != null) {
            leftChild.right.parent = node;
        }
        leftChild.parent = node.parent;
        leftChild.right = node;
        node.parent = leftChild;
        leftChild.color = node.color;
        node.color = RedBlackNode.RED;
        return leftChild;
    }

    /**
     * Flips colors of node and its children.
     *
     * @param node Node to flip colors
     */
    private void flipColors(RedBlackNode node) {
        node.color = RedBlackNode.RED;
        if (node.left != null) {
            node.left.color = RedBlackNode.BLACK;
        }
        if (node.right != null) {
            node.right.color = RedBlackNode.BLACK;
        }
    }

    /**
     * Checks if node is red.
     *
     * @param node Node to check
     * @return true if red, false otherwise
     */
    private boolean isRed(RedBlackNode node) {
        return node != null && node.isRed();
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
    private int getHeight(RedBlackNode node) {
        if (node == null) {
            return -1;
        }
        return 1 + Math.max(getHeight(node.left), getHeight(node.right));
    }

    /**
     * Gets the root node.
     *
     * @return Root node
     */
    public RedBlackNode getRoot() {
        return root;
    }
}

