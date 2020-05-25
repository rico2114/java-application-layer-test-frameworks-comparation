package com.movies.service;

import com.movies.model.Movie;
import com.movies.model.MoviePreview;

import java.util.List;

public interface MovieService {

    List<MoviePreview> listAll();
    Movie findMovie(final int movieId);
    List<MoviePreview> filterByTagAndListMovies(final String[] tagFilters);
}
