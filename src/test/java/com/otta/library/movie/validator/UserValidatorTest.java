package com.otta.library.movie.validator;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.mockito.BDDMockito.given;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.otta.library.user.entity.User;
import com.otta.library.user.factory.LoggedUserFactory;

/**
 * Testes unitários do componente {@link UserValidator}.
 * 
 * @author Guilherme
 *
 */
@ExtendWith(MockitoExtension.class)
public class UserValidatorTest {
    private static final long LOGGED_USER_ID = 5;
    private static final long ANOTHER_ID = 1;

    @Mock
    private LoggedUserFactory loggedUserFactory;
    @InjectMocks
    private UserValidator userValidator;

    @Mock
    private User user;

    @BeforeEach
    protected void setUp() throws Exception {
    }

    @Test
    public void shouldReturnTrueWhenIsSameIdInParameterAndUser() throws Exception {
        // given
        given(loggedUserFactory.get()).willReturn(Optional.of(user));
        given(user.getId()).willReturn(LOGGED_USER_ID);
        // when
        boolean actualValue = userValidator.validate(LOGGED_USER_ID);
        // then
        assertTrue(actualValue);
    }

    @Test
    public void shouldReturnFalseWhenIsNotSameIdInParameterAndUser() throws Exception {
        // given
        given(loggedUserFactory.get()).willReturn(Optional.of(user));
        given(user.getId()).willReturn(LOGGED_USER_ID);
        // when
        boolean actualValue = userValidator.validate(ANOTHER_ID);
        // then
        assertFalse(actualValue);
    }

    @Test
    public void shouldThrowIllegalStateExceptionWhenCouldNotFindLoggedUser() throws Exception {
        // given
        given(loggedUserFactory.get()).willReturn(Optional.empty());
        // when
        Assertions.assertThrows(IllegalStateException.class, () -> {
            userValidator.validate(LOGGED_USER_ID);
        });
        // then
    }
}
