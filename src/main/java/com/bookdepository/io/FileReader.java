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
 * <p>The default layout, as documented in the project README, is:
 *
 * <pre>
 *   input/input.txt                          desired sample sizes
 *   data/dataset_simp_sem_descricao.csv      Book Depository records
 *   data/authors.csv                         Author metadata
 * </pre>
 *
 * <p>The {@code input.txt} file follows the format
 * "N\nvalue1\nvalue2\n...\nvalueN", i.e. the first non-blank line is the
 * count of subsequent size values. Each path can be overridden via the
 * {@code BD_INPUT_DIR} / {@code BD_DATA_DIR} environment variables, which
 * makes the experiments easy to wire from CI.
 */
public final class FileReader {

    private static final String DEFAULT_INPUT_DIR = "input";
    private static final String DEFAULT_DATA_DIR = "data";
    private static final String[] DATASET_CANDIDATES = {
        "dataset_simp_sem_descricao.csv",
        "dataset_simp.csv",
        "dataset.csv"
    };
    private static final String AUTHORS_FILE = "authors.csv";

    private FileReader() {
        // utility class
    }

    /**
     * Reads the configured sample sizes from {@code input/input.txt}.
     *
     * <p>The file format documented in the README is a leading count line
     * {@code N} followed by {@code N} integer size values. The leading count
     * is consumed and only the size values are returned. If the file's first
     * line is not consistent with the documented format (for example, when a
     * fixture omits the count) the parser falls back to treating every
     * numeric line as a size.
     *
     * @return list of integer sizes, one per value line
     * @throws IOException if the file cannot be read
     */
    public static List<Integer> readInputSizes() throws IOException {
        Path file = inputDir().resolve("input.txt");
        if (!Files.exists(file)) {
            return new ArrayList<>();
        }
        List<Integer> all = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(file, StandardCharsets.UTF_8)) {
            String line;
            while ((line = reader.readLine()) != null) {
                String trimmed = line.trim();
                if (trimmed.isEmpty()) {
                    continue;
                }
                try {
                    all.add(Integer.parseInt(trimmed));
                } catch (NumberFormatException ignored) {
                    // skip malformed line
                }
            }
        }
        if (all.isEmpty()) {
            return all;
        }
        // The documented format starts with a count line. If the first value
        // equals the number of remaining entries, treat it as the count and
        // drop it; otherwise return every line as-is.
        int first = all.get(0);
        if (first == all.size() - 1) {
            return new ArrayList<>(all.subList(1, all.size()));
        }
        return all;
    }

    /**
     * Reads all records from the Book Depository dataset CSV, if present.
     *
     * <p>The reader tries several filenames in the data directory in order:
     * {@code dataset_simp_sem_descricao.csv} (canonical), {@code dataset_simp.csv}
     * and {@code dataset.csv}. Missing files result in an empty list rather
     * than an exception so the experiment binaries remain runnable without
     * the (large) Kaggle dump.
     *
     * @return list of records
     * @throws IOException if reading the file fails
     */
    public static List<Record> readAllRecords() throws IOException {
        Path file = locateDataset();
        if (file == null) {
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
        Path file = dataDir().resolve(AUTHORS_FILE);
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
                String[] parts = splitCsv(line);
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
        String[] parts = splitCsv(line);
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
        if (parts.length > 5 && !parts[5].isEmpty()) {
            record.setCategories(new ArrayList<>(Arrays.asList(parts[5].split(","))));
        }
        if (parts.length > 6) {
            record.setIsbn10(parts[6].trim());
        }
        if (parts.length > 7) {
            record.setIsbn13(parts[7].trim());
        }
        return record;
    }

    /**
     * Splits a line on either ';' or ',' depending on which delimiter the
     * line uses. The Kaggle dump and the test fixtures both occur in the
     * wild, so the reader auto-detects per line. Quoted commas are not
     * supported, which is consistent with the unquoted Kaggle export.
     */
    private static String[] splitCsv(String line) {
        if (line.indexOf(';') >= 0) {
            return line.split(";", -1);
        }
        return line.split(",", -1);
    }

    private static Path locateDataset() {
        Path dir = dataDir();
        for (String candidate : DATASET_CANDIDATES) {
            Path file = dir.resolve(candidate);
            if (Files.exists(file)) {
                return file;
            }
        }
        return null;
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
