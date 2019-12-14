package com.otta.library.movie.factory;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.otta.library.movie.entity.Borrow;
import com.otta.library.user.entity.User;

/**
 * Testes unitários do componente {@link BorrowFactory}.
 * 
 * @author Guilherme
 *
 */
@ExtendWith(MockitoExtension.class)
public class BorrowFactoryTest {
    private static final long UNIT_ID = 1;

    @InjectMocks
    private BorrowFactory factory;

    @Mock
    private User user;

    @Test
    public void shouldCorrectlyCreateBorrowWithUser() throws Exception {
        // given
        // when
        Borrow actualBorrow = factory.create(user, UNIT_ID);
        // then
        assertEquals(user, actualBorrow.getUser());
    }

    @Test
    public void shouldCorrectlyCreateBorrowWithIdUnit() throws Exception {
        // given
        // when
        Borrow actualBorrow = factory.create(user, UNIT_ID);
        // then
        assertEquals(UNIT_ID, actualBorrow.getIdUnit());
    }

    @Test
    public void shouldCorrectlyCreateBorrowWithBegin() throws Exception {
        // given
        // when
        Borrow actualBorrow = factory.create(user, UNIT_ID);
        // then
        assertThat(actualBorrow.getBegin(), notNullValue());
    }

    @Test
    public void shouldCorrectlyCreateBorrowWithEndNull() throws Exception {
        // given
        // when
        Borrow actualBorrow = factory.create(user, UNIT_ID);
        // then
        assertThat(actualBorrow.getEnd(), nullValue());
    }

}
