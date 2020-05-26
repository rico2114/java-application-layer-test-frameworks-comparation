package com.movies.junit.unit.model;

import com.movies.model.Movie;
import com.movies.model.MoviePreview;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@Test(groups = "model")
public class MoviePreviewModelTest {

    @Test(dataProvider = "generateParametrizationForTestModelIsConstructedCorrectly")
    public void testModelIsConstructedCorrectly(final String name, final String description, final int score) {
        final MoviePreview movie = new MoviePreview(name, description, score);

        assertThat(movie.getName()).isEqualTo(name);
        assertThat(movie.getDescription()).isEqualTo(description);
        assertThat(movie.getScore()).isEqualTo(score);
    }

    @DataProvider(name = "generateParametrizationForTestModelIsConstructedCorrectly")
    public static Object[][] generateParametrizationForTestModelIsConstructedCorrectly() {
        return new Object[][] {{"titanic", "prueba titanic", 5},
                {"resident Evil 2", "prueba resident evil", 0},
                {"waterfall", "prueba waterfall", 3}};
    }

    @Test
    public void testExceptionHappensWhenModelIsNotConstructedCorrectlyBecauseOfNameBeingNull() {
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(() -> {
            new MoviePreview(null, "desc", 5);
        });
    }

    @Test
    public void testExceptionHappensWhenModelIsNotConstructedCorrectlyBecauseOfDescriptionBeingNull() {
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(() -> {
            new MoviePreview("name", null, 5);
        });
    }

    @Test
    public void testExceptionHappensWhenModelIsNotConstructedCorrectlyBecauseOfScoreBeingGreaterThan5() {
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
            new MoviePreview("name", "description", 6);
        });
    }

    @Test
    public void testExceptionHappensWhenModelIsNotConstructedCorrectlyBecauseOfScoreBeingGreaterLessThan0() {
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
            new MoviePreview("name", "description", -1);
        });
    }

}
