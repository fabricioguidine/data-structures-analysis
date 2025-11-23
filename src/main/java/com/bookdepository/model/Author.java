package com.bookdepository.model;

/**
 * Represents an author from the Book Depository dataset.
 * This class models an author with their ID and name,
 * and tracks the frequency of their works.
 */
public class Author {
    private String id;
    private String name;
    private int frequency;

    /**
     * Default constructor.
     */
    public Author() {
        this.frequency = 0;
    }

    /**
     * Constructor with ID and name.
     *
     * @param id   Author identifier
     * @param name Author name
     */
    public Author(String id, String name) {
        this.id = id;
        this.name = name;
        this.frequency = 1;
    }

    /**
     * Constructor with ID, name, and frequency.
     *
     * @param id        Author identifier
     * @param name      Author name
     * @param frequency Number of works by this author
     */
    public Author(String id, String name, int frequency) {
        this.id = id;
        this.name = name;
        this.frequency = frequency;
    }

    // Getters and Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    /**
     * Increments the frequency count for this author.
     */
    public void incrementFrequency() {
        this.frequency++;
    }

    /**
     * Compares two authors by their frequency (descending order).
     * Used for finding most frequent authors.
     *
     * @param other The other author to compare
     * @return Negative if this frequency > other frequency, 0 if equal, positive otherwise
     */
    public int compareByFrequency(Author other) {
        return Integer.compare(other.frequency, this.frequency);
    }

    @Override
    public String toString() {
        return "Author{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", frequency=" + frequency +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Author author = (Author) obj;
        return id != null ? id.equals(author.id) : author.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
