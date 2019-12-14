package com.otta.library.movie.validator;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.otta.library.user.entity.User;
import com.otta.library.user.factory.LoggedUserFactory;

/**
 * Componente para verificar se um determinado ID, é o mesmo que representa o usuário logado na base de dados.
 * 
 * @author Guilherme
 *
 */
@Component
public class UserValidator {
    private final LoggedUserFactory loggedUserFactory;

    @Autowired
    public UserValidator(LoggedUserFactory loggedUserFactory) {
        this.loggedUserFactory = loggedUserFactory;
    }

    public boolean validate(long borrowUserId) {
        Optional<User> optionalLoggedUser = loggedUserFactory.get();

        if (optionalLoggedUser.isPresent()) {
            return optionalLoggedUser.get().getId() == borrowUserId;
        }
        throw new IllegalStateException("Could not found logged User in Session.");
    }
}
