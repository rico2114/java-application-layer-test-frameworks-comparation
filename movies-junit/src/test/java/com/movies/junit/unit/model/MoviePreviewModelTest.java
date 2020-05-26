package com.movies.junit.unit.model;

import com.movies.model.Movie;
import com.movies.model.MoviePreview;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MoviePreviewModelTest {

    @ParameterizedTest
    @MethodSource("generateParametrizationForTestModelIsConstructedCorrectly")
    public void testModelIsConstructedCorrectly(final String name, final String description, final int score) {
        final MoviePreview movie = new MoviePreview(name, description, score);

        assertThat(movie.getName(), equalTo(name));
        assertThat(movie.getDescription(), equalTo(description));
        assertThat(movie.getScore(), equalTo(score));
    }

    private static Stream<Arguments> generateParametrizationForTestModelIsConstructedCorrectly() {
        return Stream.of(Arguments.of("titanic", "prueba titanic", 5),
                Arguments.of("resident Evil 2", "prueba resident evil", 0),
                Arguments.of("waterfall", "prueba waterfall", 3));
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
