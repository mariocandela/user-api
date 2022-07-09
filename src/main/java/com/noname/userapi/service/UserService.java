package com.noname.userapi.service;

import com.noname.userapi.dto.UserDTO;
import com.noname.userapi.exception.UserAlreadyExistsException;
import com.noname.userapi.exception.UserNotFoundException;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserDTO create(UserDTO userDTO) throws UserAlreadyExistsException;
    void delete(String email) throws UserNotFoundException;
    UserDTO update(String email, UserDTO userDTO) throws UserNotFoundException;
    Optional<UserDTO> getByEmail(String email);

    List<UserDTO> findUsers(Optional<String> name, Optional<String> surname);
}
