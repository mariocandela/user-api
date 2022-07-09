package com.noname.userapi.controller;

import com.noname.userapi.dto.UserDTO;
import com.noname.userapi.service.UserMapper;
import com.noname.userapi.service.UserService;
import io.swagger.api.UsersApiDelegate;
import io.swagger.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserApiDelegateImpl implements UsersApiDelegate {
    private final UserService userService;
    private final UserMapper userMapper;

    @Override
    public ResponseEntity<Void> createUser(User body) {
        UserDTO userDTO = userMapper.mapToUserDTO(body);

        userService.create(userDTO);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}

