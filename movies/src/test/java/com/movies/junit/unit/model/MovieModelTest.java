package com.movies.junit.unit.model;

import com.movies.model.Movie;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MovieModelTest {

    @Test
    public void testModelIsConstructedCorrectly1() {
        final Movie movie = new Movie("prueba", "prueba descripcion", 5, "http://youtube.com/v=?aYFASCvas54");

        assertThat(movie.getName(), equalTo("prueba"));
        assertThat(movie.getDescription(), equalTo("prueba descripcion"));
        assertThat(movie.getScore(), equalTo(5));
        assertThat(movie.getYoutubeUrl(), equalTo("http://youtube.com/v=?aYFASCvas54"));
    }

    @Test
    public void testModelIsConstructedCorrectly2() {
        final Movie movie = new Movie("prueba", "prueba descripcion", 0, "http://youtube.com/v=?aYFASCvas54");

        assertThat(movie.getName(), equalTo("prueba"));
        assertThat(movie.getDescription(), equalTo("prueba descripcion"));
        assertThat(movie.getScore(), equalTo(0));
        assertThat(movie.getYoutubeUrl(), equalTo("http://youtube.com/v=?aYFASCvas54"));
    }

    @Test
    public void testExceptionHappensWhenModelIsNotConstructedCorrectlyBecauseOfNameBeingNull() {
        assertThrows(NullPointerException.class, () -> new Movie(null, "desc", 5, "youtubeUrl"));
    }

    @Test
    public void testExceptionHappensWhenModelIsNotConstructedCorrectlyBecauseOfDescriptionBeingNull() {
        assertThrows(NullPointerException.class, () -> new Movie("name", null, 5, "youtubeUrl"));
    }

    @Test
    public void testExceptionHappensWhenModelIsNotConstructedCorrectlyBecauseOfScoreBeingGreaterThan5() {
        assertThrows(IllegalArgumentException.class, () -> new Movie("name", "description", 6, "youtubeUrl"));
    }

    @Test
    public void testExceptionHappensWhenModelIsNotConstructedCorrectlyBecauseOfScoreBeingGreaterLessThan0() {
        assertThrows(IllegalArgumentException.class, () -> new Movie("name", "description", -1, "youtubeUrl"));
    }

    @Test
    public void testExceptionHappensWhenModelIsNotConstructedCorrectlyBecauseOfYoutubeUrlBeingNull() {
        assertThrows(NullPointerException.class, () -> new Movie("name", "description", 5, null));
    }
}
