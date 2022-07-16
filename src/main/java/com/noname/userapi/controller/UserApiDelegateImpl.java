package com.noname.userapi.controller;

import com.noname.userapi.service.User;
import com.noname.userapi.exception.UserNotFoundException;
import com.noname.userapi.service.UserMapper;
import com.noname.userapi.service.UserService;
import io.swagger.api.UsersApiDelegate;
import io.swagger.model.UserDTO;
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
    public ResponseEntity<UserDTO> createUser(UserDTO body) {
        User userInput = userMapper.mapToUser(body);
        User userStored = userService.create(userInput);
        return new ResponseEntity<>(userMapper.mapToUserDTO(userStored), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> deleteUser(String email) {
        userService.delete(email);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @Override
    public ResponseEntity<List<UserDTO>> findUser(String name, String surname) {
        List<User> usersDTO = userService.findUsers(name, surname);
        return new ResponseEntity<>(userMapper.mapToListUserDTO(usersDTO), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserDTO> getUser(String  email) {
        User user = userService.getByEmail(email).orElseThrow(UserNotFoundException::new);
        return new ResponseEntity<>(userMapper.mapToUserDTO(user), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserDTO> updateUser(String email, UserDTO body) {
        User user = userService.update(email, userMapper.mapToUser(body));
        return new ResponseEntity<>(userMapper.mapToUserDTO(user), HttpStatus.OK);
    }
}

