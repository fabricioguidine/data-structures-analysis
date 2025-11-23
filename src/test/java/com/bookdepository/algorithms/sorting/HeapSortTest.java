package com.bookdepository.algorithms.sorting;

import com.bookdepository.model.Record;
import com.bookdepository.io.PerformanceResult;
import com.bookdepository.test.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for HeapSort algorithm.
 */
@DisplayName("HeapSort Tests")
class HeapSortTest {

    private HeapSort heapSort;

    @BeforeEach
    void setUp() {
        heapSort = new HeapSort();
    }

    @Test
    @DisplayName("Should sort empty array")
    void testSortEmptyArray() {
        Record[] records = new Record[0];
        PerformanceResult result = heapSort.sort(records);
        
        assertNotNull(result);
        assertEquals(0, result.getComparisons());
        assertEquals(0, result.getSwaps());
        assertTrue(result.getExecutionTime() >= 0);
    }

    @Test
    @DisplayName("Should handle null array")
    void testSortNullArray() {
        PerformanceResult result = heapSort.sort(null);
        
        assertNotNull(result);
        assertEquals(0, result.getComparisons());
        assertEquals(0, result.getSwaps());
    }

    @Test
    @DisplayName("Should sort single element array")
    void testSortSingleElement() {
        Record[] records = {TestUtils.createRecord(1, 5)};
        PerformanceResult result = heapSort.sort(records);
        
        assertNotNull(result);
        assertEquals(5, records[0].getBestsellersRank());
        assertTrue(TestUtils.isSorted(records));
    }

    @Test
    @DisplayName("Should sort already sorted array")
    void testSortAlreadySorted() {
        Record[] records = TestUtils.generateSortedRecords(10);
        PerformanceResult result = heapSort.sort(records);
        
        assertNotNull(result);
        assertTrue(TestUtils.isSorted(records));
    }

    @Test
    @DisplayName("Should sort reverse sorted array")
    void testSortReverseSorted() {
        Record[] records = TestUtils.generateReverseSortedRecords(10);
        PerformanceResult result = heapSort.sort(records);
        
        assertNotNull(result);
        assertTrue(TestUtils.isSorted(records));
    }

    @Test
    @DisplayName("Should sort random array")
    void testSortRandomArray() {
        Record[] records = TestUtils.generateRandomRecords(100);
        PerformanceResult result = heapSort.sort(records);
        
        assertNotNull(result);
        assertTrue(TestUtils.isSorted(records));
        assertTrue(result.getComparisons() > 0);
        assertTrue(result.getSwaps() > 0);
    }

    @Test
    @DisplayName("Should sort array with duplicate ranks")
    void testSortWithDuplicates() {
        Record[] records = {
            TestUtils.createRecord(1, 3),
            TestUtils.createRecord(2, 1),
            TestUtils.createRecord(3, 3),
            TestUtils.createRecord(4, 2)
        };
        
        PerformanceResult result = heapSort.sort(records);
        
        assertNotNull(result);
        assertTrue(TestUtils.isSorted(records));
    }

    @Test
    @DisplayName("Should sort array with zero ranks")
    void testSortWithZeroRanks() {
        Record[] records = {
            TestUtils.createRecord(1, 0),
            TestUtils.createRecord(2, 3),
            TestUtils.createRecord(3, 1),
            TestUtils.createRecord(4, 0)
        };
        
        PerformanceResult result = heapSort.sort(records);
        
        assertNotNull(result);
        assertTrue(TestUtils.isSorted(records));
        
        // Zero ranks should be at the end
        boolean foundZero = false;
        for (Record record : records) {
            if (record.getBestsellersRank() == 0) {
                foundZero = true;
            } else if (foundZero) {
                fail("Non-zero rank found after zero rank");
            }
        }
    }

    @Test
    @DisplayName("Should track comparisons and swaps")
    void testPerformanceMetrics() {
        Record[] records = {
            TestUtils.createRecord(1, 10),
            TestUtils.createRecord(2, 5),
            TestUtils.createRecord(3, 8),
            TestUtils.createRecord(4, 1)
        };
        
        PerformanceResult result = heapSort.sort(records);
        
        assertNotNull(result);
        assertTrue(result.getComparisons() > 0);
        assertTrue(result.getSwaps() > 0);
        assertTrue(result.getExecutionTime() >= 0);
    }

    @ParameterizedTest
    @ValueSource(ints = {10, 100, 1000, 10000})
    @DisplayName("Should sort arrays of different sizes")
    void testSortDifferentSizes(int size) {
        Record[] records = TestUtils.generateRandomRecords(size);
        PerformanceResult result = heapSort.sort(records);
        
        assertTrue(TestUtils.isSorted(records));
        assertTrue(result.getComparisons() > 0);
        assertTrue(result.getSwaps() > 0);
    }

    @Test
    @DisplayName("Should have stable performance characteristics")
    void testPerformanceCharacteristics() {
        Record[] records = TestUtils.generateRandomRecords(1000);
        
        PerformanceResult result = heapSort.sort(records);
        
        assertTrue(TestUtils.isSorted(records));
        // HeapSort has O(n log n) complexity, so comparisons should be reasonable
        assertTrue(result.getComparisons() < 100000, 
            "Too many comparisons for 1000 elements");
    }

    @Test
    @DisplayName("Should handle large arrays efficiently")
    void testLargeArrayPerformance() {
        Record[] records = TestUtils.generateRandomRecords(50000);
        
        long startTime = System.currentTimeMillis();
        PerformanceResult result = heapSort.sort(records);
        long endTime = System.currentTimeMillis();
        
        assertTrue(TestUtils.isSorted(records));
        assertTrue((endTime - startTime) < 15000, "Sorting took too long");
    }

    @Test
    @DisplayName("Should handle array with all same ranks")
    void testSortAllSameRanks() {
        Record[] records = new Record[10];
        for (int i = 0; i < 10; i++) {
            records[i] = TestUtils.createRecord(i + 1, 5);
        }
        
        PerformanceResult result = heapSort.sort(records);
        
        assertNotNull(result);
        assertTrue(TestUtils.isSorted(records));
    }
}

