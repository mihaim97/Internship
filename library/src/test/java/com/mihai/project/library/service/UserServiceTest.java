package com.mihai.project.library.service;

import com.mihai.project.library.dao.UserDAO;
import com.mihai.project.library.dto.user.UserDTO;
import com.mihai.project.library.entity.user.User;
import com.mihai.project.library.service.user.UserService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserService userServiceResource;

    @Mock
    private UserDAO userDAOResource;

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
}
