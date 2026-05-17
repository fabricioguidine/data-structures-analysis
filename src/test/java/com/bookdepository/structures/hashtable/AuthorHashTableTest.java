package com.bookdepository.structures.hashtable;

import java.util.List;

import com.bookdepository.model.Author;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Tests for the open-addressing {@link AuthorHashTable}, including collision
 * handling and resize behaviour.
 */
@DisplayName("AuthorHashTable Tests")
class AuthorHashTableTest {

    @Test
    @DisplayName("Newly created table is empty")
    void newTableIsEmpty() {
        AuthorHashTable table = new AuthorHashTable(16);
        assertThat(table.size()).isZero();
        assertThat(table.loadFactor()).isZero();
        assertThat(table.getAllAuthors()).isEmpty();
    }

    @Test
    @DisplayName("Insert stores a deep copy that tracks frequency")
    void insertStoresEntry() {
        AuthorHashTable table = new AuthorHashTable(8);

        Author stored = table.insertOrIncrement(new Author("a1", "Alice"));

        assertThat(stored.getId()).isEqualTo("a1");
        assertThat(stored.getName()).isEqualTo("Alice");
        assertThat(stored.getFrequency()).isEqualTo(1);
        assertThat(table.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("Repeated inserts increment the same entry")
    void insertSameAuthorIncrements() {
        AuthorHashTable table = new AuthorHashTable(8);

        for (int i = 0; i < 5; i++) {
            table.insertOrIncrement(new Author("a1", "Alice"));
        }

        Author found = table.find("a1");
        assertThat(found).isNotNull();
        assertThat(found.getFrequency()).isEqualTo(5);
        assertThat(table.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("Distinct ids are all stored")
    void distinctIdsStored() {
        AuthorHashTable table = new AuthorHashTable(8);

        for (int i = 0; i < 20; i++) {
            table.insertOrIncrement(new Author("id-" + i, "Author " + i));
        }

        assertThat(table.size()).isEqualTo(20);
        for (int i = 0; i < 20; i++) {
            assertThat(table.find("id-" + i)).isNotNull();
        }
    }

    @Test
    @DisplayName("Collisions are resolved without losing entries")
    void collisionsAreResolved() {
        AuthorHashTable table = new AuthorHashTable(4);

        // Insert many entries into a tiny table to force collisions + resizing.
        int total = 64;
        for (int i = 0; i < total; i++) {
            table.insertOrIncrement(new Author("k-" + i, "name-" + i));
        }

        assertThat(table.size()).isEqualTo(total);
        for (int i = 0; i < total; i++) {
            assertThat(table.find("k-" + i)).as("entry %s", i).isNotNull();
        }
        // Load factor must stay below the configured threshold after resize.
        assertThat(table.loadFactor()).isLessThan(0.75);
    }

    @Test
    @DisplayName("getAllAuthors returns every stored entry")
    void getAllAuthorsReturnsEverything() {
        AuthorHashTable table = new AuthorHashTable(16);

        for (int i = 0; i < 10; i++) {
            table.insertOrIncrement(new Author("a" + i, "n" + i));
        }

        List<Author> all = table.getAllAuthors();
        assertThat(all).hasSize(10);
        assertThat(all).extracting(Author::getId)
                .containsExactlyInAnyOrder("a0", "a1", "a2", "a3", "a4", "a5", "a6", "a7", "a8", "a9");
    }

    @Test
    @DisplayName("Find on missing key returns null")
    void findMissingReturnsNull() {
        AuthorHashTable table = new AuthorHashTable(8);
        table.insertOrIncrement(new Author("a1", "Alice"));

        assertThat(table.find("missing")).isNull();
        assertThat(table.find(null)).isNull();
        assertThat(table.find("")).isNull();
    }

    @Test
    @DisplayName("Null author or empty id is rejected")
    void invalidInputsRejected() {
        AuthorHashTable table = new AuthorHashTable(8);

        assertThatThrownBy(() -> table.insertOrIncrement(null))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> table.insertOrIncrement(new Author("", "x")))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
