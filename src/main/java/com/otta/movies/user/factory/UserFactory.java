package com.otta.movies.user.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.otta.movies.user.entity.Role;
import com.otta.movies.user.entity.User;
import com.otta.movies.user.model.UserInformation;

/**
 * Componente para criar um novo {@link User} com os valores passados por {@link UserInformation}, criptografando a senha.
 * @author Guilherme
 *
 */
@Component
public class UserFactory {
    private static final int ACTIVE_USER = 1;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserFactory(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public User create(UserInformation userInformation, Role role) {
        User user = new User();

        user.setName(userInformation.getName());
        user.setEmail(userInformation.getEmail());
        user.setPassword(passwordEncoder.encode(userInformation.getPassword()));
        user.setActive(ACTIVE_USER);
        user.setRole(role);

        return user;
    }
}
