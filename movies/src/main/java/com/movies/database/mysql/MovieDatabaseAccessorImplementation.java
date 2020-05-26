package com.movies.database.mysql;

import com.movies.database.MovieDatabaseAccessor;
import com.movies.model.Movie;
import com.movies.model.MoviePreview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
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
        final String query = "SELECT name, description, score FROM Movie";
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
        final String query = "SELECT name, description, score, youtubeUrl FROM Movie WHERE Movie.id = ?";
        final Movie movie = jdbcTemplate.query(query, new Object [] {movieId}, (rs -> {
            if (rs.next()) {
                final String name = rs.getString("name");
                final String description = rs.getString("description");
                final int score = rs.getInt("score");
                final String youtubeUrl = rs.getString("youtubeUrl");
                return new Movie(name, description, score, youtubeUrl);
            }
            return null;
        }));
        return movie;
    }

    @Override
    public List<MoviePreview> filterByTagAndListMovies(String[] tagFilters) {
        final String query = "SELECT name, description, score FROM Movie INNER JOIN MovieXTag ON (Movie.id = MovieXTag.movie_id) WHERE MovieXTag.tag IN (:tagFilters)";

        final NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
        final MapSqlParameterSource parameters = new MapSqlParameterSource();
        // Has to be casted to a list in order for the namedParameterTemplate to work.
        parameters.addValue("tagFilters", Arrays.asList(tagFilters));

        final List<MoviePreview> moviePreviews = namedParameterJdbcTemplate.query(query, parameters, ((rs, rc) -> {
            final String name = rs.getString("name");
            final String description = rs.getString("description");
            final int score = rs.getInt("score");
            return new MoviePreview(name, description, score);
        }));
        return moviePreviews;
    }
}
