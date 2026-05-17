package com.bookdepository.structures.linkedlist;

import java.util.NoSuchElementException;

/**
 * Singly-linked list with constant-time prepend and append.
 *
 * @param <T> element type
 */
public class LinkedList<T> {

    /** Internal node container. */
    private static final class Node<T> {
        T value;
        Node<T> next;

        Node(T value) {
            this.value = value;
        }
    }

    private Node<T> head;
    private Node<T> tail;
    private int size;

    public LinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    /**
     * Appends {@code value} to the end of the list in O(1).
     *
     * @param value element to append
     */
    public void append(T value) {
        Node<T> node = new Node<>(value);
        if (head == null) {
            head = node;
            tail = node;
        } else {
            tail.next = node;
            tail = node;
        }
        size++;
    }

    /**
     * Prepends {@code value} to the head of the list in O(1).
     *
     * @param value element to insert
     */
    public void prepend(T value) {
        Node<T> node = new Node<>(value);
        node.next = head;
        head = node;
        if (tail == null) {
            tail = node;
        }
        size++;
    }

    /**
     * Returns the first occurrence of {@code value}, or {@code -1} if absent.
     *
     * @param value value to search for
     * @return zero-based index, or -1
     */
    public int indexOf(T value) {
        int index = 0;
        for (Node<T> current = head; current != null; current = current.next) {
            if (current.value == null ? value == null : current.value.equals(value)) {
                return index;
            }
            index++;
        }
        return -1;
    }

    /**
     * Removes the first occurrence of {@code value}.
     *
     * @param value value to remove
     * @return true if an element was removed
     */
    public boolean remove(T value) {
        Node<T> previous = null;
        Node<T> current = head;
        while (current != null) {
            boolean matches = current.value == null ? value == null : current.value.equals(value);
            if (matches) {
                if (previous == null) {
                    head = current.next;
                } else {
                    previous.next = current.next;
                }
                if (current == tail) {
                    tail = previous;
                }
                size--;
                return true;
            }
            previous = current;
            current = current.next;
        }
        return false;
    }

    /**
     * Returns the element at {@code index}.
     *
     * @param index zero-based index
     * @return the element
     * @throws NoSuchElementException if index is out of bounds
     */
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new NoSuchElementException("index " + index + " out of bounds for size " + size);
        }
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.value;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
