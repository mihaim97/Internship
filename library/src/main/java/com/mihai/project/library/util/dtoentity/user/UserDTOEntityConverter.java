package com.mihai.project.library.util.dtoentity.user;

import com.mihai.project.library.dto.user.UserDTO;
import com.mihai.project.library.dto.user.UserDTOOut;
import com.mihai.project.library.entity.user.User;

import java.util.List;

public interface UserDTOEntityConverter {
    /**User**/
    public User fromDTOToUser(UserDTO userDTO);
    public UserDTO fromUserTODTO(User user);
    public UserDTOOut fromUserToUserDTOOut(User user);
    public List<UserDTOOut> fromUsersToDTOOut(List<User> users);
}
