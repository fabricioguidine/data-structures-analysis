package com.bookdepository.experiments;

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
 * Integration tests for SortingExperiment.
 * These tests verify the experiment logic works correctly end-to-end.
 */
@DisplayName("SortingExperiment Integration Tests")
class SortingExperimentIntegrationTest {

    @Test
    @DisplayName("Should execute QuickSort experiment successfully")
    void testQuickSortExperiment() {
        Record[] records = TestUtils.generateRandomRecords(100);
        QuickSort quickSort = new QuickSort();
        
        PerformanceResult result = quickSort.sort(records);
        
        assertNotNull(result, "Result should not be null");
        assertTrue(TestUtils.isSorted(records), "Records should be sorted");
        assertTrue(result.getComparisons() > 0, "Should have comparisons");
        assertTrue(result.getExecutionTime() >= 0, "Execution time should be non-negative");
    }

    @Test
    @DisplayName("Should execute HeapSort experiment successfully")
    void testHeapSortExperiment() {
        Record[] records = TestUtils.generateRandomRecords(100);
        HeapSort heapSort = new HeapSort();
        
        PerformanceResult result = heapSort.sort(records);
        
        assertNotNull(result, "Result should not be null");
        assertTrue(TestUtils.isSorted(records), "Records should be sorted");
        assertTrue(result.getComparisons() > 0, "Should have comparisons");
        assertTrue(result.getSwaps() > 0, "Should have swaps");
    }

    @Test
    @DisplayName("Should handle multiple experiment runs correctly")
    void testMultipleExperimentRuns() {
        int[] sizes = {10, 50, 100};
        
        for (int size : sizes) {
            Record[] records = TestUtils.generateRandomRecords(size);
            
            QuickSort quickSort = new QuickSort();
            PerformanceResult quickResult = quickSort.sort(records);
            
            Record[] records2 = TestUtils.generateRandomRecords(size);
            HeapSort heapSort = new HeapSort();
            PerformanceResult heapResult = heapSort.sort(records2);
            
            assertTrue(TestUtils.isSorted(records), 
                "QuickSort should sort correctly for size " + size);
            assertTrue(TestUtils.isSorted(records2), 
                "HeapSort should sort correctly for size " + size);
            assertNotNull(quickResult);
            assertNotNull(heapResult);
        }
    }

    @Test
    @DisplayName("Should produce consistent results across runs")
    void testConsistencyAcrossRuns() {
        Record[] records1 = TestUtils.generateRandomRecords(1000);
        Record[] records2 = TestUtils.cloneRecords(records1);
        Record[] records3 = TestUtils.cloneRecords(records1);
        
        QuickSort quickSort = new QuickSort();
        PerformanceResult result1 = quickSort.sort(records1);
        PerformanceResult result2 = quickSort.sort(records2);
        
        assertTrue(TestUtils.isSorted(records1));
        assertTrue(TestUtils.isSorted(records2));
        
        // Both should produce the same sorted order
        for (int i = 0; i < records1.length; i++) {
            assertEquals(records1[i].getBestsellersRank(), 
                        records2[i].getBestsellersRank(),
                        "Results should be consistent at index " + i);
        }
    }
}

