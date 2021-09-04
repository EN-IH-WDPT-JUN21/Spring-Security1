package com.ironhack.rest.controller.impl;

import com.ironhack.rest.dao.Course;
import com.ironhack.rest.repository.CourseRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class CourseControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private CourseRepository courseRepository;


    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .build();
        Course course = new Course("CS101", "Intro to Java");
        Course course2 = new Course("CS102", "Databases");


        courseRepository.saveAll(List.of(course, course2));
    }

    @AfterEach
    void tearDown() {
        courseRepository.deleteAll();
    }


//
//    @Test
//    void getAll_Valid_AllCourses() throws Exception {
//        MvcResult mvcResult = mockMvc.perform(get("/courses").with(httpBasic("admin", "123456")))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andReturn();
//        assertTrue(mvcResult.getResponse().getContentAsString().contains("Intro to Java"));
//        assertTrue(mvcResult.getResponse().getContentAsString().contains("Databases"));
//    }
//
//    @Test
//    void store_Valid_Created() throws Exception {
//        Course course = new Course("CS103", "Another course");
//        String body = objectMapper.writeValueAsString(course);
//        MvcResult mvcResult = mockMvc.perform(post("/courses")
//                .content(body)
//                .contentType(MediaType.APPLICATION_JSON)
//
//        ).andExpect(status().isCreated()).andReturn();
//
//        assertTrue(mvcResult.getResponse().getContentAsString().contains("Another course"));
//    }
//
//    @Test
//    void store_Invalid_UnprocessableEntity() throws Exception {
//        Course course = new Course("CS101", "Another course");
//        String body = objectMapper.writeValueAsString(course);
//        MvcResult mvcResult = mockMvc.perform(post("/courses")
//                .content(body)
//                .contentType(MediaType.APPLICATION_JSON)
//
//        ).andExpect(status().isUnprocessableEntity()).andReturn();
//    }
//
//    @Test
//    void store_Invalid_BadRequest() throws Exception {
//        Course course = new Course("CS101", "");
//        String body = objectMapper.writeValueAsString(course);
//        MvcResult mvcResult = mockMvc.perform(post("/courses")
//                .content(body)
//                .contentType(MediaType.APPLICATION_JSON)
//
//        ).andExpect(status().isBadRequest()).andReturn();
//    }
//
//    @Test
//    void update_Invalid_NotFound() throws Exception {
//        Course course = new Course("CS101", "Modified name");
//        String body = objectMapper.writeValueAsString(course);
//        mockMvc.perform(put("/courses/CS111")
//                .content(body)
//                .contentType(MediaType.APPLICATION_JSON)
//        ).andExpect(status().isNotFound());
//    }
}