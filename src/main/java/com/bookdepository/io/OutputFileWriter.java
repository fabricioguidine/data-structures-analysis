package com.bookdepository.io;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Utility class for writing output files for Part I (Sorting algorithms).
 */
public class OutputFileWriter {
    private static final String OUTPUT_FILE = "output/saida.txt";

    /**
     * Writes sorting results to the output file.
     *
     * @param results List of performance results for each test case
     * @param algorithmName Name of the algorithm (QuickSort or HeapSort)
     * @throws IOException If file cannot be written
     */
    public static void writeSortingResults(java.util.List<PerformanceResult> results, String algorithmName) throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(OUTPUT_FILE, true))) {
            pw.println(algorithmName + ":");
            for (PerformanceResult result : results) {
                pw.println(result.toString());
            }
            pw.println();
        }
    }

    /**
     * Clears the output file before writing new results.
     *
     * @throws IOException If file cannot be written
     */
    public static void clearOutput() throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(OUTPUT_FILE))) {
            // Clear file
        }
    }
}
