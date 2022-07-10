package com.noname.userapi.service;

import com.noname.userapi.dal.documents.UserItem;
import com.noname.userapi.dto.UserDTO;
import io.swagger.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO mapToUserDTO(UserItem userItem);

    UserDTO mapToUserDTO(User user);

    UserItem mapToUserItem(UserDTO userDTO);

    User mapToUser(UserDTO userDTO);

    List<UserDTO> mapToListUserDTO(List<UserItem> userItems);

    List<User> mapToListUser(List<UserDTO> userDTOS);

    List<UserItem> mapToListUserItem(List<UserDTO> userDTOS);

    void update(UserDTO userDTO, @MappingTarget UserItem userItem);
}
