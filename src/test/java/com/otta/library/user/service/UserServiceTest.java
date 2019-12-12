package com.otta.library.user.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.empty;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

import static org.mockito.BDDMockito.given;

import java.util.Collection;
import java.util.Optional;

import org.assertj.core.util.IterableUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.otta.library.user.entity.Role;
import com.otta.library.user.entity.User;
import com.otta.library.user.factory.UserFactory;
import com.otta.library.user.mapper.UserShowMapper;
import com.otta.library.user.model.UserInformation;
import com.otta.library.user.model.UserShow;
import com.otta.library.user.repository.RoleRepository;
import com.otta.library.user.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private RoleRepository roleRepository;
    @Mock
    private UserFactory userFactory;
    @Mock
    private UserShowMapper userMapper;
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserService userService;

    @Mock
    private UserInformation userInformation;
    @Mock
    private Role role;
    @Mock
    private User user;
    @Mock
    private User userInDatabaseA;
    @Mock
    private User userInDatabaseB;
    @Mock
    private UserShow userShowMappedA;
    @Mock
    private UserShow userShowMappedB;

    @BeforeEach
    protected void setUp() throws Exception {
    }

    @Test
    public void shouldCorrectlySaveUser() throws Exception {
        // given
        given(roleRepository.findById(BDDMockito.anyLong())).willReturn(Optional.of(role));
        given(userFactory.create(userInformation, role)).willReturn(user);
        // when
        userService.saveUser(userInformation);
        // then
        verify(userRepository).save(user);
    }

    @Test
    public void shouldCorrectlyReturnUserInfomation() throws Exception {
        // given
        given(roleRepository.findById(BDDMockito.anyLong())).willReturn(Optional.of(role));
        given(userFactory.create(userInformation, role)).willReturn(user);
        // when
        UserInformation actualUserInformation = userService.saveUser(userInformation);
        // then
        assertEquals(userInformation, actualUserInformation);
    }

    @Test
    public void shouldThrowsIllegalStateExceptionWhenUserRoleIsNotPresent() throws Exception {
        // given
        given(roleRepository.findById(BDDMockito.anyLong())).willThrow(new IllegalStateException());
        // when
        Assertions.assertThrows(IllegalStateException.class, () -> {
            userService.saveUser(userInformation);
        });
        // then
    }

    @Test
    public void shouldCorrectlyListAllUsers() throws Exception {
        // given
        given(userRepository.findAll()).willReturn(IterableUtil.iterable(userInDatabaseA, userInDatabaseB));
        given(userMapper.map(userInDatabaseA)).willReturn(userShowMappedA);
        given(userMapper.map(userInDatabaseB)).willReturn(userShowMappedB);
        // when
        Collection<UserShow> allUsers = userService.listAllUsers();
        // then
        assertThat(allUsers, containsInAnyOrder(userShowMappedA, userShowMappedB));
    }

    @Test
    public void shouldCorrectlyListEmptyUsersList() throws Exception {
        // given
        given(userRepository.findAll()).willReturn(IterableUtil.iterable());
        // when
        Collection<UserShow> allUsers = userService.listAllUsers();
        // then
        assertThat(allUsers, empty());
    }
}
