package com.bookdepository.performance;

import com.bookdepository.algorithms.sorting.QuickSort;
import com.bookdepository.algorithms.sorting.HeapSort;
import com.bookdepository.model.Record;
import com.bookdepository.io.PerformanceResult;
import com.bookdepository.test.TestUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Performance tests for sorting algorithms.
 * These tests measure execution time and compare algorithm performance.
 */
@DisplayName("Performance Tests")
class PerformanceTest {

    @Test
    @DisplayName("QuickSort performance benchmark")
    void quickSortPerformanceTest() {
        int[] sizes = {100, 1000, 10000, 100000};
        
        System.out.println("\n=== QuickSort Performance Benchmark ===");
        
        for (int size : sizes) {
            Record[] records = TestUtils.generateRandomRecords(size);
            
            long startTime = System.nanoTime();
            QuickSort quickSort = new QuickSort();
            PerformanceResult result = quickSort.sort(records);
            long endTime = System.nanoTime();
            
            double timeInMs = (endTime - startTime) / 1_000_000.0;
            
            System.out.printf("QuickSort - Size: %d, Time: %.2f ms, Comparisons: %d, Swaps: %d%n",
                size, timeInMs, result.getComparisons(), result.getSwaps());
            
            assertTrue(TestUtils.isSorted(records), "Array should be sorted");
            assertTrue(timeInMs < 10000, "Performance degraded for size " + size);
        }
    }

    @Test
    @DisplayName("HeapSort performance benchmark")
    void heapSortPerformanceTest() {
        int[] sizes = {100, 1000, 10000, 100000};
        
        System.out.println("\n=== HeapSort Performance Benchmark ===");
        
        for (int size : sizes) {
            Record[] records = TestUtils.generateRandomRecords(size);
            
            long startTime = System.nanoTime();
            HeapSort heapSort = new HeapSort();
            PerformanceResult result = heapSort.sort(records);
            long endTime = System.nanoTime();
            
            double timeInMs = (endTime - startTime) / 1_000_000.0;
            
            System.out.printf("HeapSort - Size: %d, Time: %.2f ms, Comparisons: %d, Swaps: %d%n",
                size, timeInMs, result.getComparisons(), result.getSwaps());
            
            assertTrue(TestUtils.isSorted(records), "Array should be sorted");
            assertTrue(timeInMs < 15000, "Performance degraded for size " + size);
        }
    }

    @Test
    @DisplayName("Algorithm comparison test")
    void algorithmComparisonTest() {
        int size = 10000;
        Record[] records1 = TestUtils.generateRandomRecords(size);
        Record[] records2 = TestUtils.cloneRecords(records1);
        
        QuickSort quickSort = new QuickSort();
        HeapSort heapSort = new HeapSort();
        
        long start1 = System.nanoTime();
        PerformanceResult result1 = quickSort.sort(records1);
        long end1 = System.nanoTime();
        
        long start2 = System.nanoTime();
        PerformanceResult result2 = heapSort.sort(records2);
        long end2 = System.nanoTime();
        
        double quickSortTime = (end1 - start1) / 1_000_000.0;
        double heapSortTime = (end2 - start2) / 1_000_000.0;
        
        System.out.println("\n=== Algorithm Comparison ===");
        System.out.printf("QuickSort: %.2f ms, Comparisons: %d, Swaps: %d%n", 
            quickSortTime, result1.getComparisons(), result1.getSwaps());
        System.out.printf("HeapSort: %.2f ms, Comparisons: %d, Swaps: %d%n", 
            heapSortTime, result2.getComparisons(), result2.getSwaps());
        
        assertTrue(TestUtils.isSorted(records1), "QuickSort should sort correctly");
        assertTrue(TestUtils.isSorted(records2), "HeapSort should sort correctly");
    }

    @Test
    @DisplayName("Performance with different input distributions")
    void performanceWithDifferentDistributions() {
        int size = 5000;
        
        System.out.println("\n=== Performance with Different Distributions ===");
        
        // Test with sorted input
        Record[] sorted = TestUtils.generateSortedRecords(size);
        long start = System.nanoTime();
        new QuickSort().sort(sorted);
        double sortedTime = (System.nanoTime() - start) / 1_000_000.0;
        System.out.printf("QuickSort (Sorted): %.2f ms%n", sortedTime);
        
        // Test with reverse sorted input
        Record[] reverse = TestUtils.generateReverseSortedRecords(size);
        start = System.nanoTime();
        new QuickSort().sort(reverse);
        double reverseTime = (System.nanoTime() - start) / 1_000_000.0;
        System.out.printf("QuickSort (Reverse): %.2f ms%n", reverseTime);
        
        // Test with random input
        Record[] random = TestUtils.generateRandomRecords(size);
        start = System.nanoTime();
        new QuickSort().sort(random);
        double randomTime = (System.nanoTime() - start) / 1_000_000.0;
        System.out.printf("QuickSort (Random): %.2f ms%n", randomTime);
    }

    @Test
    @DisplayName("Memory efficiency test")
    void memoryEfficiencyTest() {
        int size = 10000;
        Record[] records = TestUtils.generateRandomRecords(size);
        
        Runtime runtime = Runtime.getRuntime();
        runtime.gc();
        long memoryBefore = runtime.totalMemory() - runtime.freeMemory();
        
        QuickSort quickSort = new QuickSort();
        PerformanceResult result = quickSort.sort(records);
        
        runtime.gc();
        long memoryAfter = runtime.totalMemory() - runtime.freeMemory();
        long memoryUsed = memoryAfter - memoryBefore;
        
        System.out.printf("\nMemory used: %.2f MB%n", memoryUsed / 1_000_000.0);
        
        assertTrue(TestUtils.isSorted(records), "Array should be sorted");
        // Memory usage should be reasonable (less than 100MB for 10k records)
        assertTrue(memoryUsed < 100_000_000, "Memory usage too high");
    }
}

