package com.bookdepository.io;

import com.bookdepository.model.Author;
import com.bookdepository.model.Record;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Reads experiment inputs from the working directory.
 *
 * <p>The default layout expected by the experiments is:
 *
 * <pre>
 *   input/input.txt         desired sample sizes, one integer per line
 *   data/dataset.csv        Book Depository records, semicolon separated
 *   data/authors.csv        Author metadata, semicolon separated
 * </pre>
 *
 * Each path can be overridden via the {@code BD_INPUT_DIR} / {@code BD_DATA_DIR}
 * environment variables, which makes the experiments easy to wire from CI.
 */
public final class FileReader {

    private static final String DEFAULT_INPUT_DIR = "input";
    private static final String DEFAULT_DATA_DIR = "data";

    private FileReader() {
        // utility class
    }

    /**
     * Reads the configured sample sizes from {@code input/input.txt}.
     *
     * @return list of integers, one per non-blank line
     * @throws IOException if the file cannot be read
     */
    public static List<Integer> readInputSizes() throws IOException {
        Path file = inputDir().resolve("input.txt");
        if (!Files.exists(file)) {
            return new ArrayList<>();
        }
        List<Integer> sizes = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(file, StandardCharsets.UTF_8)) {
            String line;
            while ((line = reader.readLine()) != null) {
                String trimmed = line.trim();
                if (trimmed.isEmpty()) {
                    continue;
                }
                try {
                    sizes.add(Integer.parseInt(trimmed));
                } catch (NumberFormatException ignored) {
                    // skip malformed line
                }
            }
        }
        return sizes;
    }

    /**
     * Reads all records from {@code data/dataset.csv}, if present.
     *
     * <p>Missing files result in an empty list rather than an exception so that
     * the experiment binaries remain runnable without the (large) Kaggle dump.
     *
     * @return list of records
     * @throws IOException if reading the file fails
     */
    public static List<Record> readAllRecords() throws IOException {
        Path file = dataDir().resolve("dataset.csv");
        if (!Files.exists(file)) {
            return new ArrayList<>();
        }
        List<Record> records = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(file, StandardCharsets.UTF_8)) {
            String header = reader.readLine();
            if (header == null) {
                return records;
            }
            String line;
            while ((line = reader.readLine()) != null) {
                Record record = parseRecord(line);
                if (record != null) {
                    records.add(record);
                }
            }
        }
        return records;
    }

    /**
     * Reads the authors lookup table from {@code data/authors.csv}.
     *
     * @return map from author id to {@link Author}, possibly empty
     * @throws IOException if reading the file fails
     */
    public static Map<String, Author> readAuthorsMap() throws IOException {
        Path file = dataDir().resolve("authors.csv");
        Map<String, Author> authors = new HashMap<>();
        if (!Files.exists(file)) {
            return authors;
        }
        try (BufferedReader reader = Files.newBufferedReader(file, StandardCharsets.UTF_8)) {
            String header = reader.readLine();
            if (header == null) {
                return authors;
            }
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";", -1);
                if (parts.length < 2) {
                    continue;
                }
                Author author = new Author(parts[0].trim(), parts[1].trim());
                authors.put(author.getId(), author);
            }
        }
        return authors;
    }

    private static Record parseRecord(String line) {
        String[] parts = line.split(";", -1);
        if (parts.length < 3) {
            return null;
        }
        Record record = new Record();
        record.setId(parts[0].trim());
        record.setTitle(parts[1].trim());
        try {
            record.setBestsellersRank(Integer.parseInt(parts[2].trim()));
        } catch (NumberFormatException e) {
            record.setBestsellersRank(0);
        }
        if (parts.length > 3) {
            try {
                record.setPrice(Double.parseDouble(parts[3].trim()));
            } catch (NumberFormatException ignored) {
                record.setPrice(0.0);
            }
        }
        if (parts.length > 4 && !parts[4].isEmpty()) {
            record.setAuthors(new ArrayList<>(Arrays.asList(parts[4].split(","))));
        }
        return record;
    }

    private static Path inputDir() {
        String override = System.getenv("BD_INPUT_DIR");
        return Paths.get(override == null || override.isEmpty() ? DEFAULT_INPUT_DIR : override);
    }

    private static Path dataDir() {
        String override = System.getenv("BD_DATA_DIR");
        return Paths.get(override == null || override.isEmpty() ? DEFAULT_DATA_DIR : override);
    }
}
