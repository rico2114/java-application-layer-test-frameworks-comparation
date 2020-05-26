package com.movies.model;

import java.util.Objects;

public class MoviePreview {

    private final String name;
    private final String description;
    private final int score;

    public MoviePreview(final String name, final String description, final int score) {
        Objects.requireNonNull(name, "The name cannot be null.");
        Objects.requireNonNull(description, "The description cannot be null.");
        if (score < 0 || score > 5) {
            throw new IllegalArgumentException("The movie score has to be between [0, 5]");
        }

        this.name = name;
        this.description = description;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getScore() {
        return score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MoviePreview that = (MoviePreview) o;
        return score == that.score &&
                Objects.equals(name, that.name) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, score);
    }
}
