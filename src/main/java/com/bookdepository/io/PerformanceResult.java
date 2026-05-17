package com.bookdepository.io;

/**
 * Immutable value object holding metrics from a single algorithm run.
 *
 * <p>Mirrors the three performance metrics defined in the LaTeX report
 * (Section "Performance Metrics"): number of comparisons, number of
 * swaps/copies and execution time in milliseconds. The input size that
 * produced the metrics is also tracked so the experiment output can be
 * reported per size as in {@code output/output.txt}.
 */
public class PerformanceResult {

    private final int size;
    private final int comparisons;
    private final int swaps;
    private final long executionTime;

    /**
     * Backwards-compatible constructor for callers that don't track input size.
     */
    public PerformanceResult(int comparisons, int swaps, long executionTime) {
        this(0, comparisons, swaps, executionTime);
    }

    public PerformanceResult(int size, int comparisons, int swaps, long executionTime) {
        this.size = size;
        this.comparisons = comparisons;
        this.swaps = swaps;
        this.executionTime = executionTime;
    }

    public int getSize() {
        return size;
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
        return "PerformanceResult{size=" + size
                + ", comparisons=" + comparisons
                + ", swaps=" + swaps
                + ", executionTimeMs=" + executionTime + "}";
    }
}
