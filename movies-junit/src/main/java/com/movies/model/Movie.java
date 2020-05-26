package com.movies.model;

import java.util.Objects;

public class Movie {

    private final String name;
    private final String description;
    private final int score;
    private final String youtubeUrl;

    public Movie(final String name, final String description, final int score, final String youtubeUrl) {
        Objects.requireNonNull(name, "The Movie name cannot be null.");
        Objects.requireNonNull(description, "The Movie description cannot be null.");
        if (score < 0 || score > 5) {
            throw new IllegalArgumentException("The movie score has to be between [0, 5]");
        }
        Objects.requireNonNull(youtubeUrl, "The Movie youtube url cannot be null.");

        this.name = name;
        this.description = description;
        this.score = score;
        this.youtubeUrl = youtubeUrl;
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

    public String getYoutubeUrl() {
        return youtubeUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return score == movie.score &&
                Objects.equals(name, movie.name) &&
                Objects.equals(description, movie.description) &&
                Objects.equals(youtubeUrl, movie.youtubeUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, score, youtubeUrl);
    }
}
