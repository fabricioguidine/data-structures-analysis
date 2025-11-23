package com.bookdepository.model;

import java.util.ArrayList;

/**
 * Represents a book record from the dataset.
 * Contains values and methods for a book record with authors, categories, ratings, etc.
 * 
 * @author Fabrício Guidine, Débora Duarte, Walkíria Garcia
 * @version 1.0
 */
public class Record {
    
    private ArrayList<String> authors = new ArrayList<>();
    private int bestsellersRank;
    private ArrayList<String> categories = new ArrayList<>();
    private String edition;
    private String id;
    private String isbn10;
    private String isbn13;
    private float ratingAvg;
    private int ratingCount;
    private String title;
    
    /**
     * Empty constructor for the Record class.
     */
    public Record() {}
    
    /**
     * Receives a String parameter, identifies the fields in that String and separates them field by field.
     * 
     * @param str String that represents a complete line read from the dataset file.
     * @param fields Array of Strings with 10 positions, each referring to an attribute of the Record class.
     */
    private void firstSeparation(String str, String[] fields) {
        int n = str.length();
        String info = "";
        int position = 0;
        int fieldIndex = 0;
        
        try {
            while (position < n - 1 && fieldIndex < 10) {
                if (str.charAt(position) == '"') {
                    position++;
                    while (position < n && str.charAt(position) != '"') {
                        info = info + str.charAt(position);
                        position++;
                    }
                    fields[fieldIndex] = info;
                    info = "";
                    fieldIndex++;
                }
                position++;
            }
        } catch (StringIndexOutOfBoundsException ex) {
            System.out.println("Error parsing record: " + str);
            System.out.println("String length: " + str.length());
            System.out.println("Position: " + position);
            System.out.println(ex.getMessage());
        }
    }
    
    /**
     * Handles fields that need a second separation (for list fields like authors and categories).
     * 
     * @param fieldInfo String to be separated.
     * @param listInfo ArrayList of String that represents an attribute of the Record class.
     */
    private void stringToList(String fieldInfo, ArrayList<String> listInfo) {
        int n = fieldInfo.length();
        String info = "";
        
        for (int i = 1; i < n; i++) {
            while (i < n && fieldInfo.charAt(i) != ',' && fieldInfo.charAt(i) != ']' && fieldInfo.charAt(i) != ' ') {
                info = info + fieldInfo.charAt(i);
                i++;
            }
            if (!info.isEmpty()) {
                listInfo.add(info);
            }
            info = "";
        }
    }
    
    /**
     * Constructor for the Record class.
     * 
     * @param recordInfo String that represents a complete line read from the dataset file.
     */
    public Record(String recordInfo) {
        if (recordInfo != null) {
            String[] fields = new String[10];
            firstSeparation(recordInfo, fields);
            
            setAuthors(fields[0]);
            setBestsellersRank(fields[1]);
            setCategories(fields[2]);
            setEdition(fields[3]);
            setId(fields[4]);
            setIsbn10(fields[5]);
            setIsbn13(fields[6]);
            setRatingAvg(fields[7]);
            setRatingCount(fields[8]);
            setTitle(fields[9]);
        } else {
            System.out.println("Record info is null");
        }
    }
    
    /**
     * Returns the authors field.
     * 
     * @return authors as an ArrayList of String.
     */
    public ArrayList<String> getAuthors() {
        return authors;
    }
    
    /**
     * Returns the bestsellers rank field.
     * 
     * @return bestsellersRank as an Integer.
     */
    public int getBestsellersRank() {
        return bestsellersRank;
    }
    
    /**
     * Returns the categories field.
     * 
     * @return categories as an ArrayList of String.
     */
    public ArrayList<String> getCategories() {
        return categories;
    }
    
    /**
     * Returns the edition field.
     * 
     * @return edition as a String.
     */
    public String getEdition() {
        return edition;
    }
    
    /**
     * Returns the id field.
     * 
     * @return id as a String.
     */
    public String getId() {
        return id;
    }
    
    /**
     * Returns the ISBN-10 field.
     * 
     * @return isbn10 as a String.
     */
    public String getIsbn10() {
        return isbn10;
    }
    
    /**
     * Returns the ISBN-13 field.
     * 
     * @return isbn13 as a String.
     */
    public String getIsbn13() {
        return isbn13;
    }
    
    /**
     * Returns the average rating field (0 to 5).
     * 
     * @return ratingAvg as a float.
     */
    public float getRatingAvg() {
        return ratingAvg;
    }
    
    /**
     * Returns the rating count field.
     * 
     * @return ratingCount as an int.
     */
    public int getRatingCount() {
        return ratingCount;
    }
    
    /**
     * Returns the title field.
     * 
     * @return title as a String.
     */
    public String getTitle() {
        return title;
    }
    
