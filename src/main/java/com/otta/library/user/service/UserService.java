package com.otta.library.user.service;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.otta.library.user.entity.Role;
import com.otta.library.user.entity.User;
import com.otta.library.user.model.UserInformation;
import com.otta.library.user.model.UserShow;
import com.otta.library.user.repository.RoleRepository;
import com.otta.library.user.repository.UserRepository;

@Service
public class UserService {
    private static final int ACTIVE_USER = 1;
    
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository,
            PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserInformation saveUser(UserInformation userInformation) {
        Role role = roleRepository.findById(Long.valueOf(1)).orElseThrow(IllegalArgumentException::new);
        User user = new User();

        user.setName(userInformation.getName());
        user.setEmail(userInformation.getEmail());
        user.setPassword(passwordEncoder.encode(userInformation.getPassword()));
        user.setActive(ACTIVE_USER);
        user.setRole(role);

        userRepository.save(user);
        return userInformation;
    }

    public Collection<UserShow> listAllUsers() {
        Iterable<User> allUsers = userRepository.findAll();

        return StreamSupport.stream(allUsers.spliterator(), false)
                .map(user -> new UserShow(user.getName(), user.getEmail()))
                .collect(Collectors.toList());
    }
}
