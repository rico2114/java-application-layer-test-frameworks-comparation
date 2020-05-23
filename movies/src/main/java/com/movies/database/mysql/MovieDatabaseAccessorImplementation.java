package com.movies.database.mysql;

import com.movies.database.MovieDatabaseAccessor;
import com.movies.model.Movie;
import com.movies.model.MoviePreview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MovieDatabaseAccessorImplementation implements MovieDatabaseAccessor {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public MovieDatabaseAccessorImplementation(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<MoviePreview> listAll() {
        final String query = "SELECT (name, description, score) FROM Movie";
        List<MoviePreview> movies = jdbcTemplate.query(query, (rs, rn) -> {
            final String name = rs.getString("name");
            final String description = rs.getString("description");
            final int score = rs.getInt("score");

            return new MoviePreview(name, description, score);
        });
        return movies;
    }

    @Override
    public Movie findMovie(int movieId) {
        final String query = "SELECT (name, description, score, youtubeUrl) FROM Movie WHERE Movie.id = movieId";
        final Movie movie = jdbcTemplate.query(query, new Object [] {movieId}, (rs -> {
            final String name = rs.getString("name");
            final String description = rs.getString("description");
            final int score = rs.getInt("score");
            final String youtubeUrl = rs.getString("youtube_url");
            return new Movie(name, description, score, youtubeUrl);
        }));
        return movie;
    }

    @Override
    public List<MoviePreview> filterByTagAndListMovies(String tagFilter) {
        final String query = "SELECT (name, description, score, youtube_url) FROM Movie WHERE ";
        return null;
    }
}
