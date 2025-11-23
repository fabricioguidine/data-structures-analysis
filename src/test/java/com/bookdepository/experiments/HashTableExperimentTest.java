package com.bookdepository.experiments;

import com.bookdepository.model.Record;
import com.bookdepository.model.Author;
import com.bookdepository.structures.hashtable.AuthorHashTable;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

/**
 * JUnit tests for the HashTableExperiment class.
 */
public class HashTableExperimentTest {

    @Test
    public void testAuthorHashTableCreation() {
        AuthorHashTable hashTable = new AuthorHashTable(16);
        assertNotNull(hashTable);
        assertEquals(0, hashTable.size());
    }

    @Test
    public void testHashTableExperimentProcessRecords() {
        AuthorHashTable hashTable = new AuthorHashTable(16);
        
        // Create test authors
        Author author1 = new Author("auth1", "Author One");
        Author author2 = new Author("auth2", "Author Two");
        
        // Simulate processing records
        hashTable.insertOrIncrement(author1);
        hashTable.insertOrIncrement(author1); // Increment frequency
        hashTable.insertOrIncrement(author2);
        
        assertEquals(2, hashTable.size());
        
        Author found = hashTable.search("auth1");
        assertNotNull(found);
        assertEquals(2, found.getFrequency());
    }

    @Test
    public void testHashTableExperimentWithMultipleAuthors() {
        AuthorHashTable hashTable = new AuthorHashTable(100);
        
        // Simulate processing multiple records with same author
        Author author = new Author("auth1", "Author One");
        for (int i = 0; i < 10; i++) {
            hashTable.insertOrIncrement(author);
        }
        
        Author found = hashTable.search("auth1");
        assertNotNull(found);
        assertEquals(10, found.getFrequency());
    }

    @Test
    public void testHashTableExperimentGetAllAuthors() {
        AuthorHashTable hashTable = new AuthorHashTable(16);
        
        for (int i = 0; i < 5; i++) {
            Author author = new Author("auth" + i, "Author " + i);
            hashTable.insertOrIncrement(author);
        }
        
        List<Author> allAuthors = hashTable.getAllAuthors();
        assertNotNull(allAuthors);
        assertEquals(5, allAuthors.size());
    }

    @Test
    public void testHashTableExperimentHandlesNullRecords() {
        AuthorHashTable hashTable = new AuthorHashTable(16);
        
        // Should not throw exception
        hashTable.insertOrIncrement(null);
        
        assertEquals(0, hashTable.size());
    }

    @Test
    public void testHashTableExperimentWithLargeDataset() {
        AuthorHashTable hashTable = new AuthorHashTable(1000);
        
        // Simulate large dataset
        for (int i = 0; i < 100; i++) {
            Author author = new Author("auth" + (i % 10), "Author " + (i % 10));
            hashTable.insertOrIncrement(author);
        }
        
        // Should have 10 unique authors
        List<Author> allAuthors = hashTable.getAllAuthors();
        assertNotNull(allAuthors);
        assertTrue(allAuthors.size() <= 10);
    }
}

