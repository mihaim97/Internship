package com.mihai.project.library.controller;

import com.mihai.project.library.dao.UserDAO;
import com.mihai.project.library.dto.user.UserDTO;
import com.mihai.project.library.entity.user.User;
import com.mihai.project.library.service.user.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Mock
    private UserService userServiceResource;

    @Autowired
    private  MockMvc mvc;

    private static User user;

    private static UserDTO userDTO;

    @BeforeAll
    public static void init(){
        user = new User(0,"tomas", "password", "fName", "lName", "email@email", 0, "admin");
        userDTO = new UserDTO("tomas", "password", "fName", "lName", "emailemail","ADMIN");
    }

    @Test
    public void givenUser_whenAddUser_returnUser(){
        when(userServiceResource.addUser(user)).thenReturn(user);
        User userReturned = userServiceResource.addUser(user);
        assertThat(userReturned.getId(), is(user.getId()));
    }

    @Test
    public void givenUsername_whenQueryUser_returnUser(){
        when(userServiceResource.queryUser("tomas")).thenReturn(user);
        User userReturned = userServiceResource.queryUser("tomas");
        assertThat(userReturned.getUsername(), is(user.getUsername()));
    }

    @Test
    public void givenEmail_whenAddUser_returnInvalidEmail() throws Exception {
        MvcResult result =  mvc.perform(MockMvcRequestBuilders.get("/user/list")
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        String resultFrom = result.getResponse().getContentAsString();
        Assertions.assertNotNull(resultFrom);

    }

}
