package com.movies.testng.unit.service;
import com.movies.database.MovieDatabaseAccessor;
import com.movies.model.Movie;
import com.movies.model.MoviePreview;
import com.movies.service.MovieService;
import com.movies.service.MovieServiceImplementation;
import org.aspectj.lang.annotation.Before;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.easymock.EasyMock.*;

import static org.assertj.core.api.Assertions.assertThat;

@Test(groups = "service")
public class MovieServiceTest {

    private MovieDatabaseAccessor movieDatabaseAccessorMock;
    private MovieService movieService;

    @BeforeMethod
    public void setupMovieService() {
        movieDatabaseAccessorMock = createNiceMock(MovieDatabaseAccessor.class);
        movieService = new MovieServiceImplementation(movieDatabaseAccessorMock);
    }

    @Test
    public void testListAllReturnsCorrectlyMockedMovies() {
        final ArrayList<MoviePreview> availableMovies = new ArrayList<>();
        availableMovies.add(new MoviePreview("Titanic", "Titanic", 4));

        expect(movieDatabaseAccessorMock.listAll()).andReturn(availableMovies);
        // Setup is completed no need to record anymore. Move to a test mode.
        replay(movieDatabaseAccessorMock);

        final List<MoviePreview> previews = movieService.listAll();
        assertThat(previews).hasSize(1);
        assertThat(previews).contains(new MoviePreview("Titanic", "Titanic", 4));
    }

    @Test
    public void testFindMovieReturnsCorrectlyMockedMovie() {
        expect(movieDatabaseAccessorMock.findMovie(anyInt())).andReturn(new Movie("Titanic", "Titanic", 4, "http://youtube.com/v=?UgasdHASD471"));
        // Setup is completed no need to record anymore. Move to a test mode.
        replay(movieDatabaseAccessorMock);

        assertThat(movieService.findMovie(1)).isEqualTo(new Movie("Titanic", "Titanic", 4, "http://youtube.com/v=?UgasdHASD471"));
    }

    @Test
    public void testFilterForMoviesAndReturnsCorrectlyFilteredMovies() {
        final ArrayList<MoviePreview> filteredMoviesResult = new ArrayList<>();

        final MoviePreview titanic = new MoviePreview("Titanic", "Titanic", 5);
        filteredMoviesResult.add(titanic);
        final MoviePreview theBeastAndTheBeauty = new MoviePreview("La bella y la bestia", "La bella y la bestia", 5);
        filteredMoviesResult.add(theBeastAndTheBeauty);

        expect(movieDatabaseAccessorMock.filterByTagAndListMovies(anyObject())).andReturn(filteredMoviesResult);
        // Setup is completed no need to record anymore. Move to a test mode.
        replay(movieDatabaseAccessorMock);

        final String genre = "Drama";
        final List<MoviePreview> movies = movieService.filterByTagAndListMovies(new String[]{genre});
        assertThat(movies).hasSize(2);
        assertThat(movies).contains(titanic, theBeastAndTheBeauty);
    }

    @Test(enabled = false)
    public void testFilterForMoviesAndReturnsCorrectlyFilteredMoviesNotCompletedTest() {
        assertThat(true).isEqualTo((false));
    }
}
