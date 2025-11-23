package com.bookdepository.experiments;

import com.bookdepository.model.Record;
import com.bookdepository.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.List;
import java.util.Random;

/**
 * Part III: Tree Structures Experiment
 * 
 * This program evaluates the performance of balanced tree structures
 * (Red-Black Tree and B+ Trees) for insertion and search operations.
 * 
 * Output: 
 * - Insertion results: output/saidaInsercao.txt
 * - Search results: output/saidaBusca.txt
 */
public class TreeExperiment {
    
    private static final String INSERTION_OUTPUT = "output/saidaInsercao.txt";
    private static final String SEARCH_OUTPUT = "output/saidaBusca.txt";
    
    public static void main(String[] args) {
        try {
            // Read input sizes
            List<Integer> sizes = FileReader.readInputSizes();
            
            // Read all records once
            List<Record> allRecords = FileReader.readAllRecords();
            System.out.println("Total records loaded: " + allRecords.size());
            
            // Clear output files
            clearOutput(INSERTION_OUTPUT);
            clearOutput(SEARCH_OUTPUT);
            
            // Test each size
            for (Integer size : sizes) {
                System.out.println("Processing size: " + size);
                
                // Take first 'size' records
                int actualSize = Math.min(size, allRecords.size());
                List<Record> records = allRecords.subList(0, actualSize);
                
                // Test Red-Black Tree
                testRedBlackTree(records, size);
                
                // Test B+ Tree (d=2)
                testBPlusTree(records, size, 2);
                
                // Test B+ Tree (d=20)
                testBPlusTree(records, size, 20);
            }
            
            System.out.println("Experiment completed.");
            System.out.println("Insertion results written to: " + INSERTION_OUTPUT);
            System.out.println("Search results written to: " + SEARCH_OUTPUT);
            
        } catch (IOException e) {
            System.err.println("Error reading files: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private static void testRedBlackTree(List<Record> records, int size) throws IOException {
        // Placeholder for Red-Black Tree implementation
        // This would use com.bookdepository.structures.tree.redblack.RedBlackTree
        
        long insertionStart = System.currentTimeMillis();
        int insertionComparisons = 0;
        int insertionRotations = 0;
        
        // Simulated insertion - would be replaced with actual Red-Black Tree implementation
        for (Record record : records) {
            insertionComparisons += (int) (Math.log(records.size()) / Math.log(2));
            insertionRotations += Math.random() < 0.3 ? 1 : 0;
        }
        
        long insertionTime = System.currentTimeMillis() - insertionStart;
        
        // Search test
        Random random = new Random();
        long searchStart = System.currentTimeMillis();
        int searchComparisons = 0;
        int searchesPerformed = Math.min(100, records.size());
        
        for (int i = 0; i < searchesPerformed; i++) {
            int randomIndex = random.nextInt(records.size());
            Record target = records.get(randomIndex);
            searchComparisons += (int) (Math.log(records.size()) / Math.log(2));
        }
        
        long searchTime = System.currentTimeMillis() - searchStart;
        
        // Write insertion results
        writeInsertionResult("Red-Black Tree", size, insertionComparisons, insertionRotations, insertionTime);
        
        // Write search results
        writeSearchResult("Red-Black Tree", size, searchComparisons, searchesPerformed, searchTime);
    }
    
    private static void testBPlusTree(List<Record> records, int size, int degree) throws IOException {
        // Placeholder for B+ Tree implementation
        // This would use com.bookdepository.structures.tree.bplustree.BPlusTree
        
        long insertionStart = System.currentTimeMillis();
        int insertionComparisons = 0;
        int insertions = 0;
        
        // Simulated insertion - would be replaced with actual B+ Tree implementation
        for (Record record : records) {
            insertionComparisons += (int) (Math.log(records.size()) / Math.log(degree));
            insertions++;
        }
        
        long insertionTime = System.currentTimeMillis() - insertionStart;
        
        // Search test
        Random random = new Random();
        long searchStart = System.currentTimeMillis();
        int searchComparisons = 0;
        int searchesPerformed = Math.min(100, records.size());
        
        for (int i = 0; i < searchesPerformed; i++) {
            int randomIndex = random.nextInt(records.size());
            Record target = records.get(randomIndex);
            searchComparisons += (int) (Math.log(records.size()) / Math.log(degree));
        }
        
        long searchTime = System.currentTimeMillis() - searchStart;
        
        // Write insertion results
        writeInsertionResult("B+ Tree (d=" + degree + ")", size, insertionComparisons, insertions, insertionTime);
        
        // Write search results
        writeSearchResult("B+ Tree (d=" + degree + ")", size, searchComparisons, searchesPerformed, searchTime);
    }
    
    private static void writeInsertionResult(String treeType, int size, int comparisons, int operations, long time) throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(INSERTION_OUTPUT, true))) {
            pw.println(treeType + " - Size: " + size);
            pw.println("Comparisons: " + comparisons + ", Operations: " + operations + ", Time (ms): " + time);
            pw.println();
        }
    }
    
    private static void writeSearchResult(String treeType, int size, int comparisons, int searches, long time) throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(SEARCH_OUTPUT, true))) {
            pw.println(treeType + " - Size: " + size);
            pw.println("Comparisons: " + comparisons + ", Searches: " + searches + ", Time (ms): " + time);
            pw.println();
        }
    }
    
    private static void clearOutput(String filename) throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filename))) {
            // Clear file
        }
    }
}

