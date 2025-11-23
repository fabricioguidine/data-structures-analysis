package com.bookdepository.model;

import java.util.List;

/**
 * Represents a book record from the Book Depository dataset.
 * This class models a book with all its attributes including
 * authors, ISBN, ratings, categories, and bestseller ranking.
 */
public class Record {
    private String id;
    private String title;
    private List<String> authors;
    private String isbn10;
    private String isbn13;
    private String edition;
    private List<String> categories;
    private float ratingAvg;
    private int ratingCount;
    private int bestsellersRank;

    /**
     * Default constructor.
     */
    public Record() {
    }

    /**
     * Constructor with all fields.
     *
     * @param id              Unique book identifier
     * @param title           Book title
     * @param authors         List of author IDs
     * @param isbn10          ISBN-10
     * @param isbn13          ISBN-13
     * @param edition         Edition information
     * @param categories      List of book categories
     * @param ratingAvg       Average rating (0-5)
     * @param ratingCount     Number of ratings
     * @param bestsellersRank Bestseller ranking
     */
    public Record(String id, String title, List<String> authors, String isbn10,
                  String isbn13, String edition, List<String> categories,
                  float ratingAvg, int ratingCount, int bestsellersRank) {
        this.id = id;
        this.title = title;
        this.authors = authors;
        this.isbn10 = isbn10;
        this.isbn13 = isbn13;
        this.edition = edition;
        this.categories = categories;
        this.ratingAvg = ratingAvg;
        this.ratingCount = ratingCount;
        this.bestsellersRank = bestsellersRank;
    }

    // Getters and Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public String getIsbn10() {
        return isbn10;
    }

    public void setIsbn10(String isbn10) {
        this.isbn10 = isbn10;
    }

    public String getIsbn13() {
        return isbn13;
    }

    public void setIsbn13(String isbn13) {
        this.isbn13 = isbn13;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public float getRatingAvg() {
        return ratingAvg;
    }

    public void setRatingAvg(float ratingAvg) {
        this.ratingAvg = ratingAvg;
    }

    public int getRatingCount() {
        return ratingCount;
    }

    public void setRatingCount(int ratingCount) {
        this.ratingCount = ratingCount;
    }

    public int getBestsellersRank() {
        return bestsellersRank;
    }

    public void setBestsellersRank(int bestsellersRank) {
        this.bestsellersRank = bestsellersRank;
    }

    @Override
    public String toString() {
        return "Record{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", isbn13='" + isbn13 + '\'' +
                ", ratingAvg=" + ratingAvg +
                ", bestsellersRank=" + bestsellersRank +
                '}';
    }

    /**
     * Compares two records by their bestseller rank.
     * Used for sorting algorithms.
     *
     * @param other The other record to compare
     * @return Negative if this rank < other rank, 0 if equal, positive otherwise
     */
    public int compareByRank(Record other) {
        if (this.bestsellersRank == 0 && other.bestsellersRank == 0) {
            return 0;
        }
        if (this.bestsellersRank == 0) {
            return 1; // Records without rank go to the end
        }
        if (other.bestsellersRank == 0) {
            return -1;
        }
        return Integer.compare(this.bestsellersRank, other.bestsellersRank);
    }
}
