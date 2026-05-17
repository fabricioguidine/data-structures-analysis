package com.bookdepository.io;

/**
 * Immutable value object holding metrics from a single algorithm run.
 *
 * <p>Tracks number of comparisons, number of swaps and elapsed execution time
 * in milliseconds.
 */
public class PerformanceResult {

    private final int comparisons;
    private final int swaps;
    private final long executionTime;

    public PerformanceResult(int comparisons, int swaps, long executionTime) {
        this.comparisons = comparisons;
        this.swaps = swaps;
        this.executionTime = executionTime;
    }

    public int getComparisons() {
        return comparisons;
    }

    public int getSwaps() {
        return swaps;
    }

    public long getExecutionTime() {
        return executionTime;
    }

    @Override
    public String toString() {
        return "PerformanceResult{comparisons=" + comparisons
                + ", swaps=" + swaps
                + ", executionTimeMs=" + executionTime + "}";
    }
}
