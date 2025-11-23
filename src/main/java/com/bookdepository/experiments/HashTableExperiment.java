package com.bookdepository.experiments;

import com.bookdepository.model.Record;
import com.bookdepository.model.Author;
import com.bookdepository.io.FileReader;
import com.bookdepository.io.Part2OutputWriter;
import com.bookdepository.structures.hashtable.AuthorHashTable;
import java.util.List;
import java.util.Collections;
import java.util.Scanner;
import java.io.IOException;

/**
 * Part II: Hash Tables Experiment
 * 
 * This program reads book records, uses hash tables to count author frequencies,
 * and identifies the most frequent authors.
 * 
 * Output: Results are written to output/saidaPart2.txt
 */
public class HashTableExperiment {
    
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the number of top authors to display (N): ");
            int topN = scanner.nextInt();
            scanner.close();
            
            // Read all records
            List<Record> records = FileReader.readAllRecords();
            System.out.println("Total records loaded: " + records.size());
            
            // Read authors map for name lookup
            java.util.Map<String, Author> authorMap = FileReader.readAuthorsMap();
            System.out.println("Total authors in map: " + authorMap.size());
            
            // Create hash table for authors with frequency tracking
            AuthorHashTable authorHashTable = new AuthorHashTable(records.size() / 2);
            
            // Process all records and count author frequencies
            int processedCount = 0;
            for (Record record : records) {
                if (record.getAuthors() != null) {
                    for (String authorId : record.getAuthors()) {
                        if (authorId != null && !authorId.isEmpty()) {
                            Author author = authorMap.get(authorId);
                            if (author != null) {
                                authorHashTable.insertOrIncrement(author);
                            }
                        }
                    }
                }
                processedCount++;
                if (processedCount % 10000 == 0) {
                    System.out.println("Processed " + processedCount + " records...");
                }
            }
            
            // Get all authors and sort by frequency
            List<Author> allAuthors = authorHashTable.getAllAuthors();
            Collections.sort(allAuthors, (a, b) -> b.compareByFrequency(a));
            
            System.out.println("Total unique authors found: " + allAuthors.size());
            
            // Write top N authors to output file
            Part2OutputWriter.writeMostFrequentAuthors(allAuthors, topN);
            
            System.out.println("Experiment completed. Results written to output/saidaPart2.txt");
            System.out.println("Top " + Math.min(topN, allAuthors.size()) + " authors written to file.");
            
        } catch (IOException e) {
            System.err.println("Error reading files: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
