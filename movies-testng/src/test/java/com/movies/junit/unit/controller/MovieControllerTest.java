package com.movies.junit.unit.controller;

import com.movies.MoviesApplication;
import org.junit.BeforeClass;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = MoviesApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Test(groups = "controller")
public class MovieControllerTest extends AbstractTestNGSpringContextTests {

    // TODO(Juan): Find a better way to store these jsons. Perhaps use a file for it?
    private static final String JSON_RESPONSE_REQUIRED = "[\n" +
            "    {\n" +
            "        \"activity\": \"login\",\n" +
            "        \"response\": \"ok\",\n" +
            "        \"data\": \"username:juan2114,name:Juan,email:juan.2114@hotmail.com\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"activity\": \"browse movies\",\n" +
            "        \"response\": \"failure\",\n" +
            "        \"data\": \"\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"activity\": \"browse movies\",\n" +
            "        \"response\": \"ok\",\n" +
            "        \"data\": \"\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"activity\": \"select movie\",\n" +
            "        \"response\": \"ok\",\n" +
            "        \"data\": \"movie:Piratas del caribe,movieId:14\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"activity\": \"browse movies\",\n" +
            "        \"response\": \"ok\",\n" +
            "        \"data\": \"\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"activity\": \"logout\",\n" +
            "        \"response\": \"ok\",\n" +
            "        \"data\": \"\"\n" +
            "    }\n" +
            "]";

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @BeforeMethod
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test(timeOut = 1000L)
    public void testResponseOfUserRecordRestClientIsFasterThanOneSecond() throws Exception {
        mockMvc.perform(get("/user/record/1")).andExpect(status().isOk()).andExpect(content().json(JSON_RESPONSE_REQUIRED));
    }

    @Test(timeOut = 250L)
    public void testResponseOfUserRecordRestClientIsMeetingMinimumExpectation() throws Exception {
        // Assert that this response is not retrieved within 1/4 of a second.

        // Note: If we wanted to use JsonAssert library we would possibly need to create a matcher.
        // TODO(Juan): Test more this json matcher integrated by spring framework for testing, we need to see if it's actually worth using.
        mockMvc.perform(get("/user/record/1")).andExpect(status().isOk()).andExpect(content().json(JSON_RESPONSE_REQUIRED));
    }
}
