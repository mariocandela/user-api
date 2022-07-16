package controller;

import com.noname.userapi.controller.EtlApiDelegateImpl;
import com.noname.userapi.service.User;
import com.noname.userapi.service.UserMapperImpl;
import com.noname.userapi.service.UserService;
import io.swagger.model.UserDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Base64;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class EtlApiDelegateImplTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private EtlApiDelegateImpl etlApiDelegate;

    private String csvUsers = """
            NAME;SURNAME;EMAIL;ADDRESS
            maria;mungo;maria.mungo@gmail.com;via dei test 2
            andrea;gerace;prova2@gmail.com;via dei test 12345
            """;

    private String csvUsersInvalid = """
            NAME;SURNAME;EMAIL;ADDRESS
            maria;mungo;maria.mungo@gmail.com;
            andrea;gerace;prova2@gmail.com;via dei test 12345
            """;

    @BeforeEach
    public void setup(){
        etlApiDelegate = new EtlApiDelegateImpl(userService, new UserMapperImpl());
    }

    @Test
    void givenBodyCSVUsersBase64_validCSV_whenImportCSVUsers_thenReturnResponseEntityOK(){
        // given
        given(userService.create(anyList())).willReturn(List.of());

        // when
        ResponseEntity<List<UserDTO>> listResponseEntityUsers = etlApiDelegate.importCSVUsers(Base64.getEncoder().encodeToString(csvUsers.getBytes()));

        // then
        assertThat(listResponseEntityUsers).isNotNull();
        assertThat(listResponseEntityUsers.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void givenBodyCSVUsersBase64_invalidCSV_whenImportCSVUsers_thenThrowCsvRequiredFieldEmptyException(){
        // when
        String csvUsersInvalidBase64 = Base64.getEncoder().encodeToString(csvUsersInvalid.getBytes());

        Exception exception = assertThrows(RuntimeException.class, () ->
            etlApiDelegate.importCSVUsers(csvUsersInvalidBase64)
        );

        // then
        verify(userService, never()).create(anyList());
        assertThat(exception.getMessage()).isEqualTo("Error parsing CSV line: 2. [maria,mungo,maria.mungo@gmail.com,]");
    }
}
