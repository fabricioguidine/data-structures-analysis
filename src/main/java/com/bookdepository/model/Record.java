package com.bookdepository.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Book Depository record model.
 *
 * <p>Represents a single book entry from the Book Depository dataset. The
 * fields mirror the dataset schema documented in the project README and the
 * LaTeX report (Section "Dataset"), which lists title, ISBN, categories,
 * bestseller rank and review/rating information as the relevant attributes.
 */
public class Record {

    private String id;
    private String title;
    private String isbn10;
    private String isbn13;
    private String edition;
    private int bestsellersRank;
    private double price;
    private float ratingAvg;
    private int ratingCount;
    private List<String> authors;
    private List<String> categories;

    public Record() {
        this.id = "";
        this.title = "";
        this.isbn10 = "";
        this.isbn13 = "";
        this.edition = "";
        this.bestsellersRank = 0;
        this.price = 0.0;
        this.ratingAvg = 0.0f;
        this.ratingCount = 0;
        this.authors = new ArrayList<>();
        this.categories = new ArrayList<>();
    }

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

    public String getIsbn10() {
        return isbn10;
    }

    public void setIsbn10(String isbn10) {
        this.isbn10 = isbn10 == null ? "" : isbn10;
    }

    public String getIsbn13() {
        return isbn13;
    }

    public void setIsbn13(String isbn13) {
        this.isbn13 = isbn13 == null ? "" : isbn13;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition == null ? "" : edition;
    }

    public int getBestsellersRank() {
        return bestsellersRank;
    }

    public void setBestsellersRank(int bestsellersRank) {
        this.bestsellersRank = bestsellersRank;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors == null ? new ArrayList<>() : authors;
    }

    public List<String> getAuthorsUnmodifiable() {
        return Collections.unmodifiableList(authors);
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories == null ? new ArrayList<>() : categories;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Record)) {
            return false;
        }
        Record record = (Record) other;
        return Objects.equals(id, record.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Record{id='" + id + "', rank=" + bestsellersRank + "}";
    }
}
