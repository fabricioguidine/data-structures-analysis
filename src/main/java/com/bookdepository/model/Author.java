package com.bookdepository.model;

/**
 * Represents an author from the dataset.
 * Contains author code, name, and frequency count.
 * 
 * @author Fabrício Guidine, Débora Duarte, Walkíria Garcia
 * @version 1.0
 */
public class Author {
    
    private String name;
    private int code;
    private int frequency;
    
    /**
     * Constructor for the Author class.
     * 
     * @param line String that represents a complete line read from the authors.csv file.
     */
    public Author(String line) {
        separateFields(line);
        this.frequency = 0;
    }
    
    /**
     * Receives a String parameter, identifies the fields in that String and separates them field by field.
     * 
     * @param str String that represents a complete line read from the dataset file.
     */
    private void separateFields(String str) {
        int n = str.length();
        String info = "";
        int position = 0;
        int count = 0;
        String[] fields = new String[2];
        
        try {
            while (position < n - 1 && count < 2) {
                if (str.charAt(position) == '"') {
                    position++;
                    while (position < n && str.charAt(position) != '"') {
                        info = info + str.charAt(position);
                        position++;
                    }
                    fields[count] = info;
                    count++;
                    info = "";
                }
                position++;
            }
            
            if (fields[0] == null || fields[0].isEmpty()) {
                this.code = 0;
            } else {
                try {
                    this.code = Integer.parseInt(fields[0]);
                } catch (NumberFormatException e) {
                    this.code = 0;
                }
            }
            
            if (fields[1] == null || fields[1].isEmpty()) {
                this.name = "Unknown Author";
            } else {
                this.name = fields[1];
            }
            
        } catch (StringIndexOutOfBoundsException ex) {
            System.out.println("Error parsing author: " + str);
            System.out.println("String length: " + str.length());
            System.out.println("Position: " + position);
            System.out.println(ex.getMessage());
            this.code = 0;
            this.name = "Unknown Author";
        }
    }
    
    /**
     * Sets the name attribute of the class.
     * 
     * @param name String that is the name to be inserted.
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Returns the author code.
     * 
     * @return Integer containing the author code.
     */
    public int getCode() {
        return this.code;
    }
    
    /**
     * Returns the author name.
     * 
     * @return String containing the author name.
     */
    public String getName() {
        return name;
    }
    
    /**
     * Returns the frequency (number of books by this author).
     * 
     * @return Integer that is the quantity of books written by the Author.
     */
    public int getFrequency() {
        return frequency;
    }
    
    /**
     * Increments the frequency by one (adds one more book by this author).
     */
    public void incrementFrequency() {
        this.frequency = this.frequency + 1;
    }
}

