package com.otta.movies.user.service;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.otta.movies.user.entity.Role;
import com.otta.movies.user.entity.User;
import com.otta.movies.user.factory.UserFactory;
import com.otta.movies.user.mapper.UserShowMapper;
import com.otta.movies.user.model.UserInformation;
import com.otta.movies.user.model.UserShow;
import com.otta.movies.user.repository.RoleRepository;
import com.otta.movies.user.repository.UserRepository;
import com.otta.movies.user.validation.UserCreationValidator;

@Service
public class UserService {
    private static final int USER_ROLE_ID = 1;

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserFactory userFactory;
    private final UserShowMapper userMapper;
    private final UserCreationValidator userCreationValidator;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, UserFactory userFactory,
            UserShowMapper userMapper, UserCreationValidator userCreationValidator) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userFactory = userFactory;
        this.userMapper = userMapper;
        this.userCreationValidator = userCreationValidator;
    }

    @Transactional
    public UserInformation saveUser(UserInformation userInformation) {
        if (userCreationValidator.validate(userInformation)) {
            Role role = roleRepository.findById(Long.valueOf(USER_ROLE_ID)).orElseThrow(IllegalStateException::new);
            User user = userFactory.create(userInformation, role);

            userRepository.save(user);
            return userInformation;
        }
        throw new IllegalArgumentException("User needs a Name, a valid E-mail and a Password with 4-16 characters.");
    }

    @Transactional(readOnly = true)
    public Collection<UserShow> listAllUsers() {
        Iterable<User> allUsers = userRepository.findAll();

        return StreamSupport.stream(allUsers.spliterator(), false)
                .map(user -> userMapper.map(user))
                .collect(Collectors.toList());
    }
}
