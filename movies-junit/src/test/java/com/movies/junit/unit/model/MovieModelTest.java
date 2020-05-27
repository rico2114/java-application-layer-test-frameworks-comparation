package com.movies.junit.unit.model;

import com.movies.model.Movie;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class MovieModelTest {

    @ParameterizedTest
    @MethodSource("generateParametrizationForTestModelIsConstructedCorrectly")
    @Tag("primary")
    public void testModelIsConstructedCorrectly(final String name, final String description, final int score, final String youtubeUrl) {
        final Movie movie = new Movie(name, description, score, youtubeUrl);

        assertThat(movie.getName(), equalTo(name));
        assertThat(movie.getDescription(), equalTo(description));
        assertThat(movie.getScore(), equalTo(score));
        assertThat(movie.getYoutubeUrl(), equalTo(youtubeUrl));
    }

    private static Stream<Arguments> generateParametrizationForTestModelIsConstructedCorrectly() {
        return Stream.of(Arguments.of("titanic", "prueba titanic", 5, "http://youtube.com/v=?aYFASCvas54"),
            Arguments.of("resident Evil 2", "prueba resident evil", 0, "http://youtube.com/v=?asdasd5414"),
            Arguments.of("waterfall", "prueba waterfall", 3, "http://youtube.com/v=?asefebsad5414"));
    }

    @Test
    @Tag("nullpointer")
    public void testExceptionHappensWhenModelIsNotConstructedCorrectlyBecauseOfNameBeingNull() {
        assertThrows(NullPointerException.class, () -> new Movie(null, "desc", 5, "youtubeUrl"));
    }

    @Test
    @Tag("nullpointer")
    public void testExceptionHappensWhenModelIsNotConstructedCorrectlyBecauseOfDescriptionBeingNull() {
        assertThrows(NullPointerException.class, () -> new Movie("name", null, 5, "youtubeUrl"));
    }

    @Test
    @Tag("illegalargument")
    public void testExceptionHappensWhenModelIsNotConstructedCorrectlyBecauseOfScoreBeingGreaterThan5() {
        assertThrows(IllegalArgumentException.class, () -> new Movie("name", "description", 6, "youtubeUrl"));
    }

    @Test
    @Tag("illegalargument")
    public void testExceptionHappensWhenModelIsNotConstructedCorrectlyBecauseOfScoreBeingGreaterLessThan0() {
        assertThrows(IllegalArgumentException.class, () -> new Movie("name", "description", -1, "youtubeUrl"));
    }

    @Test
    @Tag("illegalargument")
    public void testExceptionHappensWhenModelIsNotConstructedCorrectlyBecauseOfYoutubeUrlBeingNull() {
        assertThrows(NullPointerException.class, () -> new Movie("name", "description", 5, null));
    }

    @org.junit.Test
    public void testWithJUnit4TestExceptionHappensWhenModelIsNotConstructedCorrectlyBecauseOfYoutubeUrlBeingNull() {
        assertThrows(NullPointerException.class, () -> new Movie("name", "description", 5, null));
    }
}
