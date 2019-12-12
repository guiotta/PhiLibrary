package com.otta.library.user.service;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.otta.library.user.entity.Role;
import com.otta.library.user.entity.User;
import com.otta.library.user.factory.UserFactory;
import com.otta.library.user.mapper.UserShowMapper;
import com.otta.library.user.model.UserInformation;
import com.otta.library.user.model.UserShow;
import com.otta.library.user.repository.RoleRepository;
import com.otta.library.user.repository.UserRepository;

@Service
public class UserService {
    private static final int USER_ROLE_ID = 1;

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserFactory userFactory;
    private final UserShowMapper userMapper;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, UserFactory userFactory,
            UserShowMapper userMapper) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userFactory = userFactory;
        this.userMapper = userMapper;
    }

    @Transactional
    public UserInformation saveUser(UserInformation userInformation) {
        Role role = roleRepository.findById(Long.valueOf(USER_ROLE_ID)).orElseThrow(IllegalStateException::new);
        User user = userFactory.create(userInformation, role);

        userRepository.save(user);
        return userInformation;
    }

    @Transactional(readOnly = true)
    public Collection<UserShow> listAllUsers() {
        Iterable<User> allUsers = userRepository.findAll();

        return StreamSupport.stream(allUsers.spliterator(), false)
                .map(user -> userMapper.map(user))
                .collect(Collectors.toList());
    }
}
