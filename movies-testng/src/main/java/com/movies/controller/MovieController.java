package com.movies.controller;

import com.movies.model.Movie;
import com.movies.model.MoviePreview;
import com.movies.model.UserAction;
import com.movies.rest.UserActionRecordClient;
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
    private final UserActionRecordClient userActionRecordClient;

    @Autowired
    public MovieController(final MovieService movieService, final UserActionRecordClient userActionRecordClient) {
        this.movieService = movieService;
        this.userActionRecordClient = userActionRecordClient;
    }

    @RequestMapping(value = "/movie/list_all", method = RequestMethod.GET)
    List<MoviePreview> listAllMovies() {
        return movieService.listAll();
    }

    @RequestMapping(value = "/movie/find/{movieId}", method = RequestMethod.GET)
    Movie findMovie(@PathVariable("movieId") final int movieId) {
        return movieService.findMovie(movieId);
    }

    @RequestMapping(value = "/movie/filter_and_find/{tagFilters}")
    List<MoviePreview> filterByTagAndListMovies(@PathVariable("tagFilters") final String[] tagFilters) {
        return movieService.filterByTagAndListMovies(tagFilters);
    }

    @RequestMapping(value = "/user/record/{user_id}", method = RequestMethod.GET)
    UserAction[] retrieveAllUserActions(@PathVariable("user_id") final int userId) {
        return userActionRecordClient.retrieveAllUserActions(userId);
    }
}
