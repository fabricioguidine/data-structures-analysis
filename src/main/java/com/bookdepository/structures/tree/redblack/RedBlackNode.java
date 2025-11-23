package com.bookdepository.structures.tree.redblack;

import com.bookdepository.model.Record;

/**
 * Node class for Red-Black Tree.
 */
public class RedBlackNode {
    public static final boolean RED = true;
    public static final boolean BLACK = false;

    public Record record;
    public String key; // Using record ID as key
    public boolean color;
    public RedBlackNode left;
    public RedBlackNode right;
    public RedBlackNode parent;

    /**
     * Constructor.
     *
     * @param key Record ID to use as key
     * @param record Record data
     * @param color Node color (RED or BLACK)
     */
    public RedBlackNode(String key, Record record, boolean color) {
        this.key = key;
        this.record = record;
        this.color = color;
        this.left = null;
        this.right = null;
        this.parent = null;
    }

    /**
     * Check if node is red.
     *
     * @return true if red, false otherwise
     */
    public boolean isRed() {
        return color == RED;
    }

    /**
     * Check if node is black.
     *
     * @return true if black, false otherwise
     */
    public boolean isBlack() {
        return color == BLACK;
    }
}

