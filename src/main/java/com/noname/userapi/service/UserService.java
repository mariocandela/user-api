package com.noname.userapi.service;

import com.noname.userapi.exception.UserAlreadyExistsException;
import com.noname.userapi.exception.UserNotFoundException;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User create(User user) throws UserAlreadyExistsException;
    List<User> create(List<User> users);
    void delete(String email) throws UserNotFoundException;
    User update(String email, User user) throws UserNotFoundException;
    Optional<User> getByEmail(String email);
    List<User> findUsers(String name, String surname);
}
