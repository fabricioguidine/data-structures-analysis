package com.bookdepository.structures.hashtable;

import com.bookdepository.model.Author;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

import java.util.List;

/**
 * JUnit tests for the AuthorHashTable class.
 */
public class AuthorHashTableTest {
    private AuthorHashTable hashTable;

    @Before
    public void setUp() {
        hashTable = new AuthorHashTable(16);
    }

    @Test
    public void testDefaultConstructor() {
        AuthorHashTable table = new AuthorHashTable();
        assertNotNull(table);
        assertEquals(0, table.size());
        assertTrue(table.capacity() > 0);
    }

    @Test
    public void testConstructorWithCapacity() {
        AuthorHashTable table = new AuthorHashTable(32);
        assertEquals(0, table.size());
        assertEquals(32, table.capacity());
    }

    @Test
    public void testInsertOrIncrement() {
        Author author = new Author("auth1", "John Doe");
        
        boolean result = hashTable.insertOrIncrement(author);
        assertTrue(result);
        assertEquals(1, hashTable.size());
    }

    @Test
    public void testInsertNullAuthor() {
        boolean result = hashTable.insertOrIncrement(null);
        assertFalse(result);
        assertEquals(0, hashTable.size());
    }

    @Test
    public void testInsertAuthorWithNullId() {
        Author author = new Author();
        author.setName("John Doe");
        
        boolean result = hashTable.insertOrIncrement(author);
        assertFalse(result);
        assertEquals(0, hashTable.size());
    }

    @Test
    public void testIncrementFrequency() {
        Author author1 = new Author("auth1", "John Doe");
        Author author2 = new Author("auth1", "John Doe");
        
        hashTable.insertOrIncrement(author1);
        assertEquals(1, hashTable.size());
        
        hashTable.insertOrIncrement(author2);
        assertEquals(1, hashTable.size()); // Should update, not add
        
        Author found = hashTable.search("auth1");
        assertNotNull(found);
        assertEquals(2, found.getFrequency());
    }

    @Test
    public void testSearch() {
        Author author = new Author("auth1", "John Doe");
        hashTable.insertOrIncrement(author);
        
        Author found = hashTable.search("auth1");
        assertNotNull(found);
        assertEquals("auth1", found.getId());
        assertEquals("John Doe", found.getName());
        assertEquals(1, found.getFrequency());
    }

    @Test
    public void testSearchNonExistent() {
        Author found = hashTable.search("nonexistent");
        assertNull(found);
    }

    @Test
    public void testSearchNull() {
        Author found = hashTable.search(null);
        assertNull(found);
    }

    @Test
    public void testMultipleAuthors() {
        for (int i = 0; i < 10; i++) {
            Author author = new Author("auth" + i, "Author " + i);
            hashTable.insertOrIncrement(author);
        }
        
        assertEquals(10, hashTable.size());
    }

    @Test
    public void testGetAllAuthors() {
        for (int i = 0; i < 5; i++) {
            Author author = new Author("auth" + i, "Author " + i);
            hashTable.insertOrIncrement(author);
        }
        
        List<Author> allAuthors = hashTable.getAllAuthors();
        assertNotNull(allAuthors);
        assertEquals(5, allAuthors.size());
    }

    @Test
    public void testResize() {
        // Insert enough authors to trigger resize
        for (int i = 0; i < 20; i++) {
            Author author = new Author("auth" + i, "Author " + i);
            hashTable.insertOrIncrement(author);
        }
        
        assertTrue(hashTable.capacity() >= 20);
        assertEquals(20, hashTable.size());
        
        // Verify all authors are still searchable after resize
        for (int i = 0; i < 20; i++) {
            Author found = hashTable.search("auth" + i);
            assertNotNull(found);
            assertEquals("auth" + i, found.getId());
        }
    }

    @Test
    public void testFrequencyTracking() {
        Author author = new Author("auth1", "John Doe");
        
        // Insert multiple times
        for (int i = 0; i < 5; i++) {
            hashTable.insertOrIncrement(author);
        }
        
        Author found = hashTable.search("auth1");
        assertNotNull(found);
        assertEquals(5, found.getFrequency());
    }
}

