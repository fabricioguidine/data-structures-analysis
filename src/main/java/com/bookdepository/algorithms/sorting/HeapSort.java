package com.bookdepository.algorithms.sorting;

import com.bookdepository.model.Record;
import com.bookdepository.io.PerformanceResult;

/**
 * HeapSort algorithm implementation with performance tracking.
 * Sorts records by bestseller rank.
 */
public class HeapSort {
    private int comparisons;
    private int swaps;

    /**
     * Default constructor.
     */
    public HeapSort() {
        this.comparisons = 0;
        this.swaps = 0;
    }

    /**
     * Sorts an array of records using HeapSort algorithm.
     *
     * @param records Array of records to sort
     * @return Performance result with comparisons, swaps, and execution time
     */
    public PerformanceResult sort(Record[] records) {
        comparisons = 0;
        swaps = 0;
        long startTime = System.currentTimeMillis();

        if (records != null && records.length > 0) {
            int n = records.length;

            // Build max heap (rearrange array)
            for (int i = n / 2 - 1; i >= 0; i--) {
                heapify(records, n, i);
            }

            // Extract elements from heap one by one
            for (int i = n - 1; i > 0; i--) {
                // Move current root to end
                swap(records, 0, i);

                // Call heapify on the reduced heap
                heapify(records, i, 0);
            }
        }

        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;

        return new PerformanceResult(comparisons, swaps, executionTime);
    }

    /**
     * Heapifies a subtree rooted at index i.
     *
     * @param records Array of records
     * @param n       Size of heap
     * @param i       Root index of subtree
     */
    private void heapify(Record[] records, int n, int i) {
        int largest = i; // Initialize largest as root
        int left = 2 * i + 1; // Left child
        int right = 2 * i + 2; // Right child

        // Compare with left child
        if (left < n) {
            comparisons++;
            if (shouldSwap(records[left], records[largest])) {
                largest = left;
            }
        }

        // Compare with right child
        if (right < n) {
            comparisons++;
            if (shouldSwap(records[right], records[largest])) {
                largest = right;
            }
        }

        // If largest is not root, swap and continue heapifying
        if (largest != i) {
            swap(records, i, largest);
            heapify(records, n, largest);
        }
    }

    /**
     * Determines if two records should be swapped based on bestseller rank.
     *
     * @param a First record
     * @param b Second record
     * @return true if a should come before b (in max heap order)
     */
    private boolean shouldSwap(Record a, Record b) {
        int rankA = a.getBestsellersRank();
        int rankB = b.getBestsellersRank();

        // Handle zero ranks (put them at the end)
        if (rankA == 0 && rankB == 0) {
            return false;
        }
        if (rankA == 0) {
            return false; // A goes after B
        }
        if (rankB == 0) {
            return true; // A goes before B
        }

        return rankA < rankB; // Lower rank is better (max heap)
    }

    /**
     * Swaps two elements in the array.
     *
     * @param records Array of records
     * @param i       First index
     * @param j       Second index
     */
    private void swap(Record[] records, int i, int j) {
        if (i != j) {
            Record temp = records[i];
            records[i] = records[j];
            records[j] = temp;
            swaps++;
        }
    }

    /**
     * Gets the current comparison count.
     *
     * @return Number of comparisons
     */
    public int getComparisons() {
        return comparisons;
    }

    /**
     * Gets the current swap count.
     *
     * @return Number of swaps
     */
    public int getSwaps() {
        return swaps;
    }
}
