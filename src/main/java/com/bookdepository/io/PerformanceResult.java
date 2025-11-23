package com.bookdepository.io;

/**
 * Represents performance metrics for an algorithm execution.
 * Tracks comparisons, swaps/copies, and execution time.
 */
public class PerformanceResult {
    private int comparisons;
    private int swaps;
    private long executionTimeMs;

    /**
     * Constructor with all metrics.
     *
     * @param comparisons     Number of comparisons performed
     * @param swaps           Number of swaps/copies performed
     * @param executionTimeMs Execution time in milliseconds
     */
    public PerformanceResult(int comparisons, int swaps, long executionTimeMs) {
        this.comparisons = comparisons;
        this.swaps = swaps;
        this.executionTimeMs = executionTimeMs;
    }

    // Getters and Setters

    public int getComparisons() {
        return comparisons;
    }

    public void setComparisons(int comparisons) {
        this.comparisons = comparisons;
    }

    public int getSwaps() {
        return swaps;
    }

    public void setSwaps(int swaps) {
        this.swaps = swaps;
    }

    public long getExecutionTimeMs() {
        return executionTimeMs;
    }

    public void setExecutionTimeMs(long executionTimeMs) {
        this.executionTimeMs = executionTimeMs;
    }

    /**
     * Adds another result's metrics to this result.
     *
     * @param other The other result to add
     */
    public void add(PerformanceResult other) {
        this.comparisons += other.comparisons;
        this.swaps += other.swaps;
        this.executionTimeMs += other.executionTimeMs;
    }

    @Override
    public String toString() {
        return comparisons + " " + swaps + " " + executionTimeMs;
    }
}
