package com.movies.testng.unit.model;

import com.movies.model.Movie;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@Test(groups = "model")
public class MovieModelTest {

    @Test(threadPoolSize = 3, dataProvider = "generateParametrizationForTestModelIsConstructedCorrectly", invocationCount = 5)
    public void testModelIsConstructedCorrectly(final String name, final String description, final int score, final String youtubeUrl) {
        final Movie movie = new Movie(name, description, score, youtubeUrl);

        assertThat(movie.getName()).isEqualTo(name);
        assertThat(movie.getDescription()).isEqualTo(description);
        assertThat(movie.getScore()).isEqualTo(score);
        assertThat(movie.getYoutubeUrl()).isEqualTo(youtubeUrl);
    }

    @DataProvider(name = "generateParametrizationForTestModelIsConstructedCorrectly")
    public static Object[][] generateParametrizationForTestModelIsConstructedCorrectly() {
        return new Object[][] {{"titanic", "prueba titanic", 5, "http://youtube.com/v=?aYFASCvas54"},
                {"resident Evil 2", "prueba resident evil", 0, "http://youtube.com/v=?asdasd5414"},
                {"waterfall", "prueba waterfall", 3, "http://youtube.com/v=?asefebsad5414"}};
    }

    @Test(dependsOnMethods = "testModelIsConstructedCorrectly")
    public void testExceptionHappensWhenModelIsNotConstructedCorrectlyBecauseOfNameBeingNull() {
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(() -> {
            new Movie(null, "desc", 5, "youtubeUrl");
        });
    }

    @Test(dependsOnMethods = "testModelIsConstructedCorrectly")
    public void testExceptionHappensWhenModelIsNotConstructedCorrectlyBecauseOfDescriptionBeingNull() {
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(() -> {
            new Movie("name", null, 5, "youtubeUrl");
        });
    }

    @Test(dependsOnMethods = "testModelIsConstructedCorrectly")
    public void testExceptionHappensWhenModelIsNotConstructedCorrectlyBecauseOfScoreBeingGreaterThan5() {
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
            new Movie("name", "description", 6, "youtubeUrl");
        });
    }

    @Test(dependsOnMethods = "testModelIsConstructedCorrectly")
    public void testExceptionHappensWhenModelIsNotConstructedCorrectlyBecauseOfScoreBeingGreaterLessThan0() {
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
            new Movie("name", "description", -1, "youtubeUrl");
        });
    }

    @Test(dependsOnMethods = "testModelIsConstructedCorrectly")
    public void testExceptionHappensWhenModelIsNotConstructedCorrectlyBecauseOfYoutubeUrlBeingNull() {
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(() -> {
            new Movie("name", "description", 0, null);
        });
    }

}
