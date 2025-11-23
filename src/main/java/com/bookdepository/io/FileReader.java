package com.bookdepository.io;

import com.bookdepository.model.Record;
import com.bookdepository.model.Author;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Utility class for reading CSV files and parsing records and authors.
 */
public class FileReader {
    
    private static final String DATA_DIR = "data/";
    private static final String RECORDS_FILE = DATA_DIR + "dataset_simp_sem_descricao.csv";
    private static final String AUTHORS_FILE = DATA_DIR + "authors.csv";

    /**
     * Reads input sizes from entrada.txt file.
     *
     * @return List of input sizes
     * @throws IOException If file cannot be read
     */
    public static List<Integer> readInputSizes() throws IOException {
        List<Integer> sizes = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new java.io.FileReader("input/entrada.txt"))) {
            String line = br.readLine();
            if (line != null) {
                int n = Integer.parseInt(line.trim());
                for (int i = 0; i < n; i++) {
                    line = br.readLine();
                    if (line != null) {
                        sizes.add(Integer.parseInt(line.trim()));
                    }
                }
            }
        }
        return sizes;
    }

    /**
     * Reads records from the CSV file.
     *
     * @param limit Maximum number of records to read (0 for all)
     * @return List of records
     * @throws IOException If file cannot be read
     */
    public static List<Record> readRecords(int limit) throws IOException {
        List<Record> records = new ArrayList<>();
        Map<String, Author> authorMap = readAuthorsMap();

        try (BufferedReader br = new BufferedReader(new java.io.FileReader(RECORDS_FILE))) {
            // Skip header line
            String line = br.readLine();
            
            int count = 0;
            while ((line = br.readLine()) != null && (limit == 0 || count < limit)) {
                Record record = parseRecord(line, authorMap);
                if (record != null) {
                    records.add(record);
                    count++;
                }
            }
        }
        return records;
    }

    /**
     * Reads all records from the CSV file.
     *
     * @return List of all records
     * @throws IOException If file cannot be read
     */
    public static List<Record> readAllRecords() throws IOException {
        return readRecords(0);
    }

    /**
     * Reads authors from the CSV file into a map.
     *
     * @return Map of author ID to Author object
     * @throws IOException If file cannot be read
     */
    public static Map<String, Author> readAuthorsMap() throws IOException {
        Map<String, Author> authorMap = new HashMap<>();
        
        try (BufferedReader br = new BufferedReader(new java.io.FileReader(AUTHORS_FILE))) {
            // Skip header line
            String line = br.readLine();
            
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                if (parts.length >= 2) {
                    String id = parts[0].trim().replaceAll("\"", "");
                    String name = parts[1].trim().replaceAll("\"", "");
                    if (!id.isEmpty() && !name.isEmpty()) {
                        authorMap.put(id, new Author(id, name));
                    }
                }
            }
        }
        return authorMap;
    }

    /**
     * Parses a single line from the records CSV file into a Record object.
     *
     * @param line        CSV line
     * @param authorMap   Map of authors by ID
     * @return Record object or null if parsing fails
     */
    private static Record parseRecord(String line, Map<String, Author> authorMap) {
        try {
            String[] parts = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
            if (parts.length < 10) {
                return null;
            }

            String id = cleanField(parts[0]);
            String title = cleanField(parts[1]);
            
            // Parse authors (field 2 - list of author IDs)
            List<String> authorIds = new ArrayList<>();
            String authorsStr = cleanField(parts[2]);
            if (!authorsStr.isEmpty() && authorsStr.startsWith("[") && authorsStr.endsWith("]")) {
                authorsStr = authorsStr.substring(1, authorsStr.length() - 1);
                String[] authorArray = authorsStr.split(",");
                for (String authorId : authorArray) {
                    String cleaned = authorId.trim().replaceAll("[\"'\\[\\]]", "");
                    if (!cleaned.isEmpty()) {
                        authorIds.add(cleaned);
                    }
                }
            }

            String isbn10 = cleanField(parts[3]);
            String isbn13 = cleanField(parts[4]);
            String edition = cleanField(parts[5]);
            
            // Parse categories (field 6)
            List<String> categories = new ArrayList<>();
            String categoriesStr = cleanField(parts[6]);
            if (!categoriesStr.isEmpty() && categoriesStr.startsWith("[") && categoriesStr.endsWith("]")) {
                categoriesStr = categoriesStr.substring(1, categoriesStr.length() - 1);
                String[] categoryArray = categoriesStr.split(",");
                for (String category : categoryArray) {
                    String cleaned = category.trim().replaceAll("[\"'\\[\\]]", "");
                    if (!cleaned.isEmpty()) {
                        categories.add(cleaned);
                    }
                }
            }

            float ratingAvg = 0.0f;
            try {
                String ratingStr = cleanField(parts[7]);
                if (!ratingStr.isEmpty()) {
                    ratingAvg = Float.parseFloat(ratingStr);
                }
            } catch (NumberFormatException e) {
                ratingAvg = 0.0f;
            }

            int ratingCount = 0;
            try {
                String countStr = cleanField(parts[8]);
                if (!countStr.isEmpty()) {
                    ratingCount = Integer.parseInt(countStr);
                }
            } catch (NumberFormatException e) {
                ratingCount = 0;
            }

            int bestsellersRank = 0;
            try {
                String rankStr = cleanField(parts[9]);
                if (!rankStr.isEmpty()) {
                    bestsellersRank = Integer.parseInt(rankStr);
                }
            } catch (NumberFormatException e) {
                bestsellersRank = 0;
            }

            return new Record(id, title, authorIds, isbn10, isbn13, edition,
                            categories, ratingAvg, ratingCount, bestsellersRank);
        } catch (Exception e) {
            // Skip malformed lines
            return null;
        }
    }

    /**
     * Cleans a CSV field by removing quotes and trimming.
     *
     * @param field Raw field value
     * @return Cleaned field value
     */
    private static String cleanField(String field) {
        if (field == null) {
            return "";
        }
        return field.trim().replaceAll("^\"|\"$", "");
    }
}
