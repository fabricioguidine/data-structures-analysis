package com.bookdepository.io;

import com.bookdepository.model.Author;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Utility class for writing output files for Part II (Hash tables - most frequent authors).
 */
public class Part2OutputWriter {
    private static final String OUTPUT_FILE = "output/saidaPart2.txt";

    /**
     * Writes the most frequent authors to the output file.
     *
     * @param authors List of authors sorted by frequency (descending)
     * @param topN Number of top authors to write
     * @throws IOException If file cannot be written
     */
    public static void writeMostFrequentAuthors(List<Author> authors, int topN) throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(OUTPUT_FILE))) {
            int count = Math.min(topN, authors.size());
            for (int i = 0; i < count; i++) {
                Author author = authors.get(i);
                pw.println((i + 1) + ". " + author.getName() + " - " + author.getFrequency() + " works");
            }
        }
    }
}
