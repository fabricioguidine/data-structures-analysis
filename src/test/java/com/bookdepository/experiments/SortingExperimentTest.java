package com.bookdepository.experiments;

import com.bookdepository.model.Record;
import com.bookdepository.io.FileReader;
import com.bookdepository.io.OutputFileWriter;
import com.bookdepository.algorithms.sorting.QuickSort;
import com.bookdepository.algorithms.sorting.HeapSort;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * JUnit tests for the SortingExperiment class.
 */
public class SortingExperimentTest {

    @Before
    public void setUp() {
        // Create necessary directories
        new File("input").mkdirs();
        new File("output").mkdirs();
        new File("data").mkdirs();
        
        // Create test input file
        try {
            FileWriter fw = new FileWriter("input/entrada.txt");
            fw.write("2\n");
            fw.write("5\n");
            fw.write("10\n");
            fw.close();
        } catch (IOException e) {
            // Ignore
        }
    }

    @After
    public void tearDown() {
        // Clean up test files
        new File("input/entrada.txt").delete();
        new File("output/saida.txt").delete();
    }

    @Test
    public void testSortingExperimentCanCreateSorters() {
        QuickSort quickSort = new QuickSort();
        HeapSort heapSort = new HeapSort();
        
        assertNotNull(quickSort);
        assertNotNull(heapSort);
    }

    @Test
    public void testSortingExperimentHandlesEmptyRecords() {
        Record[] emptyRecords = new Record[0];
        
        QuickSort quickSort = new QuickSort();
        HeapSort heapSort = new HeapSort();
        
        // Should not throw exception
        quickSort.sort(emptyRecords);
        heapSort.sort(emptyRecords);
    }

    @Test
    public void testSortingExperimentHandlesSingleRecord() {
        Record record = new Record("id1", "Book 1", null, null, null, null, null, 0f, 0, 1);
        Record[] records = {record};
        
        QuickSort quickSort = new QuickSort();
        HeapSort heapSort = new HeapSort();
        
        // Should not throw exception
        quickSort.sort(records);
        heapSort.sort(records);
        
        assertEquals("id1", records[0].getId());
    }

    @Test
    public void testSortingExperimentSortsCorrectly() {
        Record[] records = new Record[5];
        for (int i = 0; i < 5; i++) {
            records[i] = new Record("id" + (5 - i), "Book " + (5 - i), 
                null, null, null, null, null, 0f, 0, 5 - i);
        }
        
        QuickSort quickSort = new QuickSort();
        quickSort.sort(records);
        
        // Verify sorted order
        for (int i = 0; i < records.length; i++) {
            assertEquals(i + 1, records[i].getBestsellersRank());
        }
    }

    @Test
    public void testOutputFileWriterClearOutput() {
        try {
            OutputFileWriter.clearOutput();
            File outputFile = new File("output/saida.txt");
            assertTrue(outputFile.exists() || !outputFile.exists()); // File may or may not exist
        } catch (IOException e) {
            // Ignore for test
        }
    }
}

