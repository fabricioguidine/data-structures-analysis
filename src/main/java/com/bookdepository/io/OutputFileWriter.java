package com.bookdepository.io;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

/**
 * Appends sorting experiment results to {@code output/output.txt}.
 */
public final class OutputFileWriter {

    private static final String DEFAULT_OUTPUT_DIR = "output";
    private static final String OUTPUT_FILE_NAME = "output.txt";

    private OutputFileWriter() {
        // utility class
    }

    /**
     * Removes any existing output file so subsequent writes start fresh.
     *
     * @throws IOException if the file cannot be deleted
     */
    public static void clearOutput() throws IOException {
        Path file = outputFile();
        if (Files.exists(file)) {
            Files.delete(file);
        }
    }

    /**
     * Appends one block per result to the output file.
     *
     * @param results        results to write
     * @param algorithmLabel label printed before the metrics
     * @throws IOException if the file cannot be written
     */
    public static void writeSortingResults(List<PerformanceResult> results, String algorithmLabel)
            throws IOException {
        Path file = outputFile();
        Files.createDirectories(file.getParent());
        try (BufferedWriter writer = Files.newBufferedWriter(
                file,
                StandardCharsets.UTF_8,
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND)) {
            for (PerformanceResult result : results) {
                writer.write(algorithmLabel + ":");
                writer.newLine();
                writer.write("  comparisons=" + result.getComparisons());
                writer.newLine();
                writer.write("  swaps=" + result.getSwaps());
                writer.newLine();
                writer.write("  time_ms=" + result.getExecutionTime());
                writer.newLine();
            }
        }
    }

    private static Path outputFile() {
        String override = System.getenv("BD_OUTPUT_DIR");
        Path dir = Paths.get(override == null || override.isEmpty() ? DEFAULT_OUTPUT_DIR : override);
        return dir.resolve(OUTPUT_FILE_NAME);
    }
}
