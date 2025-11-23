package com.bookdepository.experiments;

import com.bookdepository.model.Record;
import com.bookdepository.structures.tree.redblack.RedBlackTree;
import com.bookdepository.structures.tree.bplustree.BPlusTree;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * JUnit tests for the TreeExperiment class.
 */
public class TreeExperimentTest {

    @Test
    public void testRedBlackTreeCreation() {
        RedBlackTree tree = new RedBlackTree();
        assertNotNull(tree);
        assertEquals(0, tree.getComparisons());
        assertEquals(0, tree.getRotations());
    }

    @Test
    public void testBPlusTreeCreation() {
        BPlusTree tree1 = new BPlusTree(2);
        BPlusTree tree2 = new BPlusTree(20);
        
        assertNotNull(tree1);
        assertNotNull(tree2);
        assertEquals(0, tree1.getComparisons());
        assertEquals(0, tree2.getComparisons());
    }

    @Test
    public void testTreeExperimentInsertRecords() {
        RedBlackTree tree = new RedBlackTree();
        
        for (int i = 0; i < 10; i++) {
            Record record = new Record("id" + i, "Book " + i, null, null, null, null, null, 0f, 0, i);
            tree.insert(record);
        }
        
        // Verify all records can be found
        for (int i = 0; i < 10; i++) {
            Record found = tree.search("id" + i);
            assertNotNull(found);
            assertEquals("id" + i, found.getId());
        }
    }

    @Test
    public void testTreeExperimentBPlusTreeInsert() {
        BPlusTree tree = new BPlusTree(2);
        
        for (int i = 0; i < 10; i++) {
            Record record = new Record("id" + i, "Book " + i, null, null, null, null, null, 0f, 0, i);
            tree.insert(record);
        }
        
        assertEquals(10, tree.getInsertions());
        
        // Verify all records can be found
        for (int i = 0; i < 10; i++) {
            Record found = tree.search("id" + i);
            assertNotNull(found);
            assertEquals("id" + i, found.getId());
        }
    }

    @Test
    public void testTreeExperimentPerformanceTracking() {
        RedBlackTree tree = new RedBlackTree();
        tree.resetCounters();
        
        for (int i = 0; i < 50; i++) {
            Record record = new Record("id" + i, "Book " + i, null, null, null, null, null, 0f, 0, i);
            tree.insert(record);
        }
        
        // Search for records
        for (int i = 0; i < 50; i++) {
            tree.search("id" + i);
        }
        
        assertTrue(tree.getComparisons() > 0);
    }

    @Test
    public void testTreeExperimentBPlusTreeDifferentDegrees() {
        BPlusTree tree2 = new BPlusTree(2);
        BPlusTree tree20 = new BPlusTree(20);
        
        // Insert same records into both
        for (int i = 0; i < 100; i++) {
            Record record = new Record("id" + i, "Book " + i, null, null, null, null, null, 0f, 0, i);
            tree2.insert(record);
            tree20.insert(record);
        }
        
        assertEquals(100, tree2.getInsertions());
        assertEquals(100, tree20.getInsertions());
        
        // Both should have all records
        for (int i = 0; i < 100; i++) {
            assertNotNull(tree2.search("id" + i));
            assertNotNull(tree20.search("id" + i));
        }
    }

    @Test
    public void testTreeExperimentHandlesNullRecords() {
        RedBlackTree tree = new RedBlackTree();
        BPlusTree bplusTree = new BPlusTree(2);
        
        // Should not throw exception
        tree.insert(null);
        bplusTree.insert(null);
    }

    @Test
    public void testTreeExperimentResetCounters() {
        RedBlackTree tree = new RedBlackTree();
        BPlusTree bplusTree = new BPlusTree(2);
        
        // Insert and search
        Record record = new Record("id1", "Book 1", null, null, null, null, null, 0f, 0, 1);
        tree.insert(record);
        tree.search("id1");
        bplusTree.insert(record);
        bplusTree.search("id1");
        
        // Reset counters
        tree.resetCounters();
        bplusTree.resetCounters();
        
        assertEquals(0, tree.getComparisons());
        assertEquals(0, tree.getRotations());
        assertEquals(0, bplusTree.getComparisons());
        assertEquals(0, bplusTree.getInsertions());
    }
}

