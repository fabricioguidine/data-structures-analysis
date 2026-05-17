package com.bookdepository.structures.bst;

import java.util.ArrayList;
import java.util.List;

/**
 * Recursive binary search tree keyed by a {@link Comparable} value.
 *
 * @param <T> element type
 */
public class BinarySearchTree<T extends Comparable<T>> {

    private static final class Node<T> {
        T value;
        Node<T> left;
        Node<T> right;

        Node(T value) {
            this.value = value;
        }
    }

    private Node<T> root;
    private int size;

    public BinarySearchTree() {
        this.root = null;
        this.size = 0;
    }

    /**
     * Inserts {@code value} unless an equal element already exists.
     *
     * @param value value to insert, must be non-null
     * @return true if the value was inserted
     */
    public boolean insert(T value) {
        if (value == null) {
            throw new IllegalArgumentException("value must not be null");
        }
        int oldSize = size;
        root = insertRecursive(root, value);
        return size > oldSize;
    }

    private Node<T> insertRecursive(Node<T> node, T value) {
        if (node == null) {
            size++;
            return new Node<>(value);
        }
        int cmp = value.compareTo(node.value);
        if (cmp < 0) {
            node.left = insertRecursive(node.left, value);
        } else if (cmp > 0) {
            node.right = insertRecursive(node.right, value);
        }
        return node;
    }

    /**
     * @param value value to search
     * @return true if present
     */
    public boolean contains(T value) {
        Node<T> current = root;
        while (current != null) {
            int cmp = value.compareTo(current.value);
            if (cmp == 0) {
                return true;
            }
            current = cmp < 0 ? current.left : current.right;
        }
        return false;
    }

    /**
     * In-order traversal — returns values in ascending order.
     *
     * @return ordered list of values
     */
    public List<T> inOrder() {
        List<T> values = new ArrayList<>(size);
        inOrderRecursive(root, values);
        return values;
    }

    private void inOrderRecursive(Node<T> node, List<T> values) {
        if (node == null) {
            return;
        }
        inOrderRecursive(node.left, values);
        values.add(node.value);
        inOrderRecursive(node.right, values);
    }

    /**
     * Returns the height of the tree (single-node tree has height 0).
     *
     * @return tree height, or -1 if empty
     */
    public int height() {
        return heightRecursive(root);
    }

    private int heightRecursive(Node<T> node) {
        if (node == null) {
            return -1;
        }
        return 1 + Math.max(heightRecursive(node.left), heightRecursive(node.right));
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
