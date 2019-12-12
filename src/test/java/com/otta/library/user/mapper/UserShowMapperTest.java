package com.otta.library.user.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.BDDMockito.given;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.otta.library.user.entity.User;
import com.otta.library.user.model.UserShow;

@ExtendWith(MockitoExtension.class)
public class UserShowMapperTest {
    private static final String NAME = "name";
    private static final String EMAIL = "email";

    @InjectMocks
    private UserShowMapper userShowMapper;

    @Mock
    private User user;

    @BeforeEach
    protected void setUp() throws Exception {
        given(user.getName()).willReturn(NAME);
        given(user.getEmail()).willReturn(EMAIL);
    }

    @Test
    public void shouldMapNamePropertie() throws Exception {
        // given
        // when
        UserShow userShow = userShowMapper.map(user);
        // then
        assertEquals(NAME, userShow.getName());
    }

    @Test
    public void shouldMapEmailPropertie() throws Exception {
        // given
        // when
        UserShow userShow = userShowMapper.map(user);
        // then
        assertEquals(EMAIL, userShow.getEmail());
    }
}
