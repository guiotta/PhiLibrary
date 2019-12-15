package com.otta.library.user.factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;

import com.otta.library.user.entity.User;
import com.otta.library.user.repository.UserRepository;

/**
 * Testes unitários do componente {@link LoggedUserFactory}.
 * 
 * @author Guilherme
 *
 */
@ExtendWith(MockitoExtension.class)
public class LoggedUserFactoryTest {
    private static final String EMAIL = "email";

    @Mock
    private UserRepository userRepository;
    private LoggedUserFactory loggedUserFactory;

    @Mock
    private Authentication auth;
    @Mock
    private User user;

    @BeforeEach
    protected void setUp() throws Exception {
        loggedUserFactory = spy(new LoggedUserFactory(userRepository));
        doReturn(auth).when(loggedUserFactory).getAuthentication();
        doReturn(EMAIL).when(auth).getName();
        doReturn(Optional.of(user)).when(userRepository).findByEmail(EMAIL);
    }

    @Test
    public void shouldCorrectlyGetUserFromDatabase() throws Exception {
        // given
        // when
        Optional<User> actualValue = loggedUserFactory.get();
        // then
        assertEquals(Optional.of(user), actualValue);
    }

}
