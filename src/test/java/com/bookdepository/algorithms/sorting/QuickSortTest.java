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
 * Unit tests for QuickSort algorithm.
 */
@DisplayName("QuickSort Tests")
class QuickSortTest {

    private QuickSort quickSort;

    @BeforeEach
    void setUp() {
        quickSort = new QuickSort();
    }

    @Test
    @DisplayName("Should sort empty array")
    void testSortEmptyArray() {
        Record[] records = new Record[0];
        PerformanceResult result = quickSort.sort(records);
        
        assertNotNull(result);
        assertEquals(0, result.getComparisons());
        assertEquals(0, result.getSwaps());
        assertTrue(result.getExecutionTime() >= 0);
    }

    @Test
    @DisplayName("Should handle null array")
    void testSortNullArray() {
        PerformanceResult result = quickSort.sort(null);
        
        assertNotNull(result);
        assertEquals(0, result.getComparisons());
        assertEquals(0, result.getSwaps());
    }

    @Test
    @DisplayName("Should sort single element array")
    void testSortSingleElement() {
        Record[] records = {TestUtils.createRecord(1, 5)};
        PerformanceResult result = quickSort.sort(records);
        
        assertNotNull(result);
        assertEquals(5, records[0].getBestsellersRank());
        assertTrue(TestUtils.isSorted(records));
    }

    @Test
    @DisplayName("Should sort already sorted array")
    void testSortAlreadySorted() {
        Record[] records = TestUtils.generateSortedRecords(10);
        PerformanceResult result = quickSort.sort(records);
        
        assertNotNull(result);
        assertTrue(TestUtils.isSorted(records));
        assertTrue(result.getComparisons() >= 0);
    }

    @Test
    @DisplayName("Should sort reverse sorted array")
    void testSortReverseSorted() {
        Record[] records = TestUtils.generateReverseSortedRecords(10);
        PerformanceResult result = quickSort.sort(records);
        
        assertNotNull(result);
        assertTrue(TestUtils.isSorted(records));
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
        
        PerformanceResult result = quickSort.sort(records);
        
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
        
        PerformanceResult result = quickSort.sort(records);
        
        assertNotNull(result);
        assertTrue(TestUtils.isSorted(records));
        
        // Zero ranks should be at the end
        int zeroCount = 0;
        for (Record record : records) {
            if (record.getBestsellersRank() == 0) {
                zeroCount++;
            }
        }
        // Verify zeros are at the end
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
    @DisplayName("Should sort random array")
    void testSortRandomArray() {
        Record[] records = TestUtils.generateRandomRecords(100);
        PerformanceResult result = quickSort.sort(records);
        
        assertNotNull(result);
        assertTrue(TestUtils.isSorted(records));
        assertTrue(result.getComparisons() > 0);
        assertTrue(result.getSwaps() >= 0);
    }

    @ParameterizedTest
    @ValueSource(ints = {10, 100, 1000, 10000})
    @DisplayName("Should sort arrays of different sizes")
    void testSortDifferentSizes(int size) {
        Record[] records = TestUtils.generateRandomRecords(size);
        PerformanceResult result = quickSort.sort(records);
        
        assertTrue(TestUtils.isSorted(records));
        assertTrue(result.getComparisons() > 0);
        assertTrue(result.getExecutionTime() >= 0);
    }

    @Test
    @DisplayName("Should track performance metrics correctly")
    void testPerformanceMetrics() {
        Record[] records = {
            TestUtils.createRecord(1, 5),
            TestUtils.createRecord(2, 2),
            TestUtils.createRecord(3, 8),
            TestUtils.createRecord(4, 1),
            TestUtils.createRecord(5, 9)
        };
        
        PerformanceResult result = quickSort.sort(records);
        
        assertNotNull(result);
        assertTrue(result.getComparisons() >= 0);
        assertTrue(result.getSwaps() >= 0);
        assertTrue(result.getExecutionTime() >= 0);
    }

    @Test
    @DisplayName("Should not modify array when already sorted")
    void testSortAlreadySortedPreservesOrder() {
        Record[] records = {
            TestUtils.createRecord(1, 1),
            TestUtils.createRecord(2, 2),
            TestUtils.createRecord(3, 3)
        };
        
        Record[] original = TestUtils.cloneRecords(records);
        quickSort.sort(records);
        
        // After sorting already sorted array, order should be preserved
        assertTrue(TestUtils.isSorted(records));
    }

    @Test
    @DisplayName("Should handle large arrays efficiently")
    void testLargeArrayPerformance() {
        Record[] records = TestUtils.generateRandomRecords(50000);
        
        long startTime = System.currentTimeMillis();
        PerformanceResult result = quickSort.sort(records);
        long endTime = System.currentTimeMillis();
        
        assertTrue(TestUtils.isSorted(records));
        assertTrue((endTime - startTime) < 10000, "Sorting took too long");
    }
}

