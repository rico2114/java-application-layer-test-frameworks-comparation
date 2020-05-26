package com.movies.junit.unit.model;

import com.movies.model.Movie;
import com.movies.model.MoviePreview;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MoviePreviewModelTest {

    @Test
    public void testModelIsConstructedCorrectly1() {
        final MoviePreview moviePreview = new MoviePreview("prueba", "prueba descripcion", 5);

        assertThat(moviePreview.getName(), equalTo("prueba"));
        assertThat(moviePreview.getDescription(), equalTo("prueba descripcion"));
        assertThat(moviePreview.getScore(), equalTo(5));
    }

    @Test
    public void testModelIsConstructedCorrectly2() {
        final MoviePreview moviePreview = new MoviePreview("prueba", "prueba descripcion", 0);

        assertThat(moviePreview.getName(), equalTo("prueba"));
        assertThat(moviePreview.getDescription(), equalTo("prueba descripcion"));
        assertThat(moviePreview.getScore(), equalTo(0));
    }

    @Test
    public void testExceptionHappensWhenModelIsNotConstructedCorrectlyBecauseOfNameBeingNull() {
        assertThrows(NullPointerException.class, () -> new MoviePreview(null, "desc", 5));
    }

    @Test
    public void testExceptionHappensWhenModelIsNotConstructedCorrectlyBecauseOfDescriptionBeingNull() {
        assertThrows(NullPointerException.class, () -> new MoviePreview("name", null, 5));
    }

    @Test
    public void testExceptionHappensWhenModelIsNotConstructedCorrectlyBecauseOfScoreBeingGreaterThan5() {
        assertThrows(IllegalArgumentException.class, () -> new Movie("name", "description", 6, "youtubeUrl"));
    }

    @Test
    public void testExceptionHappensWhenModelIsNotConstructedCorrectlyBecauseOfScoreBeingGreaterLessThan0() {
        assertThrows(IllegalArgumentException.class, () -> new MoviePreview("name", "description", -1));
    }
}
