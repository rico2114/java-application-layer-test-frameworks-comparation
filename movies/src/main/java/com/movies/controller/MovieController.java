package com.movies.controller;

import com.movies.model.Movie;
import com.movies.model.MoviePreview;
import com.movies.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MovieController {

    private final MovieService movieService;

    @Autowired
    public MovieController(final MovieService movieService) {
        this.movieService = movieService;
    }

    @RequestMapping(value = "/movie/list_all", method = RequestMethod.GET)
    List<MoviePreview> listAllMovies() {
        return movieService.listAll();
    }

    @RequestMapping(value = "/movie/find/{movieId}", method = RequestMethod.GET)
    Movie findMovie(@PathVariable("movieId") final int movieId) {
        return movieService.findMovie(movieId);
    }

    @RequestMapping(value = "/movie/filter_and_find/{movieId}/{tagFilter}")
    List<MoviePreview> filterByTagAndListMovies(final String tagFilter) {
        return movieService.filterByTagAndListMovies(tagFilter);
    }
}
