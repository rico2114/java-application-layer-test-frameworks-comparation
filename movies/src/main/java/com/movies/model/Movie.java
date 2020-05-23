package com.movies.model;

public class Movie {

    private final String name;
    private final String description;
    private final int score;
    private final String youtubeUrl;

    public Movie(final String name, final String description, final int score, final String youtubeUrl) {
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
}
