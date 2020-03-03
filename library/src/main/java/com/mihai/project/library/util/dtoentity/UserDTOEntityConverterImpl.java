package com.mihai.project.library.util.dtoentity;

import com.mihai.project.library.dto.user.UserDTO;
import com.mihai.project.library.dto.user.UserDTOOut;
import com.mihai.project.library.entity.user.User;
import com.mihai.project.library.util.mapper.ModelMapperUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserDTOEntityConverterImpl implements UserDTOEntityConverter {

    @Override
    public User fromDTOToUser(UserDTO userDTO) {
        return ModelMapperUtil.getMapper().map(userDTO, User.class);
    }

    @Override
    public UserDTO fromUserTODTO(User user) {
        return ModelMapperUtil.getMapper().map(user, UserDTO.class);
    }

    @Override
    public UserDTOOut fromUserToUserDTOOut(User user) {
        return ModelMapperUtil.getMapper().map(user, UserDTOOut.class);
    }

    @Override
    public List<UserDTOOut> fromUsersToDTOOut(List<User> users) {
        List<UserDTOOut> usersToReturn = new ArrayList<>();
        ModelMapper mapper = ModelMapperUtil.getMapper();
        users.stream().forEach(user->{
            usersToReturn.add(mapper.map(user, UserDTOOut.class));
        });
        return usersToReturn;
    }
}
