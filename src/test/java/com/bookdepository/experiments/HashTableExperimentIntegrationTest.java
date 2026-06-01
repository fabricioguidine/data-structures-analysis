package com.bookdepository.experiments;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bookdepository.io.Part2OutputWriter;
import com.bookdepository.model.Author;
import com.bookdepository.structures.hashtable.AuthorHashTable;
import com.bookdepository.test.ResourceLoader;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * End-to-end test for the Part&nbsp;II author-frequency pipeline.
 *
 * <p>Drives the same flow as {@link HashTableExperiment}: parse the records and
 * authors CSV fixtures from the classpath, count author frequencies in the
 * {@link AuthorHashTable}, rank the authors, and write the ranked report via
 * {@link Part2OutputWriter}. The output is then read back from disk and
 * asserted. All paths are classpath/relative and UTF-8 so the test runs
 * identically on Linux, macOS and Windows.
 */
@DisplayName("HashTable Experiment End-to-End Pipeline")
class HashTableExperimentIntegrationTest {

    private java.nio.file.Path outputFile;

    @BeforeEach
    void cleanOutput() throws IOException {
        // Part2OutputWriter resolves output/outputPart2.txt relative to the
        // working directory unless BD_OUTPUT_DIR is set; remove any stale file.
        outputFile = java.nio.file.Paths.get("output", "outputPart2.txt");
        java.nio.file.Files.deleteIfExists(outputFile);
    }

    @AfterEach
    void removeOutput() throws IOException {
        java.nio.file.Files.deleteIfExists(outputFile);
    }

    @Test
    @DisplayName("Counts, ranks and persists the most frequent authors end-to-end")
    void rankAndWriteMostFrequentAuthors() throws IOException {
        Map<String, Author> authorMap = loadAuthors();
        List<String[]> records = loadRecordAuthorIds();

        AuthorHashTable table = new AuthorHashTable(authorMap.size());
        for (String[] authorIds : records) {
            for (String authorId : authorIds) {
                Author author = authorMap.get(authorId);
                if (author != null) {
                    table.insertOrIncrement(author);
                }
            }
        }

        // A1 appears 3x, A2 3x, A3 2x, A4 1x, A5 1x in the fixture.
        List<Author> ranked = table.getAllAuthors();
        ranked.sort((a, b) -> b.compareByFrequency(a));

        assertThat(ranked).extracting(Author::getId).containsExactlyInAnyOrder("A1", "A2", "A3", "A4", "A5");
        assertThat(table.find("A1").getFrequency()).isEqualTo(3);
        assertThat(table.find("A2").getFrequency()).isEqualTo(3);
        assertThat(table.find("A3").getFrequency()).isEqualTo(2);
        assertThat(table.find("A4").getFrequency()).isEqualTo(1);
        assertThat(table.find("A5").getFrequency()).isEqualTo(1);
        assertThat(ranked.get(0).getFrequency()).isEqualTo(3);

        Part2OutputWriter.writeMostFrequentAuthors(ranked, 3);

        assertThat(java.nio.file.Files.exists(outputFile)).isTrue();
        List<String> lines = java.nio.file.Files.readAllLines(outputFile, java.nio.charset.StandardCharsets.UTF_8);
        assertThat(lines).hasSize(4);
        assertThat(lines.get(0)).isEqualTo("Top 3 authors by frequency:");
        // Top two slots are the frequency-3 authors (order between ties is
        // unspecified); the third slot is the frequency-2 author A3.
        assertThat(lines.get(1)).contains("(3)");
        assertThat(lines.get(2)).contains("(3)");
        assertThat(lines.get(3)).contains("(2)");
    }

    private Map<String, Author> loadAuthors() throws IOException {
        Map<String, Author> authors = new HashMap<>();
        String[] lines = ResourceLoader.readResourceAsLines(ResourceLoader.Paths.SAMPLE_AUTHORS);
        for (int i = 1; i < lines.length; i++) {
            String line = lines[i].trim();
            if (line.isEmpty()) {
                continue;
            }
            String[] parts = line.split(",", 2);
            String id = parts[0].trim();
            String name = parts.length > 1 ? unquote(parts[1].trim()) : "";
            authors.put(id, new Author(id, name));
        }
        return authors;
    }

    private List<String[]> loadRecordAuthorIds() throws IOException {
        List<String[]> result = new ArrayList<>();
        String[] lines = ResourceLoader.readResourceAsLines(ResourceLoader.Paths.SAMPLE_RECORDS);
        // Header: id,title,author_id,bestsellers_rank,price,rating
        for (int i = 1; i < lines.length; i++) {
            String line = lines[i].trim();
            if (line.isEmpty()) {
                continue;
            }
            String[] parts = splitCsvRespectingQuotes(line);
            if (parts.length < 3) {
                continue;
            }
            result.add(new String[] {unquote(parts[2].trim())});
        }
        return result;
    }

    private static String[] splitCsvRespectingQuotes(String line) {
        List<String> fields = new ArrayList<>();
        StringBuilder current = new StringBuilder();
        boolean inQuotes = false;
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (c == '"') {
                inQuotes = !inQuotes;
            } else if (c == ',' && !inQuotes) {
                fields.add(current.toString());
                current.setLength(0);
            } else {
                current.append(c);
            }
        }
        fields.add(current.toString());
        return fields.toArray(new String[0]);
    }

    private static String unquote(String value) {
        if (value.length() >= 2 && value.charAt(0) == '"' && value.charAt(value.length() - 1) == '"') {
            return value.substring(1, value.length() - 1);
        }
        return value;
    }
}
