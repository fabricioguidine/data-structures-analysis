package com.bookdepository.test;

import com.bookdepository.model.Record;
import com.bookdepository.io.PerformanceResult;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Utility class with custom assertions for testing.
 */
public class AssertionUtils {

    /**
     * Asserts that a Record array is properly sorted.
     *
     * @param records The array to check
     * @param message The assertion message
     */
    public static void assertSorted(Record[] records, String message) {
        assertTrue(TestUtils.isSorted(records), message);
    }

    /**
     * Asserts that a Record array is properly sorted.
     *
     * @param records The array to check
     */
    public static void assertSorted(Record[] records) {
        assertSorted(records, "Array should be sorted");
    }

    /**
     * Asserts that a PerformanceResult has valid metrics.
     *
     * @param result The performance result to check
     */
    public static void assertValidPerformanceResult(PerformanceResult result) {
        assertNotNull(result, "Performance result should not be null");
        assertTrue(result.getComparisons() >= 0, 
            "Comparisons should be non-negative");
        assertTrue(result.getSwaps() >= 0, 
            "Swaps should be non-negative");
        assertTrue(result.getExecutionTime() >= 0, 
            "Execution time should be non-negative");
    }

    /**
     * Asserts that two Record arrays have the same ranks.
     *
     * @param expected The expected array
     * @param actual   The actual array
     */
    public static void assertEqualRanks(Record[] expected, Record[] actual) {
        assertNotNull(expected, "Expected array should not be null");
        assertNotNull(actual, "Actual array should not be null");
        assertEquals(expected.length, actual.length, 
            "Arrays should have the same length");
        
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i].getBestsellersRank(), 
                        actual[i].getBestsellersRank(),
                        "Ranks should match at index " + i);
        }
    }
}

