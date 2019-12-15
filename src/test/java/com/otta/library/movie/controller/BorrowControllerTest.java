package com.otta.library.movie.controller;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.otta.library.movie.model.BorrowReturnInformation;
import com.otta.library.movie.model.MovieBorrow;
import com.otta.library.movie.service.BorrowService;
import com.otta.library.pagination.model.PageEndpoint;

/**
 * Testes unitários do componente {@link BorrowController}.
 * 
 * @author Guilherme
 *
 */
@ExtendWith(MockitoExtension.class)
public class BorrowControllerTest {
    private static final int PAGE_ZERO = 0;
    private static final int PAGE_ONE = 1;

    @Mock
    private BorrowService borrowService;
    @InjectMocks
    private BorrowController borrowController;

    @Mock
    private MovieBorrow movieBorrow;
    @Mock
    private BorrowReturnInformation borrowReturnInformation;

    @BeforeEach
    protected void setUp() throws Exception {
    }

    @Test
    public void shouldCallBorrowServiceWhenBorrowMovie() {
        // given
        // when
        borrowController.borrowMovie(movieBorrow);
        // then
        verify(borrowService).borrowMovie(movieBorrow);
    }

    @Test
    public void shouldCallBorrowServiceWhenListRentedUnits() {
        // given
        // when
        borrowController.listRentedUnits();
        // then
        verify(borrowService).listRentsByLoggedUser(PageEndpoint.BORROW, PAGE_ZERO);
    }

    @Test
    public void shouldCallBorrowServiceWhenListRentedUnitsInt() {
        // given
        // when
        borrowController.listRentedUnits(PAGE_ONE);
        // then
        verify(borrowService).listRentsByLoggedUser(PageEndpoint.BORROW, PAGE_ONE);
    }

    @Test
    public void shouldCallBorrowServiceWhenReturnBorrowMovie() {
        // given
        // when
        borrowController.returnBorrowMovie(borrowReturnInformation);
        // then
        verify(borrowService).returnMovie(borrowReturnInformation);
    }

}
