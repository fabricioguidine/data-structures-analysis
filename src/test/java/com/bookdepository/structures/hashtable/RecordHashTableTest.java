package com.bookdepository.structures.hashtable;

import com.bookdepository.model.Record;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

/**
 * JUnit tests for the RecordHashTable class.
 */
public class RecordHashTableTest {
    private RecordHashTable hashTable;

    @Before
    public void setUp() {
        hashTable = new RecordHashTable(16);
    }

    @Test
    public void testDefaultConstructor() {
        RecordHashTable table = new RecordHashTable();
        assertNotNull(table);
        assertEquals(0, table.size());
        assertTrue(table.capacity() > 0);
    }

    @Test
    public void testConstructorWithCapacity() {
        RecordHashTable table = new RecordHashTable(32);
        assertEquals(0, table.size());
        assertEquals(32, table.capacity());
    }

    @Test
    public void testInsert() {
        Record record = new Record("id1", "Book 1", null, null, null, null, null, 0f, 0, 1);
        
        boolean result = hashTable.insert(record);
        assertTrue(result);
        assertEquals(1, hashTable.size());
    }

    @Test
    public void testInsertNullRecord() {
        boolean result = hashTable.insert(null);
        assertFalse(result);
        assertEquals(0, hashTable.size());
    }

    @Test
    public void testInsertRecordWithNullId() {
        Record record = new Record();
        record.setTitle("Book 1");
        
        boolean result = hashTable.insert(record);
        assertFalse(result);
        assertEquals(0, hashTable.size());
    }

    @Test
    public void testSearch() {
        Record record = new Record("id1", "Book 1", null, null, null, null, null, 0f, 0, 1);
        hashTable.insert(record);
        
        Record found = hashTable.search("id1");
        assertNotNull(found);
        assertEquals("id1", found.getId());
        assertEquals("Book 1", found.getTitle());
    }

    @Test
    public void testSearchNonExistent() {
        Record found = hashTable.search("nonexistent");
        assertNull(found);
    }

    @Test
    public void testSearchNull() {
        Record found = hashTable.search(null);
        assertNull(found);
    }

    @Test
    public void testMultipleInsertions() {
        for (int i = 0; i < 10; i++) {
            Record record = new Record("id" + i, "Book " + i, null, null, null, null, null, 0f, 0, i);
            hashTable.insert(record);
        }
        
        assertEquals(10, hashTable.size());
    }

    @Test
    public void testInsertDuplicate() {
        Record record1 = new Record("id1", "Book 1", null, null, null, null, null, 0f, 0, 1);
        Record record2 = new Record("id1", "Book 1 Updated", null, null, null, null, null, 0f, 0, 2);
        
        hashTable.insert(record1);
        assertEquals(1, hashTable.size());
        
        hashTable.insert(record2);
        assertEquals(1, hashTable.size()); // Should replace, not add
        
        Record found = hashTable.search("id1");
        assertNotNull(found);
        assertEquals("Book 1 Updated", found.getTitle());
    }

    @Test
    public void testGetAllRecords() {
        for (int i = 0; i < 5; i++) {
            Record record = new Record("id" + i, "Book " + i, null, null, null, null, null, 0f, 0, i);
            hashTable.insert(record);
        }
        
        Record[] allRecords = hashTable.getAllRecords();
        assertNotNull(allRecords);
        assertEquals(5, allRecords.length);
    }

    @Test
    public void testResize() {
        // Insert enough records to trigger resize
        for (int i = 0; i < 20; i++) {
            Record record = new Record("id" + i, "Book " + i, null, null, null, null, null, 0f, 0, i);
            hashTable.insert(record);
        }
        
        assertTrue(hashTable.capacity() >= 20);
        assertEquals(20, hashTable.size());
        
        // Verify all records are still searchable after resize
        for (int i = 0; i < 20; i++) {
            Record found = hashTable.search("id" + i);
            assertNotNull(found);
            assertEquals("id" + i, found.getId());
        }
    }
}

