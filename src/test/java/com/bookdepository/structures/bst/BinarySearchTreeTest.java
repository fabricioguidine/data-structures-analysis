package com.bookdepository.structures.bst;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Correctness tests for {@link BinarySearchTree}.
 */
@DisplayName("BinarySearchTree Tests")
class BinarySearchTreeTest {

    @Test
    @DisplayName("Newly created tree is empty")
    void newTreeIsEmpty() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        assertThat(tree.isEmpty()).isTrue();
        assertThat(tree.size()).isZero();
        assertThat(tree.height()).isEqualTo(-1);
    }

    @Test
    @DisplayName("Insert deduplicates equal keys")
    void insertDeduplicates() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();

        assertThat(tree.insert(5)).isTrue();
        assertThat(tree.insert(5)).isFalse();
        assertThat(tree.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("Search finds inserted values and rejects others")
    void searchFindsValues() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        for (int value : new int[] {8, 3, 10, 1, 6, 14, 4, 7, 13}) {
            tree.insert(value);
        }

        assertThat(tree.contains(7)).isTrue();
        assertThat(tree.contains(8)).isTrue();
        assertThat(tree.contains(14)).isTrue();
        assertThat(tree.contains(99)).isFalse();
    }

    @Test
    @DisplayName("In-order traversal returns sorted sequence")
    void inOrderIsSorted() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        List<Integer> input = Arrays.asList(50, 30, 70, 20, 40, 60, 80, 10);
        for (Integer value : input) {
            tree.insert(value);
        }

        List<Integer> sorted = new java.util.ArrayList<>(input);
        Collections.sort(sorted);

        assertThat(tree.inOrder()).containsExactlyElementsOf(sorted);
    }

    @Test
    @DisplayName("Null inserts are rejected")
    void nullInsertRejected() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        assertThatThrownBy(() -> tree.insert(null))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Degenerate insert order yields linear height")
    void degenerateInsertOrder() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        for (int i = 1; i <= 8; i++) {
            tree.insert(i);
        }

        // Inserting in ascending order produces a right-skewed tree of height n-1.
        assertThat(tree.height()).isEqualTo(7);
        assertThat(tree.size()).isEqualTo(8);
    }

    @Test
    @DisplayName("Balanced insert order yields logarithmic height")
    void balancedInsertOrder() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        int[] balanced = {4, 2, 6, 1, 3, 5, 7};
        for (int value : balanced) {
            tree.insert(value);
        }

        assertThat(tree.height()).isEqualTo(2);
        assertThat(tree.size()).isEqualTo(balanced.length);
    }
}
