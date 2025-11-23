package com.bookdepository.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

/**
 * Utility class for loading test resources.
 * Provides helper methods to access files from src/test/resources.
 */
public class ResourceLoader {

    /**
     * Loads a resource file as an InputStream.
     *
     * @param resourcePath The path to the resource (e.g., "/test-input.txt")
     * @return InputStream for the resource, or null if not found
     */
    public static InputStream getResourceAsStream(String resourcePath) {
        return ResourceLoader.class.getResourceAsStream(resourcePath);
    }

    /**
     * Reads a resource file as a string.
     *
     * @param resourcePath The path to the resource (e.g., "/test-input.txt")
     * @return The content of the resource file as a string
     * @throws IOException if the resource cannot be read
     */
    public static String readResourceAsString(String resourcePath) throws IOException {
        InputStream inputStream = getResourceAsStream(resourcePath);
        if (inputStream == null) {
            throw new IOException("Resource not found: " + resourcePath);
        }
        
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
            return content.toString();
        }
    }

    /**
     * Loads a properties file from resources.
     *
     * @param resourcePath The path to the properties file (e.g., "/test-config.properties")
     * @return Properties object loaded from the resource
     * @throws IOException if the properties file cannot be loaded
     */
    public static Properties loadProperties(String resourcePath) throws IOException {
        Properties properties = new Properties();
        InputStream inputStream = getResourceAsStream(resourcePath);
        if (inputStream == null) {
            throw new IOException("Properties resource not found: " + resourcePath);
        }
        
        try (InputStream is = inputStream) {
            properties.load(is);
        }
        return properties;
    }

    /**
     * Reads a resource file line by line.
     *
     * @param resourcePath The path to the resource (e.g., "/test-input.txt")
     * @return Array of lines from the resource file
     * @throws IOException if the resource cannot be read
     */
    public static String[] readResourceAsLines(String resourcePath) throws IOException {
        String content = readResourceAsString(resourcePath);
        return content.split("\n");
    }

    /**
     * Checks if a resource exists.
     *
     * @param resourcePath The path to the resource
     * @return true if the resource exists, false otherwise
     */
    public static boolean resourceExists(String resourcePath) {
        return getResourceAsStream(resourcePath) != null;
    }

    /**
     * Common resource paths.
     */
    public static class Paths {
        public static final String TEST_INPUT = "/test-input.txt";
        public static final String SAMPLE_RECORDS = "/sample-records.csv";
        public static final String SAMPLE_AUTHORS = "/sample-authors.csv";
        public static final String TEST_CONFIG = "/test-config.properties";
        public static final String TEST_OUTPUT = "/test-output.txt";
    }
}

