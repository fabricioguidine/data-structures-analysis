package com.bookdepository.model;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

/**
 * JUnit tests for the Record class.
 */
public class RecordTest {
    private Record record;
    private List<String> authors;
    private List<String> categories;

    @Before
    public void setUp() {
        authors = Arrays.asList("author1", "author2");
        categories = Arrays.asList("Fiction", "Adventure");
        record = new Record(
            "book123",
            "Test Book",
            authors,
            "0123456789",
            "9780123456789",
            "First Edition",
            categories,
            4.5f,
            100,
            5
        );
    }

    @Test
    public void testDefaultConstructor() {
        Record r = new Record();
        assertNotNull(r);
        assertNull(r.getId());
        assertNull(r.getTitle());
    }

    @Test
    public void testConstructorWithAllFields() {
        assertNotNull(record);
        assertEquals("book123", record.getId());
        assertEquals("Test Book", record.getTitle());
        assertEquals(authors, record.getAuthors());
        assertEquals("0123456789", record.getIsbn10());
        assertEquals("9780123456789", record.getIsbn13());
        assertEquals("First Edition", record.getEdition());
        assertEquals(categories, record.getCategories());
        assertEquals(4.5f, record.getRatingAvg(), 0.001f);
        assertEquals(100, record.getRatingCount());
        assertEquals(5, record.getBestsellersRank());
    }

    @Test
    public void testSettersAndGetters() {
        Record r = new Record();
        
        r.setId("newId");
        assertEquals("newId", r.getId());

        r.setTitle("New Title");
        assertEquals("New Title", r.getTitle());

        List<String> newAuthors = Arrays.asList("author3");
        r.setAuthors(newAuthors);
        assertEquals(newAuthors, r.getAuthors());

        r.setIsbn10("9876543210");
        assertEquals("9876543210", r.getIsbn10());

        r.setIsbn13("9789876543210");
        assertEquals("9789876543210", r.getIsbn13());

        r.setEdition("Second Edition");
        assertEquals("Second Edition", r.getEdition());

        List<String> newCategories = Arrays.asList("Science");
        r.setCategories(newCategories);
        assertEquals(newCategories, r.getCategories());

        r.setRatingAvg(3.8f);
        assertEquals(3.8f, r.getRatingAvg(), 0.001f);

        r.setRatingCount(50);
        assertEquals(50, r.getRatingCount());

        r.setBestsellersRank(10);
        assertEquals(10, r.getBestsellersRank());
    }

    @Test
    public void testCompareByRank() {
        Record r1 = new Record("id1", "Book 1", null, null, null, null, null, 0f, 0, 5);
        Record r2 = new Record("id2", "Book 2", null, null, null, null, null, 0f, 0, 10);
        Record r3 = new Record("id3", "Book 3", null, null, null, null, null, 0f, 0, 5);
        Record r4 = new Record("id4", "Book 4", null, null, null, null, null, 0f, 0, 0);
        Record r5 = new Record("id5", "Book 5", null, null, null, null, null, 0f, 0, 0);

        // r1 (rank 5) should come before r2 (rank 10)
        assertTrue(r1.compareByRank(r2) < 0);
        
        // r2 (rank 10) should come after r1 (rank 5)
        assertTrue(r2.compareByRank(r1) > 0);

        // Equal ranks
        assertEquals(0, r1.compareByRank(r3));

        // Record with rank 0 should go to the end
        assertTrue(r1.compareByRank(r4) < 0);
        assertTrue(r4.compareByRank(r1) > 0);

        // Both records with rank 0 are equal
        assertEquals(0, r4.compareByRank(r5));
    }

    @Test
    public void testToString() {
        String str = record.toString();
        assertNotNull(str);
        assertTrue(str.contains("book123"));
        assertTrue(str.contains("Test Book"));
    }
}

