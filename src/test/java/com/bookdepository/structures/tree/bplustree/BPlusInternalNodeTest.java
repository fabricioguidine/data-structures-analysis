package com.bookdepository.structures.tree.bplustree;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * JUnit tests for the BPlusInternalNode class.
 */
public class BPlusInternalNodeTest {

    @Test
    public void testConstructor() {
        BPlusInternalNode node = new BPlusInternalNode(2);
        assertNotNull(node);
        assertEquals(2, node.degree);
        assertEquals(0, node.getKeyCount());
    }

    @Test
    public void testInsertKeyAndChild() {
        BPlusInternalNode node = new BPlusInternalNode(2);
        BPlusLeafNode child = new BPlusLeafNode(2);
        
        int index = node.insert("key1", child);
        assertTrue(index >= 0);
        assertEquals(1, node.getKeyCount());
        assertEquals(child, node.getChild(index + 1));
        assertEquals(node, child.parent);
    }

    @Test
    public void testInsertMultiple() {
        BPlusInternalNode node = new BPlusInternalNode(2);
        
        for (int i = 0; i < 3; i++) {
            BPlusLeafNode child = new BPlusLeafNode(2);
            int index = node.insert("key" + i, child);
            assertTrue(index >= 0);
        }
        
        assertEquals(3, node.getKeyCount());
    }

    @Test
    public void testInsertWhenFull() {
        BPlusInternalNode node = new BPlusInternalNode(2);
        int maxSize = 2 * 2; // degree * 2
        
        // Fill the node
        for (int i = 0; i < maxSize; i++) {
            BPlusLeafNode child = new BPlusLeafNode(2);
            node.insert("key" + i, child);
        }
        
        assertTrue(node.isFull());
        
        // Try to insert when full
        BPlusLeafNode child = new BPlusLeafNode(2);
        int index = node.insert("key" + maxSize, child);
        assertEquals(-1, index); // Should return -1 when full
    }

    @Test
    public void testGetKey() {
        BPlusInternalNode node = new BPlusInternalNode(2);
        
        node.insert("key1", new BPlusLeafNode(2));
        node.insert("key2", new BPlusLeafNode(2));
        
        assertEquals("key1", node.getKey(0));
        assertEquals("key2", node.getKey(1));
    }

    @Test
    public void testGetChild() {
        BPlusInternalNode node = new BPlusInternalNode(2);
        BPlusLeafNode child0 = new BPlusLeafNode(2);
        BPlusLeafNode child1 = new BPlusLeafNode(2);
        BPlusLeafNode child2 = new BPlusLeafNode(2);
        
        node.setChild(0, child0);
        node.insert("key1", child1);
        node.insert("key2", child2);
        
        assertEquals(child0, node.getChild(0));
        assertEquals(child1, node.getChild(1));
        assertEquals(child2, node.getChild(2));
    }

    @Test
    public void testSetChild() {
        BPlusInternalNode node = new BPlusInternalNode(2);
        BPlusLeafNode child = new BPlusLeafNode(2);
        
        node.setChild(0, child);
        assertEquals(child, node.getChild(0));
        assertEquals(node, child.parent);
    }

    @Test
    public void testSplit() {
        BPlusInternalNode node = new BPlusInternalNode(2);
        int maxSize = 2 * 2;
        
        // Fill the node
        node.setChild(0, new BPlusLeafNode(2));
        for (int i = 0; i < maxSize; i++) {
            node.insert("key" + i, new BPlusLeafNode(2));
        }
        
        BPlusInternalNode newInternal = node.split();
        assertNotNull(newInternal);
        assertTrue(newInternal instanceof BPlusInternalNode);
        assertTrue(node.getKeyCount() < maxSize);
        assertTrue(newInternal.getKeyCount() < maxSize);
    }

    @Test
    public void testSearch() {
        BPlusInternalNode node = new BPlusInternalNode(2);
        BPlusLeafNode leaf1 = new BPlusLeafNode(2);
        BPlusLeafNode leaf2 = new BPlusLeafNode(2);
        
        // Insert a record in leaf1
        Record record = new Record("id1", "Book 1", null, null, null, null, null, 0f, 0, 1);
        leaf1.insert("id1", record);
        
        node.setChild(0, leaf1);
        node.insert("key1", leaf2);
        
        Record found = node.search("id1");
        assertNotNull(found);
        assertEquals("id1", found.getId());
    }

    @Test
    public void testGetKeyCount() {
        BPlusInternalNode node = new BPlusInternalNode(2);
        assertEquals(0, node.getKeyCount());
        
        for (int i = 0; i < 5; i++) {
            node.insert("key" + i, new BPlusLeafNode(2));
            assertEquals(i + 1, node.getKeyCount());
        }
    }
}

