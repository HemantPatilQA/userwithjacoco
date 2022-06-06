package com.selflearning.userwithjacoco.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.selflearning.userwithjacoco.entities.Response;
import com.selflearning.userwithjacoco.entities.User;
import com.selflearning.userwithjacoco.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class UserControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private UserRepository userRepository;

    ObjectMapper om = new ObjectMapper();

    @BeforeEach
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

        Assertions.assertTrue(response.isSuccess()==Boolean.TRUE);
    }

    @Test
    public void getUsersTest() throws Exception {
        when(userRepository.findAll()).thenReturn(Stream.of(new User(101L, "Hemant", 42, "Pune"), new User(102L, "Minali", 39, "Nasik")).collect(Collectors.toList()));
        MvcResult result = mockMvc.perform(get("/users/").contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk()).andReturn();

        String resultContent = result.getResponse().getContentAsString();

        Response response = om.readValue(resultContent, Response.class);
        Assertions.assertEquals("Record Count : 2", response.getMessage());
        Assertions.assertTrue(response.isSuccess()==Boolean.TRUE);
    }

    @Test
    public void getUsersByAddressTest() throws Exception {
        String address = "Bangalore";
        when(userRepository.findAllByAddress(address)).thenReturn(Stream.of(new User(101L, "Hemant", 42, "Pune"))
                .collect(Collectors.toList()));
        MvcResult result = mockMvc.perform(get("/users/getbyaddress/" + address).contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk()).andReturn();

        String resultContent = result.getResponse().getContentAsString();

        Response response = om.readValue(resultContent, Response.class);
        Assertions.assertTrue(response.isSuccess()==Boolean.TRUE);
        Assertions.assertEquals("Record Count : 1", response.getMessage());
    }

    @Test
    public void deleteUserTest() throws Exception {
        User user = new User(101L, "Hemant", 42, "Pune");

        String jsonRequest = om.writeValueAsString(user);
        MvcResult result = mockMvc.perform(delete("/users/remove").content(jsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk()).andReturn();
        verify(userRepository, times(1)).delete(user);
    }
}