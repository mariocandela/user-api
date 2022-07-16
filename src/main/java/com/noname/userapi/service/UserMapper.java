package com.noname.userapi.service;

import com.noname.userapi.dal.documents.UserItem;
import io.swagger.model.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User mapToUser(UserItem userItem);
    User mapToUser(UserDTO userDTO);
    List<User> mapToListUser(List<User> users);
    List<User> listUserItemMapToListUser(List<UserItem> usersItem);
    void update(User user, @MappingTarget UserItem userItem);
    UserItem mapToUserItem(User user);
    List<UserItem> mapToListUserItem(List<User> users);
    UserDTO mapToUserDTO(User user);
    List<UserDTO> mapToListUserDTO(List<User> users);
}
