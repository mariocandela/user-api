package com.noname.userapi.service;

import com.noname.userapi.dal.models.UserItem;
import com.noname.userapi.dto.UserDTO;
import io.swagger.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO mapToUserDTO(UserItem userItem);

    UserDTO mapToUserDTO(User user);

    UserItem mapToUserItem(UserDTO userDTO);
}
