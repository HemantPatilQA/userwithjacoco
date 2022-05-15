package com.selflearning.userwithjacoco.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.selflearning.userwithjacoco.entities.Response;
import com.selflearning.userwithjacoco.entities.User;
import com.selflearning.userwithjacoco.repositories.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private UserRepository userRepository;

    ObjectMapper om = new ObjectMapper();

    @Before
    public void setUp(){
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void addUserTest() throws Exception {
        User user = new User();
        user.setUsername("Mahesh");
        user.setAge(42);
        user.setAddress("Jalgaon");

        String jsonRequest = om.writeValueAsString(user);

        MvcResult result = mockMvc.perform(post("/users/adduser").content(jsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk()).andReturn();

        String resultContent = result.getResponse().getContentAsString();

        Response response = om.readValue(resultContent, Response.class);

        Assert.assertTrue(response.isSuccess()==Boolean.TRUE);
    }

    @Test
    public void getUsersTest() throws Exception {
        when(userRepository.findAll()).thenReturn(Stream.of(new User(101L, "Hemant", 42, "Pune"), new User(102L, "Minali", 39, "Nasik")).collect(Collectors.toList()));
        MvcResult result = mockMvc.perform(get("/users/").contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk()).andReturn();

        String resultContent = result.getResponse().getContentAsString();

        Response response = om.readValue(resultContent, Response.class);
        System.out.println("Message : " + response.getMessage());
        Assert.assertTrue(response.isSuccess()==Boolean.TRUE);
    }
}