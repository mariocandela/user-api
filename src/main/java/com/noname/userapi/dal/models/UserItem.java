package com.noname.userapi.dal.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Document("useritems")
public class UserItem {
    @Id
    private String email;
    private String name;
    private String surname;
    private String address;
}
