package com.noname.userapi.controller;

import com.noname.userapi.dto.UserDTO;
import com.noname.userapi.exception.UserNotFoundException;
import com.noname.userapi.service.UserMapper;
import com.noname.userapi.service.UserService;
import io.swagger.api.UsersApiDelegate;
import io.swagger.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
@RequiredArgsConstructor
public class UserApiDelegateImpl implements UsersApiDelegate {
    private final UserService userService;
    private final UserMapper userMapper;

    @Override
    public ResponseEntity<User> createUser(User body) {
        UserDTO userDTOInput = userMapper.mapToUserDTO(body);
        UserDTO userDTOStored = userService.create(userDTOInput);
        return new ResponseEntity<>(userMapper.mapToUser(userDTOStored), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> deleteUser(String email) {
        userService.delete(email);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @Override
    public ResponseEntity<List<User>> findUser(String name, String surname) {
        List<UserDTO> usersDTO = userService.findUsers(name, surname);
        return new ResponseEntity<>(userMapper.mapToListUser(usersDTO), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<User> getUser(String  email) {
        UserDTO userDTO = userService.getByEmail(email).orElseThrow(UserNotFoundException::new);
        return new ResponseEntity<>(userMapper.mapToUser(userDTO), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<User> updateUser(String email, User body) {
        UserDTO userDTO = userService.update(email, userMapper.mapToUserDTO(body));
        return new ResponseEntity<>(userMapper.mapToUser(userDTO), HttpStatus.OK);
    }
}

