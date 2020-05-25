package com.movies.service;

import com.movies.database.MovieDatabaseAccessor;
import com.movies.model.Movie;
import com.movies.model.MoviePreview;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MovieServiceImplementation implements MovieService {

    private final MovieDatabaseAccessor movieDatabaseAccessor;

    @Autowired
    public MovieServiceImplementation(final MovieDatabaseAccessor movieDatabaseAccessor) {
        this.movieDatabaseAccessor = movieDatabaseAccessor;
    }

    @Override
    public List<MoviePreview> listAll() {
        return movieDatabaseAccessor.listAll();
    }

    @Override
    public Movie findMovie(int movieId) {
        return movieDatabaseAccessor.findMovie(movieId);
    }

    @Override
    public List<MoviePreview> filterByTagAndListMovies(String[] tagFilters) {
        return movieDatabaseAccessor.filterByTagAndListMovies(tagFilters);
    }
}
