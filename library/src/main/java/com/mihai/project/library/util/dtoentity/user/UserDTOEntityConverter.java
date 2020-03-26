package com.mihai.project.library.util.dtoentity.user;

import com.mihai.project.library.dto.user.UserDTO;
import com.mihai.project.library.dto.user.UserDTOAdd;
import com.mihai.project.library.dto.user.UserDTOOut;
import com.mihai.project.library.entity.user.User;

import java.util.List;

public interface UserDTOEntityConverter {

    User fromDTOToUser(UserDTO userDTO);

    UserDTO fromUserTODTO(User user);

    User fromDTOAddToUser(UserDTOAdd userDTOAdd);

    UserDTOOut fromUserToUserDTOOut(User user);

    List<UserDTOOut> fromUsersToDTOOut(List<User> users);
}
