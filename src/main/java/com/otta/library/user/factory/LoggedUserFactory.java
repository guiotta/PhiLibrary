package com.otta.library.user.factory;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.google.common.annotations.VisibleForTesting;
import com.otta.library.user.entity.User;
import com.otta.library.user.repository.UserRepository;

/**
 * Componente para obter o {@link User} representando o usuário logado no sistema.
 * @author Guilherme
 *
 */
@Component
public class LoggedUserFactory {
    private final UserRepository userRepository;

    @Autowired
    public LoggedUserFactory(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> get() {
        Authentication auth = this.getAuthentication();
        return userRepository.findByEmail(auth.getName());
    }

    @VisibleForTesting
    Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
