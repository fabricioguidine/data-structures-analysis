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
 *
 * <p>The on-disk format follows the convention used by the test fixture
 * {@code src/test/resources/test-output.txt}: the algorithm name is written
 * on its own line, followed by one comma-separated row per input size in
 * the form {@code size,comparisons,swaps,time_ms}. A blank line separates
 * algorithm blocks.
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
     * Appends one algorithm block to the output file.
     *
     * <p>The block starts with the algorithm label on its own line, followed
     * by {@code size,comparisons,swaps,time_ms} for each result, and finishes
     * with a trailing blank line.
     *
     * @param results        results to write, one per input size
     * @param algorithmLabel header written before the metrics
     * @throws IOException if the file cannot be written
     */
    public static void writeSortingResults(List<PerformanceResult> results, String algorithmLabel)
            throws IOException {
        if (results == null || algorithmLabel == null) {
            return;
        }
        Path file = outputFile();
        Files.createDirectories(file.getParent());
        try (BufferedWriter writer = Files.newBufferedWriter(
                file,
                StandardCharsets.UTF_8,
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND)) {
            writer.write(algorithmLabel);
            writer.newLine();
            for (PerformanceResult result : results) {
                writer.write(result.getSize() + ","
                        + result.getComparisons() + ","
                        + result.getSwaps() + ","
                        + result.getExecutionTime());
                writer.newLine();
            }
            writer.newLine();
        }
    }

    private static Path outputFile() {
        String override = System.getenv("BD_OUTPUT_DIR");
        Path dir = Paths.get(override == null || override.isEmpty() ? DEFAULT_OUTPUT_DIR : override);
        return dir.resolve(OUTPUT_FILE_NAME);
    }
}
