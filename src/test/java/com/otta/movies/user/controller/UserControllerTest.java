package com.otta.movies.user.controller;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.otta.movies.user.controller.UserController;
import com.otta.movies.user.model.UserInformation;
import com.otta.movies.user.service.UserService;

/**
 * Teste unitário da controller {@link UserController}.
 * 
 * @author Guilherme
 *
 */
@ExtendWith(MockitoExtension.class)
public class UserControllerTest {
    @Mock
    private UserService userService;
    @InjectMocks
    private UserController userController;

    @Mock
    private UserInformation userInformation;

    @Test
    public void shouldCallUserServiceWhenSave() {
        // given
        // when
        userController.save(userInformation);
        // then
        verify(userService).saveUser(userInformation);
    }

    @Test
    public void shouldCallUserServiceWhenList() {
        // given
        // when
        userController.list();
        // then
        verify(userService).listAllUsers();
    }

}
