package com.bookdepository.io;

import com.bookdepository.model.Author;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

/**
 * Writes the most-frequent-authors report to {@code output/outputPart2.txt}.
 */
public final class Part2OutputWriter {

    private static final String DEFAULT_OUTPUT_DIR = "output";
    private static final String OUTPUT_FILE_NAME = "outputPart2.txt";

    private Part2OutputWriter() {
        // utility class
    }

    /**
     * Writes the top {@code topN} authors, ordered by descending frequency.
     *
     * @param authors authors sorted from most to least frequent
     * @param topN    maximum number of authors to write
     * @throws IOException if writing fails
     */
    public static void writeMostFrequentAuthors(List<Author> authors, int topN) throws IOException {
        Path file = outputFile();
        Files.createDirectories(file.getParent());
        int limit = Math.min(topN, authors == null ? 0 : authors.size());
        try (BufferedWriter writer = Files.newBufferedWriter(
                file,
                StandardCharsets.UTF_8,
                StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING)) {
            writer.write("Top " + limit + " authors by frequency:");
            writer.newLine();
            if (authors == null) {
                return;
            }
            for (int i = 0; i < limit; i++) {
                Author author = authors.get(i);
                writer.write((i + 1) + ". " + author.getName() + " (" + author.getFrequency() + ")");
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
