package com.movies.junit.unit.service;

import com.movies.database.MovieDatabaseAccessor;
import com.movies.model.Movie;
import com.movies.model.MoviePreview;
import com.movies.service.MovieService;
import com.movies.service.MovieServiceImplementation;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class MovieControllerTest {

    private static MovieDatabaseAccessor movieDatabaseAccessorMock;
    private static MovieService movieService;

    @BeforeAll
    public static void setupMovieService() {
        movieDatabaseAccessorMock = mock(MovieDatabaseAccessor.class);
        movieService = new MovieServiceImplementation(movieDatabaseAccessorMock);
    }

    @Test
    public void testListAllReturnsCorrectlyMockedMovies() {
        final ArrayList<MoviePreview> availableMovies = new ArrayList<>();
        availableMovies.add(new MoviePreview("Titanic", "Titanic", 4));

        when(movieDatabaseAccessorMock.listAll()).thenReturn(availableMovies);

        assertThat(movieService.listAll(), hasSize(1));
        assertThat(movieService.listAll(), hasItem(new MoviePreview("Titanic", "Titanic", 4)));
    }

    @Test
    public void testFindMovieReturnsCorrectlyMockedMovie() {
        when(movieDatabaseAccessorMock.findMovie(anyInt())).thenReturn(new Movie("Titanic", "Titanic", 4, "http://youtube.com/v=?UgasdHASD471"));

        assertThat(movieService.findMovie(1), equalTo(new Movie("Titanic", "Titanic", 4, "http://youtube.com/v=?UgasdHASD471")));
    }

    @Test
    public void testFilterForMoviesAndReturnsCorrectlyFilteredMovies() {
        final ArrayList<MoviePreview> filteredMoviesResult = new ArrayList<>();

        final MoviePreview titanic = new MoviePreview("Titanic", "Titanic", 5);
        filteredMoviesResult.add(titanic);
        final MoviePreview theBeastAndTheBeauty = new MoviePreview("La bella y la bestia", "La bella y la bestia", 5);
        filteredMoviesResult.add(theBeastAndTheBeauty);

        when(movieDatabaseAccessorMock.filterByTagAndListMovies(ArgumentMatchers.any())).thenReturn(filteredMoviesResult);

        final String genre = "Drama";
        assertThat(movieService.filterByTagAndListMovies(new String[]{genre}), hasSize(2));
        assertThat(movieService.filterByTagAndListMovies(new String[]{genre}), hasItems(titanic, theBeastAndTheBeauty));
    }

    @Test
    @Disabled
    public void testFilterForMoviesAndReturnsCorrectlyFilteredMoviesNotCompletedTest() {
        assertThat(true, equalTo(false));
    }
}
