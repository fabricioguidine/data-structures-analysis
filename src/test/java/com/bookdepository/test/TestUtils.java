package com.bookdepository.test;

import com.bookdepository.model.Record;
import java.util.Random;

/**
 * Utility class for testing.
 * Provides helper methods to create test data for Record objects.
 */
public class TestUtils {

    private static final Random random = new Random();

    /**
     * Creates a Record with the given id and bestseller rank.
     *
     * @param id   The record ID
     * @param rank The bestseller rank
     * @return A new Record instance
     */
    public static Record createRecord(int id, int rank) {
        Record record = new Record();
        record.setId(String.valueOf(id));
        record.setBestsellersRank(rank);
        return record;
    }

    /**
     * Generates an array of sorted records (ranked 1 to size).
     *
     * @param size The number of records to generate
     * @return An array of sorted records
     */
    public static Record[] generateSortedRecords(int size) {
        Record[] records = new Record[size];
        for (int i = 0; i < size; i++) {
            records[i] = createRecord(i + 1, i + 1);
        }
        return records;
    }

    /**
     * Generates an array of reverse sorted records (ranked size to 1).
     *
     * @param size The number of records to generate
     * @return An array of reverse sorted records
     */
    public static Record[] generateReverseSortedRecords(int size) {
        Record[] records = new Record[size];
        for (int i = 0; i < size; i++) {
            records[i] = createRecord(i + 1, size - i);
        }
        return records;
    }

    /**
     * Generates an array of randomly ranked records.
     *
     * @param size The number of records to generate
     * @return An array of randomly ranked records
     */
    public static Record[] generateRandomRecords(int size) {
        Record[] records = new Record[size];
        for (int i = 0; i < size; i++) {
            records[i] = createRecord(i + 1, random.nextInt(1000) + 1);
        }
        return records;
    }

    /**
     * Checks if an array of records is sorted by bestseller rank.
     * Zero ranks are treated as being at the end.
     *
     * @param records The array of records to check
     * @return true if the array is sorted, false otherwise
     */
    public static boolean isSorted(Record[] records) {
        if (records == null || records.length <= 1) {
            return true;
        }
        
        boolean foundZero = false;
        for (int i = 0; i < records.length - 1; i++) {
            int rank1 = records[i].getBestsellersRank();
            int rank2 = records[i + 1].getBestsellersRank();
            
            if (rank1 == 0) {
                foundZero = true;
            }
            
            if (!foundZero && rank2 != 0) {
                if (rank1 > rank2) {
                    return false;
                }
            } else if (foundZero && rank2 != 0) {
                // Zero ranks should be at the end
                return false;
            }
        }
        return true;
    }

    /**
     * Creates a deep copy of a Record array.
     *
     * @param records The array to copy
     * @return A new array with copied records
     */
    public static Record[] cloneRecords(Record[] records) {
        if (records == null) {
            return null;
        }
        Record[] copy = new Record[records.length];
        for (int i = 0; i < records.length; i++) {
            // Create new record with same rank
            // Using index as ID if getId() is not available or returns null
            int rank = records[i].getBestsellersRank();
            try {
                String id = records[i].getId();
                if (id != null && !id.isEmpty()) {
                    copy[i] = createRecord(Integer.parseInt(id), rank);
                } else {
                    copy[i] = createRecord(i, rank);
                }
            } catch (Exception e) {
                // Fallback: use index as ID
                copy[i] = createRecord(i, rank);
            }
        }
        return copy;
    }
}

