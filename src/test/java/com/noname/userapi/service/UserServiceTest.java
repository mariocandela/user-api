package com.noname.userapi.service;

import com.noname.userapi.dal.documents.UserItem;
import com.noname.userapi.dal.repositories.UserItemRepository;
import com.noname.userapi.dto.UserDTO;
import com.noname.userapi.exception.UserAlreadyExistsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

/**
 * Example of Unit Test using BDD
 */
@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserItemRepository userItemRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private UserDTO userDTO;

    private UserItem userItem;

    @BeforeEach
    public void setup(){
        userService = new UserServiceImpl(userItemRepository, new UserMapperImpl());
        userDTO = new UserDTO("test@gmail.com", "mario", "rossi", "Via dei test");
        userItem = new UserItem("test@gmail.com", "mario", "rossi", "Via dei test");
    }

    @Test
    void givenUserDTOObject_whenCreateUserDTO_thenReturnUserDTOObject(){
        // given
        given(userItemRepository.existsById(userDTO.getEmail()))
                .willReturn(false);

        given(userItemRepository.save(userItem)).willReturn(userItem);

        // when
        UserDTO savedUserDTO = userService.create(userDTO);

        // then
        assertThat(savedUserDTO).isNotNull();
        assertThat(savedUserDTO.getEmail()).isEqualTo(userItem.getEmail());
        assertThat(savedUserDTO.getAddress()).isEqualTo(userItem.getAddress());
        assertThat(savedUserDTO.getSurname()).isEqualTo(userItem.getSurname());
        assertThat(savedUserDTO.getName()).isEqualTo(userItem.getName());
    }

    @Test
    void givenUserDTOObject_whenCreateUserDTO_thenThrowUserAlreadyExistsException(){
        // given
        given(userItemRepository.existsById(userDTO.getEmail()))
                .willReturn(true);

        // when
        assertThrows(UserAlreadyExistsException.class, () ->
            userService.create(userDTO)
        );

        // then
        verify(userItemRepository, never()).save(any(UserItem.class));
    }
}
