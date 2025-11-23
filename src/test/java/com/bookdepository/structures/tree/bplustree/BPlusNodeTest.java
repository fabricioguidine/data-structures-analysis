package com.bookdepository.structures.tree.bplustree;

import com.bookdepository.model.Record;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * JUnit tests for the BPlusNode abstract class.
 * Tests functionality through concrete implementations.
 */
public class BPlusNodeTest {

    @Test
    public void testBPlusLeafNodeCreation() {
        BPlusLeafNode node = new BPlusLeafNode(2);
        assertNotNull(node);
        assertTrue(node instanceof BPlusNode);
        assertEquals(2, node.degree);
    }

    @Test
    public void testBPlusInternalNodeCreation() {
        BPlusInternalNode node = new BPlusInternalNode(2);
        assertNotNull(node);
        assertTrue(node instanceof BPlusNode);
        assertEquals(2, node.degree);
    }

    @Test
    public void testBPlusLeafNodeIsFull() {
        BPlusLeafNode node = new BPlusLeafNode(2);
        assertFalse(node.isFull());
        
        // Fill the node
        for (int i = 0; i < 2 * 2; i++) {
            Record record = new Record("id" + i, "Book " + i, null, null, null, null, null, 0f, 0, i);
            node.insert("id" + i, record);
        }
        
        assertTrue(node.isFull());
    }

    @Test
    public void testBPlusInternalNodeIsFull() {
        BPlusInternalNode node = new BPlusInternalNode(2);
        assertFalse(node.isFull());
        
        // Fill the node (internal nodes can have 2*degree keys)
        BPlusLeafNode child = new BPlusLeafNode(2);
        for (int i = 0; i < 2 * 2; i++) {
            node.insert("key" + i, child);
        }
        
        assertTrue(node.isFull());
    }

    @Test
    public void testParentRelationship() {
        BPlusLeafNode leaf = new BPlusLeafNode(2);
        BPlusInternalNode parent = new BPlusInternalNode(2);
        
        assertNull(leaf.parent);
        
        leaf.parent = parent;
        assertEquals(parent, leaf.parent);
    }

    @Test
    public void testDifferentDegrees() {
        BPlusLeafNode node2 = new BPlusLeafNode(2);
        BPlusLeafNode node20 = new BPlusLeafNode(20);
        
        assertEquals(2, node2.degree);
        assertEquals(20, node20.degree);
    }
}

