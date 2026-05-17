package com.bookdepository.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Book Depository record model.
 *
 * <p>Represents a single book entry from the Book Depository dataset, holding
 * the metadata required by the sorting and hash-table experiments.
 */
public class Record {

    private String id;
    private String title;
    private int bestsellersRank;
    private double price;
    private List<String> authors;

    public Record() {
        this.id = "";
        this.title = "";
        this.bestsellersRank = 0;
        this.price = 0.0;
        this.authors = new ArrayList<>();
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

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors == null ? new ArrayList<>() : authors;
    }

    public List<String> getAuthorsUnmodifiable() {
        return Collections.unmodifiableList(authors);
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
