package com.otta.movies.user.validation;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.mockito.BDDMockito.given;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.otta.movies.user.model.UserInformation;
import com.otta.movies.user.validation.UserCreationValidator;

/**
 * Tetes unitários do componente {@link UserCreationValidator}.
 * 
 * @author Guilherme
 *
 */
@ExtendWith(MockitoExtension.class)
public class UserCreationValidatorTest {
    private static final String NAME = "name";
    private static final String EMAIL = "name@email.com";
    private static final String PASSWORD = "password";
    private static final String EMPTY = "";
    private static final String INVALID_EMAIL = "nameblahblah";
    private static final String INVALID_PASSWORD = "veryverylargestringtouseinpasswordfield";

    @InjectMocks
    private UserCreationValidator userCreationValidator;

    @Mock
    private UserInformation userInformation;

    @BeforeEach
    protected void setUp() {
        given(userInformation.getName()).willReturn(NAME);
        given(userInformation.getEmail()).willReturn(EMAIL);
        given(userInformation.getPassword()).willReturn(PASSWORD);
    }

    @Test
    public void shouldReturnTrueWhenUserInformationIsValud() {
        // given
        // when
        boolean actualValue = userCreationValidator.validate(userInformation);
        // then
        assertTrue(actualValue);
    }

    @Test
    public void shouldReturnFalseWhenUserInformationNameIsEmpty() {
        // given
        given(userInformation.getName()).willReturn(EMPTY);
        // when
        boolean actualValue = userCreationValidator.validate(userInformation);
        // then
        assertFalse(actualValue);
    }

    @Test
    public void shouldReturnFalseWhenUserInformationEmailIsEmpty() {
        // given
        given(userInformation.getEmail()).willReturn(EMPTY);
        // when
        boolean actualValue = userCreationValidator.validate(userInformation);
        // then
        assertFalse(actualValue);
    }

    @Test
    public void shouldReturnFalseWhenUserInformationEmailIsInvalid() {
        // given
        given(userInformation.getEmail()).willReturn(INVALID_EMAIL);
        // when
        boolean actualValue = userCreationValidator.validate(userInformation);
        // then
        assertFalse(actualValue);
    }

    @Test
    public void shouldReturnFalseWhenUserInformationPasswordIsEmpty() {
        // given
        given(userInformation.getPassword()).willReturn(EMPTY);
        // when
        boolean actualValue = userCreationValidator.validate(userInformation);
        // then
        assertFalse(actualValue);
    }

    @Test
    public void shouldReturnFalseWhenUserInformationPasswordIsInvalid() {
        // given
        given(userInformation.getPassword()).willReturn(INVALID_PASSWORD);
        // when
        boolean actualValue = userCreationValidator.validate(userInformation);
        // then
        assertFalse(actualValue);
    }
}
