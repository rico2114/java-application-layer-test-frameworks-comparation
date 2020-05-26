package com.movies.junit.unit.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.opentest4j.AssertionFailedError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.Duration;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MovieControllerTest {

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

    @BeforeAll
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    public void testResponseOfUserRecordRestClientIsFasterThanOneSecond() throws Exception {
        assertTimeout(Duration.ofMillis(1000), () -> {
            mockMvc.perform(get("/user/record/1")).andExpect(status().isOk()).andExpect(content().json(JSON_RESPONSE_REQUIRED));
        });
    }

    @Test
    public void testResponseOfUserRecordRestClientIsSlowAndFails() throws Exception {
        // Assert that this response is not retrieved within 1/4 of a second.

        // Note: If we wanted to use JsonAssert library we would possibly need to create a matcher.
        // TODO(Juan): Test more this json matcher integrated by spring framework for testing, we need to see if it's actually worth using.
        assertThrows(AssertionFailedError.class, () -> {
            assertTimeout(Duration.ofMillis(250), () -> {
                mockMvc.perform(get("/user/record/1")).andExpect(status().isOk()).andExpect(content().json(JSON_RESPONSE_REQUIRED));
            });
        });
    }
}
