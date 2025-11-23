package com.bookdepository.io;

import com.bookdepository.model.Record;
import com.bookdepository.model.Author;
import java.io.*;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Utility class for reading data from CSV files.
 * 
 * @author Fabrício Guidine, Débora Duarte, Walkíria Garcia
 * @version 1.0
 */
public class FileReader {
    
    /**
     * Reads input file and returns an array of integers representing test sizes.
     * The input file format should be:
     * First line: N (number of test cases)
     * Next N lines: one value per line
     * 
     * @param filename Name of the input file (e.g., "entrada.txt").
     * @return Array of integers where first element is the count, followed by the values.
     * @throws IOException if the file cannot be read.
     */
    public static int[] readInputFile(String filename) throws IOException {
        int count;
        int index = 1;
        int[] entries;
        
        FileInputStream input = new FileInputStream(filename);
        InputStreamReader formattedInput = new InputStreamReader(input);
        BufferedReader bufferedInput = new BufferedReader(formattedInput);
        
        String line = bufferedInput.readLine();
        count = Integer.parseInt(line);
        entries = new int[count + 1];
        entries[0] = count;
        
        while (line != null && index <= count) {
            line = bufferedInput.readLine();
            if (line != null && !line.trim().isEmpty()) {
                entries[index] = Integer.parseInt(line.trim());
                index++;
            }
        }
        
        input.close();
        return entries;
    }
    
    /**
     * Loads a random number of records from the dataset file.
     * 
     * @param records Array of Record objects where data will be stored.
     * @param count Integer that represents the size of the Record array.
     * @param filename Name of the CSV file to read from (e.g., "dataset_simp_sem_descricao.csv").
     */
    public static void loadRandomRecords(Record[] records, int count, String filename) {
        try {
            RandomAccessFile raf = new RandomAccessFile(filename, "r");
            long position = ThreadLocalRandom.current().nextLong(raf.length());
            raf.seek(position);
            String line = "";
            String[] lines = new String[count + count / 2];
            
            // Skip current line (might be incomplete)
            raf.readLine();
            
            // Read extra lines to handle multi-line records
            for (int i = 0; i < count + count / 2; i++) {
                if (raf.getFilePointer() == raf.length()) {
                    raf.seek(0);
                }
                lines[i] = raf.readLine();
            }
            
            int i = 0, j = 0;
            
            while (i < count && j < count + count / 2) {
                if (lines[j] == null) {
                    j++;
                    continue;
                }
                
                line = lines[j];
                int last = line.length() - 1;
                
                // Check if the line is complete (ends with quote) or needs next line
                if (last >= 0 && line.charAt(last) != '"' && j + 1 < lines.length) {
                    String line2 = lines[j + 1];
                    j++;
                    String combined = line + line2;
                    if (combined != null && !combined.trim().isEmpty()) {
                        Record record = new Record(combined);
                        records[i] = record;
                        i++;
                    }
                    j++;
                } else {
                    if (line != null && !line.trim().isEmpty()) {
                        Record record = new Record(line);
                        records[i] = record;
                        i++;
                    }
                    j++;
                }
            }
            
            raf.close();
        } catch (FileNotFoundException ex) {
            System.out.println("File not found: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("IO error: " + ex.getMessage());
        }
    }
    
    /**
     * Reads authors from the authors.csv file.
     * 
     * @param filename Name of the CSV file to read from (e.g., "authors.csv").
     * @return ArrayList of Author objects.
     */
    public static ArrayList<Author> readAuthors(String filename) {
        try {
            ArrayList<Author> authors;
            FileInputStream input = new FileInputStream(filename);
            InputStreamReader formattedInput = new InputStreamReader(input);
            BufferedReader bufferedInput = new BufferedReader(formattedInput);
            authors = new ArrayList<>();
            
            // Skip header
            String line = bufferedInput.readLine();
            
            while (line != null) {
                line = bufferedInput.readLine();
                if (line != null && !line.trim().isEmpty()) {
                    Author author = new Author(line);
                    authors.add(author);
                }
            }
            
            input.close();
            return authors;
        } catch (FileNotFoundException ex) {
            System.out.println("Authors file not found!");
            return new ArrayList<>();
        } catch (IOException ex) {
            System.out.println("IO error reading authors: " + ex.getMessage());
            return new ArrayList<>();
        }
    }
    
    /**
     * Calculates the next prime number greater than or equal to the given number.
     * 
     * @param num Starting number.
     * @return Next prime number.
     */
    public static int getNextPrime(int num) {
        while (!isPrime(num)) {
            num++;
        }
        return num;
    }
    
    /**
     * Checks if a number is prime.
     * 
     * @param n Number to check.
     * @return true if prime, false otherwise.
     */
    private static boolean isPrime(int n) {
        if (n < 2) return false;
        if (n == 2) return true;
        if (n % 2 == 0) return false;
        
        for (int j = 3; j * j <= n; j += 2) {
            if (n % j == 0) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Calculates the next power of 2 greater than or equal to the given number.
     * 
     * @param value Starting value.
     * @return Next power of 2.
     */
    public static int getNextPowerOf2(int value) {
        while (!isPowerOf2(value)) {
            value++;
        }
        return value;
    }
    
    /**
     * Checks if a number is a power of 2.
     * 
     * @param v Number to check.
     * @return true if power of 2, false otherwise.
     */
    private static boolean isPowerOf2(int v) {
        return v > 0 && (v & (v - 1)) == 0;
    }
}