    /**
     * Converts the record's ID to an integer.
     * 
     * @return the ID as an integer, or -1 if conversion fails.
     */
    public int intGetId() {
        try {
            return Integer.parseInt(id);
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    
    /**
     * Sets the authors field with the value received as parameter.
     * Sets empty author fields to "-1" to handle possible exceptions.
     * 
     * @param newAuthor String that receives the author field.
     */
    private void setAuthors(String newAuthor) {
        if (newAuthor == null || newAuthor.isEmpty()) {
            this.authors.add("-1");
        } else {
            stringToList(newAuthor, this.authors);
        }
    }
    
    /**
     * Sets the bestsellers_rank field with the value received as parameter.
     * Sets empty bestsellers_rank fields to 0 to handle possible exceptions.
     * 
     * @param bestsellersRank String that receives the bestsellers field.
     */
    private void setBestsellersRank(String bestsellersRank) {
        if (bestsellersRank == null || bestsellersRank.length() < 1) {
            this.bestsellersRank = 0;
        } else {
            String info = "";
            for (int b = 0; b < bestsellersRank.length(); b++) {
                if (bestsellersRank.charAt(b) != ',' && bestsellersRank.charAt(b) != ' ') {
                    info = info + bestsellersRank.charAt(b);
                }
            }
            try {
                this.bestsellersRank = Integer.parseInt(info);
            } catch (NumberFormatException e) {
                this.bestsellersRank = 0;
            }
        }
    }
    
    /**
     * Sets the categories field with the value received as parameter.
     * Sets empty category fields to "-1" to handle possible exceptions.
     * 
     * @param newCategory String that receives the category field.
     */
    private void setCategories(String newCategory) {
        if (newCategory == null || newCategory.length() < 1) {
            this.categories.add("-1");
        } else {
            stringToList(newCategory, this.categories);
        }
    }
    
    /**
     * Sets the edition field with the value received as parameter.
     * Sets empty edition fields to "-1" to handle possible exceptions.
     * 
     * @param edition String that receives the edition field.
     */
    private void setEdition(String edition) {
        if (edition == null || edition.isEmpty()) {
            this.edition = "-1";
        } else {
            this.edition = edition;
        }
    }
    
    /**
     * Sets the id field with the value received as parameter.
     * Sets empty id fields to "-1" to handle possible exceptions.
     * 
     * @param id String that receives the id field.
     */
    private void setId(String id) {
        if (id == null || id.length() < 1) {
            this.id = "-1";
        } else {
            this.id = id;
        }
    }
    
    /**
     * Sets the isbn10 field with the value received as parameter.
     * Sets empty isbn-10 fields to "-1" to handle possible exceptions.
     * 
     * @param isbn10 String that receives the isbn-10 field.
     */
    private void setIsbn10(String isbn10) {
        if (isbn10 == null || isbn10.length() < 1) {
            this.isbn10 = "-1";
        } else {
            this.isbn10 = isbn10;
        }
    }
    
    /**
     * Sets the isbn13 field with the value received as parameter.
     * Sets empty isbn13 fields to "-1" to handle possible exceptions.
     * 
     * @param isbn13 String that receives the isbn13 field.
     */
    private void setIsbn13(String isbn13) {
        if (isbn13 == null || isbn13.length() < 1) {
            this.isbn13 = "-1";
        } else {
            this.isbn13 = isbn13;
        }
    }
    
    /**
     * Sets the rating_avg field with the value received as parameter.
     * Sets empty average rating fields to 0 to handle possible exceptions.
     * 
     * @param ratingAvg String that receives the average rating field.
     */
    private void setRatingAvg(String ratingAvg) {
        if (ratingAvg == null || ratingAvg.length() < 1) {
            this.ratingAvg = 0;
        } else {
            try {
                this.ratingAvg = Float.parseFloat(ratingAvg);
            } catch (NumberFormatException e) {
                this.ratingAvg = 0;
            }
        }
    }
    
    /**
     * Sets the rating_count field with the value received as parameter.
     * Sets empty rating count fields to 0 to handle possible exceptions.
     * 
     * @param ratingCount String that receives the rating count field.
     */
    private void setRatingCount(String ratingCount) {
        if (ratingCount == null || ratingCount.length() < 1) {
            this.ratingCount = 0;
        } else {
            try {
                this.ratingCount = Integer.parseInt(ratingCount);
            } catch (NumberFormatException e) {
                this.ratingCount = 0;
            }
        }
    }
    
    /**
     * Sets the title field with the value received as parameter.
     * Sets empty title fields to "-1" to handle possible exceptions.
     * 
     * @param title String that receives the title field.
     */
    private void setTitle(String title) {
        if (title == null || title.isEmpty()) {
            this.title = "-1";
        } else {
            this.title = title;
        }
    }
}

