package com.otta.library.movie.controller;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.otta.library.movie.model.BorrowReturnInformation;
import com.otta.library.movie.model.MovieBorrow;
import com.otta.library.movie.model.MovieInformation;
import com.otta.library.movie.model.MovieSearchInformation;
import com.otta.library.movie.service.BorrowService;
import com.otta.library.movie.service.MovieService;
import com.otta.library.pagination.model.PageEndpoint;

/**
 * Testes unitários do componente {@link movieController}.
 * 
 * @author Guilherme
 *
 */
@ExtendWith(MockitoExtension.class)
public class MovieControllerTest {
    private static final int PAGE_ZERO = 0;
    private static final int PAGE_ONE = 1;

    @Mock
    private BorrowService borrowService;
    @Mock
    private MovieService movieService;
    @InjectMocks
    private MovieController movieController;

    @Mock
    private MovieSearchInformation movieSearchInformation;
    @Mock
    private MovieInformation movieInformation;
    @Mock
    private MovieBorrow movieBorrow;
    @Mock
    private BorrowReturnInformation borrowReturnInformation;

    @Test
    public void shouldCallMoviServiceWhenReturnMovies() {
        // given
        // when
        movieController.returnMovies();
        // then
        verify(movieService).listMovies(PageEndpoint.MOVIE, PAGE_ZERO);
    }

    @Test
    public void shouldCallMoviServiceWhenReturnMoviesInt() {
        // given
        // when
        movieController.returnMovies(PAGE_ONE);
        // then
        verify(movieService).listMovies(PageEndpoint.MOVIE, PAGE_ONE);
    }

    @Test
    public void shouldCallMoviServiceWhenReturnMoviesMovieSearchInformation() {
        // given
        // when
        movieController.returnMovies(movieSearchInformation);
        // then
        verify(movieService).listMovies(PageEndpoint.SEARCH, movieSearchInformation, PAGE_ZERO);
    }

    @Test
    public void shouldCallMoviServiceWhenReturnMoviesMovieSearchInformationInt() {
        // given
        // when
        movieController.returnMovies(movieSearchInformation, PAGE_ONE);
        // then
        verify(movieService).listMovies(PageEndpoint.SEARCH, movieSearchInformation, PAGE_ONE);
    }

    @Test
    public void shouldCallMoviServiceWhenSaveMovie() {
        // given
        // when
        movieController.saveMovie(movieInformation);
        // then
        verify(movieService).saveMovie(movieInformation);
    }

    @Test
    public void shouldCallBorrowServiceWhenBorrowMovie() {
        // given
        // when
        movieController.borrowMovie(movieBorrow);
        // then
        verify(borrowService).borrowMovie(movieBorrow);
    }

    @Test
    public void shouldCallBorrowServiceWhenListRentedUnits() {
        // given
        // when
        movieController.listRentedUnits();
        // then
        verify(borrowService).listRentsByLoggedUser(PageEndpoint.BORROW, PAGE_ZERO);
    }

    @Test
    public void shouldCallBorrowServiceWhenListRentedUnitsInt() {
        // given
        // when
        movieController.listRentedUnits(PAGE_ONE);
        // then
        verify(borrowService).listRentsByLoggedUser(PageEndpoint.BORROW, PAGE_ONE);
    }

    @Test
    public void shouldCallBorrowServiceWhenReturnBorrowMovie() {
        // given
        // when
        movieController.returnBorrowMovie(borrowReturnInformation);
        // then
        verify(borrowService).returnMovie(borrowReturnInformation);
    }

}
