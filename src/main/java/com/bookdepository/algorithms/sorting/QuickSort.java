package com.bookdepository.algorithms.sorting;

import com.bookdepository.model.Record;
import com.bookdepository.io.PerformanceResult;

/**
 * QuickSort algorithm implementation with performance tracking.
 * Sorts records by bestseller rank.
 */
public class QuickSort {
    private int comparisons;
    private int swaps;

    /**
     * Default constructor.
     */
    public QuickSort() {
        this.comparisons = 0;
        this.swaps = 0;
    }

    /**
     * Sorts an array of records using QuickSort algorithm.
     *
     * @param records Array of records to sort
     * @return Performance result with comparisons, swaps, and execution time
     */
    public PerformanceResult sort(Record[] records) {
        comparisons = 0;
        swaps = 0;
        long startTime = System.currentTimeMillis();

        if (records != null && records.length > 0) {
            quickSort(records, 0, records.length - 1);
        }

        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;

        return new PerformanceResult(comparisons, swaps, executionTime);
    }

    /**
     * Recursive QuickSort implementation.
     *
     * @param records Array of records
     * @param low     Starting index
     * @param high    Ending index
     */
    private void quickSort(Record[] records, int low, int high) {
        if (low < high) {
            int pi = partition(records, low, high);
            quickSort(records, low, pi - 1);
            quickSort(records, pi + 1, high);
        }
    }

    /**
     * Partitions the array around a pivot element.
     *
     * @param records Array of records
     * @param low     Starting index
     * @param high    Ending index
     * @return Index of the pivot after partitioning
     */
    private int partition(Record[] records, int low, int high) {
        Record pivot = records[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            comparisons++;
            // Compare by bestseller rank (lower rank is better)
            if (shouldSwap(records[j], pivot)) {
                i++;
                swap(records, i, j);
            }
        }
        swap(records, i + 1, high);
        return i + 1;
    }

    /**
     * Determines if two records should be swapped based on bestseller rank.
     *
     * @param a First record
     * @param b Second record (pivot)
     * @return true if a should come before b
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

        return rankA < rankB; // Lower rank is better
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
