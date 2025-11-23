package com.bookdepository.model;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

/**
 * JUnit tests for the Author class.
 */
public class AuthorTest {
    private Author author;

    @Before
    public void setUp() {
        author = new Author("auth001", "John Doe");
    }

    @Test
    public void testDefaultConstructor() {
        Author a = new Author();
        assertNotNull(a);
        assertEquals(0, a.getFrequency());
    }

    @Test
    public void testConstructorWithIdAndName() {
        assertNotNull(author);
        assertEquals("auth001", author.getId());
        assertEquals("John Doe", author.getName());
        assertEquals(1, author.getFrequency());
    }

    @Test
    public void testConstructorWithFrequency() {
        Author a = new Author("auth002", "Jane Smith", 5);
        assertEquals("auth002", a.getId());
        assertEquals("Jane Smith", a.getName());
        assertEquals(5, a.getFrequency());
    }

    @Test
    public void testSettersAndGetters() {
        Author a = new Author();
        
        a.setId("newId");
        assertEquals("newId", a.getId());

        a.setName("New Name");
        assertEquals("New Name", a.getName());

        a.setFrequency(10);
        assertEquals(10, a.getFrequency());
    }

    @Test
    public void testIncrementFrequency() {
        assertEquals(1, author.getFrequency());
        author.incrementFrequency();
        assertEquals(2, author.getFrequency());
        author.incrementFrequency();
        assertEquals(3, author.getFrequency());
    }

    @Test
    public void testCompareByFrequency() {
        Author a1 = new Author("auth1", "Author 1", 10);
        Author a2 = new Author("auth2", "Author 2", 5);
        Author a3 = new Author("auth3", "Author 3", 10);

        // a1 (freq 10) should come before a2 (freq 5) - descending order
        assertTrue(a1.compareByFrequency(a2) < 0);
        
        // a2 (freq 5) should come after a1 (freq 10)
        assertTrue(a2.compareByFrequency(a1) > 0);

        // Equal frequencies
        assertEquals(0, a1.compareByFrequency(a3));
    }

    @Test
    public void testEquals() {
        Author a1 = new Author("auth001", "John Doe");
        Author a2 = new Author("auth001", "Different Name");
        Author a3 = new Author("auth002", "John Doe");
        Author a4 = null;
        String notAuthor = "Not an author";

        // Same ID should be equal
        assertEquals(author, a1);
        assertEquals(author, a2); // Same ID, different name still equals

        // Different ID should not be equal
        assertNotEquals(author, a3);

        // Null should not be equal
        assertNotEquals(author, a4);

        // Different type should not be equal
        assertNotEquals(author, notAuthor);
    }

    @Test
    public void testHashCode() {
        Author a1 = new Author("auth001", "John Doe");
        Author a2 = new Author("auth001", "Different Name");
        Author a3 = new Author("auth002", "John Doe");

        // Same ID should have same hash code
        assertEquals(author.hashCode(), a1.hashCode());
        assertEquals(author.hashCode(), a2.hashCode());

        // Different ID should have different hash code
        assertNotEquals(author.hashCode(), a3.hashCode());
    }

    @Test
    public void testToString() {
        String str = author.toString();
        assertNotNull(str);
        assertTrue(str.contains("auth001"));
        assertTrue(str.contains("John Doe"));
        assertTrue(str.contains("1"));
    }
}

