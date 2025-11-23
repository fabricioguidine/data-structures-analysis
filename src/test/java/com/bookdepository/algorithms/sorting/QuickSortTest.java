package com.bookdepository.algorithms.sorting;

import com.bookdepository.model.Record;
import com.bookdepository.io.PerformanceResult;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

import java.util.Arrays;

/**
 * JUnit tests for the QuickSort algorithm.
 */
public class QuickSortTest {
    private QuickSort quickSort;

    @Before
    public void setUp() {
        quickSort = new QuickSort();
    }

    @Test
    public void testSortEmptyArray() {
        Record[] records = new Record[0];
        PerformanceResult result = quickSort.sort(records);
        
        assertNotNull(result);
        assertEquals(0, result.getComparisons());
        assertEquals(0, result.getSwaps());
    }

    @Test
    public void testSortSingleElement() {
        Record record = new Record("1", "Book 1", null, null, null, null, null, 0f, 0, 5);
        Record[] records = {record};
        PerformanceResult result = quickSort.sort(records);
        
        assertNotNull(result);
        assertEquals("1", records[0].getId());
    }

    @Test
    public void testSortAlreadySorted() {
        Record[] records = new Record[5];
        for (int i = 0; i < 5; i++) {
            records[i] = new Record(String.valueOf(i + 1), "Book " + (i + 1), 
                null, null, null, null, null, 0f, 0, i + 1);
        }
        
        PerformanceResult result = quickSort.sort(records);
        assertNotNull(result);
        
        // Verify sorted order
        for (int i = 0; i < records.length; i++) {
            assertEquals(i + 1, records[i].getBestsellersRank());
        }
    }

    @Test
    public void testSortReverseOrder() {
        Record[] records = new Record[5];
        for (int i = 0; i < 5; i++) {
            records[i] = new Record(String.valueOf(5 - i), "Book " + (5 - i), 
                null, null, null, null, null, 0f, 0, 5 - i);
        }
        
        PerformanceResult result = quickSort.sort(records);
        assertNotNull(result);
        
        // Verify sorted order
        for (int i = 0; i < records.length; i++) {
            assertEquals(i + 1, records[i].getBestsellersRank());
        }
    }

    @Test
    public void testSortRandomOrder() {
        Record[] records = new Record[10];
        int[] ranks = {7, 3, 9, 1, 5, 2, 8, 4, 6, 10};
        
        for (int i = 0; i < 10; i++) {
            records[i] = new Record(String.valueOf(i + 1), "Book " + (i + 1), 
                null, null, null, null, null, 0f, 0, ranks[i]);
        }
        
        PerformanceResult result = quickSort.sort(records);
        assertNotNull(result);
        
        // Verify sorted order (ascending by rank)
        for (int i = 0; i < records.length; i++) {
            assertEquals(i + 1, records[i].getBestsellersRank());
        }
    }

    @Test
    public void testSortWithZeroRanks() {
        Record[] records = new Record[5];
        records[0] = new Record("1", "Book 1", null, null, null, null, null, 0f, 0, 5);
        records[1] = new Record("2", "Book 2", null, null, null, null, null, 0f, 0, 0);
        records[2] = new Record("3", "Book 3", null, null, null, null, null, 0f, 0, 2);
        records[3] = new Record("4", "Book 4", null, null, null, null, null, 0f, 0, 0);
        records[4] = new Record("5", "Book 5", null, null, null, null, null, 0f, 0, 10);
        
        PerformanceResult result = quickSort.sort(records);
        assertNotNull(result);
        
        // Records with rank 0 should be at the end
        // Valid ranks should be sorted: 2, 5, 10
        assertTrue(records[0].getBestsellersRank() == 2);
        assertTrue(records[1].getBestsellersRank() == 5);
        assertTrue(records[2].getBestsellersRank() == 10);
    }

    @Test
    public void testNullArray() {
        PerformanceResult result = quickSort.sort(null);
        assertNotNull(result);
    }

    @Test
    public void testPerformanceTracking() {
        Record[] records = new Record[100];
        for (int i = 0; i < 100; i++) {
            records[i] = new Record(String.valueOf(i), "Book " + i, 
                null, null, null, null, null, 0f, 0, 100 - i);
        }
        
        PerformanceResult result = quickSort.sort(records);
        
        assertNotNull(result);
        assertTrue(result.getComparisons() > 0);
        assertTrue(result.getSwaps() >= 0);
        assertTrue(result.getExecutionTime() >= 0);
    }

    @Test
    public void testGetComparisons() {
        Record[] records = new Record[5];
        for (int i = 0; i < 5; i++) {
            records[i] = new Record(String.valueOf(i), "Book " + i, 
                null, null, null, null, null, 0f, 0, 5 - i);
        }
        
        quickSort.sort(records);
        assertTrue(quickSort.getComparisons() > 0);
    }

    @Test
    public void testGetSwaps() {
        Record[] records = new Record[5];
        for (int i = 0; i < 5; i++) {
            records[i] = new Record(String.valueOf(i), "Book " + i, 
                null, null, null, null, null, 0f, 0, 5 - i);
        }
        
        quickSort.sort(records);
        assertTrue(quickSort.getSwaps() >= 0);
    }
}

