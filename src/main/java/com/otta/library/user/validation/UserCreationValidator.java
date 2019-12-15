package com.otta.library.user.validation;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Component;

import com.otta.library.user.model.UserInformation;

/**
 * Componente para realizar a validação das informações do usuário que será criado.
 * @author Guilherme
 *
 */
@Component
public class UserCreationValidator {

    public boolean validate(UserInformation userInformation) {
        String name = userInformation.getName();
        String email = userInformation.getEmail();
        String password = userInformation.getPassword();

        return !name.trim().isEmpty() && EmailValidator.getInstance().isValid(email)
                && (password.trim().length() > 3 && password.trim().length() < 17);
    }
}
