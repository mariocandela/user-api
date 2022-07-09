package com.noname.userapi.service;

import com.noname.userapi.dal.models.UserItem;
import com.noname.userapi.dal.repositories.UserItemRepository;
import com.noname.userapi.dto.UserDTO;
import com.noname.userapi.exception.UserAlreadyExistsException;
import com.noname.userapi.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserItemRepository userItemRepository;

    private final UserMapper userMapper;

    @Override
    public UserDTO create(UserDTO userDTO) throws UserAlreadyExistsException {
        if (userItemRepository.existsById(userDTO.getEmail())){
            throw new UserAlreadyExistsException();
        }

        UserItem userPersistent = userItemRepository.save(userMapper.mapToUserItem(userDTO));
        return userMapper.mapToUserDTO(userPersistent);
    }

    @Override
    public void delete(String email) throws UserNotFoundException {
        if (!userItemRepository.existsById(email)){
            throw new UserNotFoundException();
        }
        userItemRepository.deleteById(email);
    }

    @Override
    public UserDTO update(String email, UserDTO userDTO) throws UserNotFoundException {
        UserItem userItem = userItemRepository.findById(email).orElseThrow(UserNotFoundException::new);

        return userMapper.mapToUserDTO(userItem);
    }

    @Override
    public Optional<UserDTO> getByEmail(String email) {
        Optional<UserItem> userItem = userItemRepository.findById(email);

        if (userItem.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(userMapper.mapToUserDTO(userItem.get()));
    }

    @Override
    public List<UserDTO> findUsers(Optional<String> name, Optional<String> surname) {
        //TODO userItemRepository.findAll(new UserItem(name.orElse, surname));
        return Collections.emptyList();
    }
}
