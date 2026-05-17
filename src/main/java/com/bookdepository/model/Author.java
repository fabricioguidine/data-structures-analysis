package com.bookdepository.model;

import java.util.Objects;

/**
 * Author of one or more book records.
 *
 * <p>Used by the hash-table experiment to count author frequencies across the
 * dataset.
 */
public class Author {

    private String id;
    private String name;
    private int frequency;

    public Author() {
        this("", "");
    }

    public Author(String id, String name) {
        this.id = id == null ? "" : id;
        this.name = name == null ? "" : name;
        this.frequency = 0;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? "" : id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? "" : name;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public void incrementFrequency() {
        this.frequency++;
    }

    /**
     * Compares this author against another by frequency.
     *
     * @param other the other author
     * @return negative if this is less frequent, positive if more, zero if equal
     */
    public int compareByFrequency(Author other) {
        if (other == null) {
            return 1;
        }
        return Integer.compare(this.frequency, other.frequency);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Author)) {
            return false;
        }
        Author author = (Author) other;
        return Objects.equals(id, author.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Author{id='" + id + "', name='" + name + "', frequency=" + frequency + "}";
    }
}
