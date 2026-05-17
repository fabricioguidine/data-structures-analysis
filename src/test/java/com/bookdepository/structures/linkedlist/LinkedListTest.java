package com.bookdepository.structures.linkedlist;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Correctness + micro-benchmark tests for {@link LinkedList}.
 */
@DisplayName("LinkedList Tests")
class LinkedListTest {

    @Test
    @DisplayName("New list is empty")
    void newListIsEmpty() {
        LinkedList<Integer> list = new LinkedList<>();
        assertThat(list.isEmpty()).isTrue();
        assertThat(list.size()).isZero();
    }

    @Test
    @DisplayName("Append maintains insertion order")
    void appendMaintainsOrder() {
        LinkedList<String> list = new LinkedList<>();
        list.append("a");
        list.append("b");
        list.append("c");

        assertThat(list.size()).isEqualTo(3);
        assertThat(list.get(0)).isEqualTo("a");
        assertThat(list.get(1)).isEqualTo("b");
        assertThat(list.get(2)).isEqualTo("c");
    }

    @Test
    @DisplayName("Prepend inserts at the head")
    void prependInsertsAtHead() {
        LinkedList<Integer> list = new LinkedList<>();
        list.append(2);
        list.prepend(1);
        list.prepend(0);

        assertThat(list.get(0)).isEqualTo(0);
        assertThat(list.get(1)).isEqualTo(1);
        assertThat(list.get(2)).isEqualTo(2);
    }

    @Test
    @DisplayName("indexOf finds existing values, returns -1 otherwise")
    void indexOfBehaviour() {
        LinkedList<String> list = new LinkedList<>();
        list.append("alpha");
        list.append("beta");
        list.append("gamma");

        assertThat(list.indexOf("alpha")).isZero();
        assertThat(list.indexOf("beta")).isEqualTo(1);
        assertThat(list.indexOf("gamma")).isEqualTo(2);
        assertThat(list.indexOf("delta")).isEqualTo(-1);
    }

    @Test
    @DisplayName("Remove from middle preserves remaining order")
    void removeFromMiddle() {
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < 5; i++) {
            list.append(i);
        }

        boolean removed = list.remove(2);

        assertThat(removed).isTrue();
        assertThat(list.size()).isEqualTo(4);
        assertThat(list.indexOf(2)).isEqualTo(-1);
        assertThat(list.get(0)).isEqualTo(0);
        assertThat(list.get(1)).isEqualTo(1);
        assertThat(list.get(2)).isEqualTo(3);
        assertThat(list.get(3)).isEqualTo(4);
    }

    @Test
    @DisplayName("Remove of absent value returns false")
    void removeAbsentValue() {
        LinkedList<Integer> list = new LinkedList<>();
        list.append(1);
        list.append(2);

        assertThat(list.remove(99)).isFalse();
        assertThat(list.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("Remove head and tail keeps invariants")
    void removeHeadAndTail() {
        LinkedList<Integer> list = new LinkedList<>();
        list.append(1);
        list.append(2);
        list.append(3);

        assertThat(list.remove(1)).isTrue();
        assertThat(list.get(0)).isEqualTo(2);
        assertThat(list.remove(3)).isTrue();
        assertThat(list.size()).isEqualTo(1);
        assertThat(list.get(0)).isEqualTo(2);
    }

    @Test
    @DisplayName("get with out-of-bounds index throws")
    void getOutOfBoundsThrows() {
        LinkedList<Integer> list = new LinkedList<>();
        list.append(1);

        assertThatThrownBy(() -> list.get(5))
                .isInstanceOf(NoSuchElementException.class);
    }

    @ParameterizedTest
    @ValueSource(ints = {10, 100, 1_000, 10_000})
    @DisplayName("Bulk append/lookup scales linearly enough for typical sizes")
    void bulkOperations(int size) {
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            list.append(i);
        }
        assertThat(list.size()).isEqualTo(size);
        assertThat(list.indexOf(size - 1)).isEqualTo(size - 1);
        assertThat(list.indexOf(size + 1)).isEqualTo(-1);
    }
}
