package com.noname.userapi.service;

import com.noname.userapi.dal.documents.UserItem;
import com.noname.userapi.dal.repositories.UserItemRepository;
import com.noname.userapi.exception.UserAlreadyExistsException;
import com.noname.userapi.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserItemRepository userItemRepository;

    private final UserMapper userMapper;

    @Override
    @Transactional
    public User create(User user) throws UserAlreadyExistsException {
        if (userItemRepository.existsById(user.getEmail())){
            throw new UserAlreadyExistsException();
        }

        UserItem userPersistent = userItemRepository.save(userMapper.mapToUserItem(user));
        return userMapper.mapToUser(userPersistent);
    }

    @Override
    @Transactional
    public List<User> create(List<User> users) {
        List<UserItem> usersItem = userItemRepository.saveAll(userMapper.mapToListUserItem(users));
        return userMapper.listUserItemMapToListUser(usersItem);
    }

    @Override
    @Transactional
    public void delete(String email) throws UserNotFoundException {
        if (!userItemRepository.existsById(email)){
            throw new UserNotFoundException();
        }
        userItemRepository.deleteById(email);
    }

    @Override
    @Transactional
    public User update(String email, User user) throws UserNotFoundException {
        UserItem userItem = userItemRepository.findById(email).orElseThrow(UserNotFoundException::new);
        if (!user.getEmail().equals(userItem.getEmail())) {
            userItemRepository.deleteById(email);
        }
        userMapper.update(user, userItem);
        UserItem userItemUpdated = userItemRepository.save(userItem);
        return userMapper.mapToUser(userItemUpdated);
    }

    @Override
    public Optional<User> getByEmail(String email) {
        Optional<UserItem> userItem = userItemRepository.findById(email);

        if (userItem.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(userMapper.mapToUser(userItem.get()));
    }

    @Override
    public List<User> findUsers(String name, String surname) {
        UserItem userItem = new UserItem();

        userItem.setName(name);
        userItem.setSurname(surname);

        return userMapper.listUserItemMapToListUser(userItemRepository.findAll(Example.of(userItem)));
    }
}
