package com.noname.userapi.dto;

import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class UserDTO {

    @CsvBindByName(column = "email", required = true)
    private String email;

    @CsvBindByName(column = "name", required = true)
    private String name;

    @CsvBindByName(column = "surname", required = true)
    private String surname;

    @CsvBindByName(column = "address", required = true)
    private String address;
}