package com.bookdepository.structures.tree.redblack;

import com.bookdepository.model.Record;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * JUnit tests for the RedBlackNode class.
 */
public class RedBlackNodeTest {

    @Test
    public void testConstructor() {
        Record record = new Record("id1", "Book 1", null, null, null, null, null, 0f, 0, 1);
        RedBlackNode node = new RedBlackNode("id1", record, RedBlackNode.RED);
        
        assertNotNull(node);
        assertEquals("id1", node.key);
        assertEquals(record, node.record);
        assertEquals(RedBlackNode.RED, node.color);
    }

    @Test
    public void testConstructorWithBlackColor() {
        Record record = new Record("id1", "Book 1", null, null, null, null, null, 0f, 0, 1);
        RedBlackNode node = new RedBlackNode("id1", record, RedBlackNode.BLACK);
        
        assertNotNull(node);
        assertEquals(RedBlackNode.BLACK, node.color);
    }

    @Test
    public void testNodeWithChildren() {
        Record record1 = new Record("id1", "Book 1", null, null, null, null, null, 0f, 0, 1);
        Record record2 = new Record("id2", "Book 2", null, null, null, null, null, 0f, 0, 2);
        Record record3 = new Record("id3", "Book 3", null, null, null, null, null, 0f, 0, 3);
        
        RedBlackNode root = new RedBlackNode("id2", record2, RedBlackNode.BLACK);
        RedBlackNode left = new RedBlackNode("id1", record1, RedBlackNode.RED);
        RedBlackNode right = new RedBlackNode("id3", record3, RedBlackNode.RED);
        
        root.left = left;
        root.right = right;
        left.parent = root;
        right.parent = root;
        
        assertNotNull(root.left);
        assertNotNull(root.right);
        assertEquals(root, left.parent);
        assertEquals(root, right.parent);
    }

    @Test
    public void testNullChildren() {
        Record record = new Record("id1", "Book 1", null, null, null, null, null, 0f, 0, 1);
        RedBlackNode node = new RedBlackNode("id1", record, RedBlackNode.RED);
        
        assertNull(node.left);
        assertNull(node.right);
        assertNull(node.parent);
    }

    @Test
    public void testColorConstants() {
        // Verify color constants are defined
        assertNotNull(RedBlackNode.RED);
        assertNotNull(RedBlackNode.BLACK);
        assertNotEquals(RedBlackNode.RED, RedBlackNode.BLACK);
    }

    @Test
    public void testNodeKeyComparison() {
        Record record1 = new Record("id1", "Book 1", null, null, null, null, null, 0f, 0, 1);
        Record record2 = new Record("id2", "Book 2", null, null, null, null, null, 0f, 0, 2);
        
        RedBlackNode node1 = new RedBlackNode("id1", record1, RedBlackNode.RED);
        RedBlackNode node2 = new RedBlackNode("id2", record2, RedBlackNode.RED);
        
        assertTrue(node1.key.compareTo(node2.key) < 0);
        assertTrue(node2.key.compareTo(node1.key) > 0);
    }

    @Test
    public void testUpdateRecord() {
        Record record1 = new Record("id1", "Book 1", null, null, null, null, null, 0f, 0, 1);
        Record record2 = new Record("id1", "Book 1 Updated", null, null, null, null, null, 0f, 0, 2);
        
        RedBlackNode node = new RedBlackNode("id1", record1, RedBlackNode.RED);
        assertEquals("Book 1", node.record.getTitle());
        
        node.record = record2;
        assertEquals("Book 1 Updated", node.record.getTitle());
    }
}

