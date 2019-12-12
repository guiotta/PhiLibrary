package com.otta.library.user.factory;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.BDDMockito.given;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.otta.library.user.entity.Role;
import com.otta.library.user.entity.User;
import com.otta.library.user.model.UserInformation;

@ExtendWith(MockitoExtension.class)
public class UserFactoryTest {
    private static final String NAME = "name";
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";
    private static final String ENCODED_PASSWORD = "encoded";
    private static final int ACTIVE_USER_CODE = 1;

    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    private UserFactory userFactory;

    @Mock
    private UserInformation userInformation;
    @Mock
    private Role role;

    @BeforeEach
    protected void setUp() throws Exception {
        given(userInformation.getName()).willReturn(NAME);
        given(userInformation.getEmail()).willReturn(EMAIL);
        given(userInformation.getPassword()).willReturn(PASSWORD);
        given(passwordEncoder.encode(PASSWORD)).willReturn(ENCODED_PASSWORD);
    }

    @Test
    public void shouldCreateUserWithName() throws Exception {
        // given
        // when
        User user = userFactory.create(userInformation, role);
        // then
        assertEquals(NAME, user.getName());
    }

    @Test
    public void shouldCreateUserWithEmail() throws Exception {
        // given
        // when
        User user = userFactory.create(userInformation, role);
        // then
        assertEquals(EMAIL, user.getEmail());
    }

    @Test
    public void shouldCreateUserWithPassword() throws Exception {
        // given
        // when
        User user = userFactory.create(userInformation, role);
        // then
        assertEquals(ENCODED_PASSWORD, user.getPassword());
    }

    @Test
    public void shouldCreateUserWithActiveUser() throws Exception {
        // given
        // when
        User user = userFactory.create(userInformation, role);
        // then
        assertEquals(ACTIVE_USER_CODE, user.getActive());
    }

    @Test
    public void shouldCreateUserWithRole() throws Exception {
        // given
        // when
        User user = userFactory.create(userInformation, role);
        // then
        assertEquals(role, user.getRole());
    }
}
