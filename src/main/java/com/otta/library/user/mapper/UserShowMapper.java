package com.otta.library.user.mapper;

import org.springframework.stereotype.Component;

import com.otta.library.user.entity.User;
import com.otta.library.user.model.UserShow;

/**
 * Componente para mapear um {@link User} em um {@link UserShow}.
 * @author Guilherme
 *
 */
@Component
public class UserShowMapper {

    public UserShow map(User user) {
        return new UserShow(user.getName(), user.getEmail());
    }
}
