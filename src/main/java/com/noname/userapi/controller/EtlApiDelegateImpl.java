package com.noname.userapi.controller;

import com.noname.userapi.service.User;
import com.noname.userapi.service.UserMapper;
import com.noname.userapi.service.UserService;
import com.opencsv.bean.CsvToBeanBuilder;
import io.swagger.api.EtlApiDelegate;
import io.swagger.model.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.StringReader;
import java.util.Base64;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EtlApiDelegateImpl implements EtlApiDelegate {

    private final UserService userService;

    private final UserMapper userMapper;

    @Override
    public ResponseEntity<List<UserDTO>> importCSVUsers(String body) {
        byte[] decodedBytes = Base64.getDecoder().decode(body);
        String csvUsers = new String(decodedBytes);

        List<User> usersDTOToImport = new CsvToBeanBuilder<User>(new StringReader(csvUsers))
                .withType(User.class)
                .withSeparator(';')
                .build()
                .parse();

        List<User> usersStored = userService.create(usersDTOToImport);

        return new ResponseEntity<>(userMapper.mapToListUserDTO(usersStored), HttpStatus.OK);
    }
}
