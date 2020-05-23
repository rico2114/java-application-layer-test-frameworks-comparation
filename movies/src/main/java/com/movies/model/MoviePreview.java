package com.movies.model;

public class MoviePreview {

    private final String name;
    private final String description;
    private final int score;

    public MoviePreview(final String name, final String description, final int score) {
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
}
