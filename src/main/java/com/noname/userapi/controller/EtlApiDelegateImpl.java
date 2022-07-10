package com.noname.userapi.controller;

import com.noname.userapi.dto.UserDTO;
import com.noname.userapi.service.UserMapper;
import com.noname.userapi.service.UserService;
import com.opencsv.bean.CsvToBeanBuilder;
import io.swagger.api.EtlApiDelegate;
import io.swagger.model.User;
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
    public ResponseEntity<List<User>> importCSVUsers(String body) {
        byte[] decodedBytes = Base64.getDecoder().decode(body);
        String csvUsers = new String(decodedBytes);

        List<UserDTO> usersDTOToImport = new CsvToBeanBuilder<UserDTO>(new StringReader(csvUsers))
                .withType(UserDTO.class)
                .withSeparator(';')
                .build()
                .parse();

        List<UserDTO> usersDTOStored = userService.create(usersDTOToImport);

        return new ResponseEntity<>(userMapper.mapToListUser(usersDTOStored), HttpStatus.OK);
    }
}
