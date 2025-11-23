package com.bookdepository.structures.tree.bplustree;

import com.bookdepository.model.Record;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * JUnit tests for the BPlusLeafNode class.
 */
public class BPlusLeafNodeTest {

    @Test
    public void testConstructor() {
        BPlusLeafNode node = new BPlusLeafNode(2);
        assertNotNull(node);
        assertEquals(2, node.degree);
        assertEquals(0, node.getKeyCount());
    }

    @Test
    public void testInsert() {
        BPlusLeafNode node = new BPlusLeafNode(2);
        Record record = new Record("id1", "Book 1", null, null, null, null, null, 0f, 0, 1);
        
        boolean result = node.insert("id1", record);
        assertTrue(result);
        assertEquals(1, node.getKeyCount());
    }

    @Test
    public void testInsertMultiple() {
        BPlusLeafNode node = new BPlusLeafNode(2);
        
        for (int i = 0; i < 3; i++) {
            Record record = new Record("id" + i, "Book " + i, null, null, null, null, null, 0f, 0, i);
            node.insert("id" + i, record);
        }
        
        assertEquals(3, node.getKeyCount());
    }

    @Test
    public void testInsertWhenFull() {
        BPlusLeafNode node = new BPlusLeafNode(2);
        int maxSize = 2 * 2; // degree * 2
        
        // Fill the node
        for (int i = 0; i < maxSize; i++) {
            Record record = new Record("id" + i, "Book " + i, null, null, null, null, null, 0f, 0, i);
            node.insert("id" + i, record);
        }
        
        assertTrue(node.isFull());
        
        // Try to insert when full
        Record record = new Record("id" + maxSize, "Book " + maxSize, null, null, null, null, null, 0f, 0, maxSize);
        boolean result = node.insert("id" + maxSize, record);
        assertFalse(result);
    }

    @Test
    public void testSearch() {
        BPlusLeafNode node = new BPlusLeafNode(2);
        Record record1 = new Record("id1", "Book 1", null, null, null, null, null, 0f, 0, 1);
        Record record2 = new Record("id2", "Book 2", null, null, null, null, null, 0f, 0, 2);
        
        node.insert("id1", record1);
        node.insert("id2", record2);
        
        Record found = node.search("id1");
        assertNotNull(found);
        assertEquals("id1", found.getId());
        
        Record notFound = node.search("nonexistent");
        assertNull(notFound);
    }

    @Test
    public void testSplit() {
        BPlusLeafNode node = new BPlusLeafNode(2);
        int maxSize = 2 * 2;
        
        // Fill the node
        for (int i = 0; i < maxSize; i++) {
            Record record = new Record("id" + i, "Book " + i, null, null, null, null, null, 0f, 0, i);
            node.insert("id" + i, record);
        }
        
        BPlusLeafNode newLeaf = node.split();
        assertNotNull(newLeaf);
        assertTrue(newLeaf instanceof BPlusLeafNode);
        assertTrue(node.getKeyCount() <= maxSize / 2 + 1);
        assertTrue(newLeaf.getKeyCount() <= maxSize / 2 + 1);
    }

    @Test
    public void testGetMinKey() {
        BPlusLeafNode node = new BPlusLeafNode(2);
        
        Record record1 = new Record("id1", "Book 1", null, null, null, null, null, 0f, 0, 1);
        Record record2 = new Record("id2", "Book 2", null, null, null, null, null, 0f, 0, 2);
        Record record3 = new Record("id3", "Book 3", null, null, null, null, null, 0f, 0, 3);
        
        node.insert("id3", record3);
        node.insert("id1", record1);
        node.insert("id2", record2);
        
        String minKey = node.getMinKey();
        assertEquals("id1", minKey);
    }

    @Test
    public void testGetKeyCount() {
        BPlusLeafNode node = new BPlusLeafNode(2);
        assertEquals(0, node.getKeyCount());
        
        for (int i = 0; i < 5; i++) {
            Record record = new Record("id" + i, "Book " + i, null, null, null, null, null, 0f, 0, i);
            node.insert("id" + i, record);
            assertEquals(i + 1, node.getKeyCount());
        }
    }
}

