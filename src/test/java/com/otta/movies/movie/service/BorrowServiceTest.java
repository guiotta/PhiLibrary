package com.otta.movies.movie.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

import static org.mockito.BDDMockito.given;

import java.util.Optional;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;

import com.otta.movies.movie.entity.Borrow;
import com.otta.movies.movie.entity.Movie;
import com.otta.movies.movie.entity.Unit;
import com.otta.movies.movie.factory.BorrowFactory;
import com.otta.movies.movie.factory.RentedMovieShowPageFactory;
import com.otta.movies.movie.get.UnitsToRentGetter;
import com.otta.movies.movie.model.BorrowReturnInformation;
import com.otta.movies.movie.model.MovieBorrow;
import com.otta.movies.movie.model.RentedMovieShow;
import com.otta.movies.movie.repository.MovieRepository;
import com.otta.movies.movie.repository.UnitRepository;
import com.otta.movies.movie.service.BorrowService;
import com.otta.movies.movie.update.UnitBorrowReturnEndSetter;
import com.otta.movies.movie.validator.UserValidator;
import com.otta.movies.pagination.Page;
import com.otta.movies.pagination.model.PageEndpoint;
import com.otta.movies.user.entity.User;
import com.otta.movies.user.factory.LoggedUserFactory;

/**
 * Testes unitários do componente {@link BorrowService}.
 * 
 * @author Guilherme
 *
 */
@ExtendWith(MockitoExtension.class)
public class BorrowServiceTest {
    private static final int CURRENT_PAGE = 0;
    private static final long MOVIE_ID = 100;
    private static final long UNIT_ID = 90;
    private static final long USER_ID = 80;
    private static final long BORROW_ID = 70;

    @Mock
    private BorrowFactory borrowFactory;
    @Mock
    private LoggedUserFactory loggedUserFactory;
    @Mock
    private MovieRepository movieRepository;
    @Mock
    private RentedMovieShowPageFactory rentedMovieShowPageFactory;
    @Mock
    private UnitBorrowReturnEndSetter unitBorrowReturnEndSetter;
    @Mock
    private UnitRepository unitRepository;
    @Mock
    private UnitsToRentGetter unitsToRentGetter;
    @Mock
    private UserValidator userValidator;
    @InjectMocks
    private BorrowService borrowService;

    @Mock
    private MovieBorrow movieBorrow;
    @Mock
    private Movie movie;
    @Mock
    private Unit unit;
    @Mock
    private User user;
    @Mock
    private Borrow borrow;
    @Mock
    private BorrowReturnInformation borrowReturnInformation;
    @Mock
    private org.springframework.data.domain.Page<Unit> unitsPage;
    @Mock
    private Page<RentedMovieShow> page;

    private PageEndpoint pageEndpoint = PageEndpoint.MOVIE;

    @BeforeEach
    protected void setUp() {
    }

    @Test
    public void shouldCorrectlySaveUnitAfterAddBorrow() {
        // given
        given(movieBorrow.getIdMovie()).willReturn(MOVIE_ID);
        given(unit.getId()).willReturn(UNIT_ID);
        given(movieRepository.findById(MOVIE_ID)).willReturn(Optional.of(movie));
        given(unitsToRentGetter.get(movie)).willReturn(Lists.list(unit));
        given(loggedUserFactory.get()).willReturn(Optional.of(user));
        given(borrowFactory.create(user, UNIT_ID)).willReturn(borrow);
        // when
        borrowService.borrowMovie(movieBorrow);
        // then
        verify(unit).addBorrow(borrow);
        verify(unitRepository).save(unit);
    }

    @Test
    public void shouldReturnMovieBorrow() {
        // given
        given(movieBorrow.getIdMovie()).willReturn(MOVIE_ID);
        given(unit.getId()).willReturn(UNIT_ID);
        given(movieRepository.findById(MOVIE_ID)).willReturn(Optional.of(movie));
        given(unitsToRentGetter.get(movie)).willReturn(Lists.list(unit));
        given(loggedUserFactory.get()).willReturn(Optional.of(user));
        given(borrowFactory.create(user, UNIT_ID)).willReturn(borrow);
        // when
        MovieBorrow actualValue = borrowService.borrowMovie(movieBorrow);
        // then
        assertEquals(movieBorrow, actualValue);
    }

    @Test
    public void shouldThrowIllegalArgumentsExceptionWhenSaveMovieBorrowWithouFindindMovie() {
        // given
        given(movieBorrow.getIdMovie()).willReturn(MOVIE_ID);
        given(movieRepository.findById(MOVIE_ID)).willReturn(Optional.empty());
        // when
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            borrowService.borrowMovie(movieBorrow);
        });
        // then
    }

    @Test
    public void shouldThrowIllegalArgumentsExceptionWhenSaveMovieBorrowWithouGettingUnitToRent() {
        // given
        given(movieBorrow.getIdMovie()).willReturn(MOVIE_ID);
        given(movieRepository.findById(MOVIE_ID)).willReturn(Optional.of(movie));
        given(unitsToRentGetter.get(movie)).willReturn(Lists.list());
        // when
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            borrowService.borrowMovie(movieBorrow);
        });
        // then
    }

    @Test
    public void shouldThrowIllegalStateExceptionWhenSaveMovieBorrowWithouFindLoggedUser() {
        // given
        given(movieBorrow.getIdMovie()).willReturn(MOVIE_ID);
        given(movieRepository.findById(MOVIE_ID)).willReturn(Optional.of(movie));
        given(unitsToRentGetter.get(movie)).willReturn(Lists.list(unit));
        given(loggedUserFactory.get()).willReturn(Optional.empty());
        // when
        Assertions.assertThrows(IllegalStateException.class, () -> {
            borrowService.borrowMovie(movieBorrow);
        });
        // then
    }

    @Test
    public void shouldSaveUnitUpdatedWithBorrowReturned() {
        // given
        given(borrowReturnInformation.getUserId()).willReturn(USER_ID);
        given(userValidator.validate(USER_ID)).willReturn(Boolean.TRUE);
        given(borrowReturnInformation.getBorrowId()).willReturn(BORROW_ID);
        given(unitBorrowReturnEndSetter.set(BORROW_ID)).willReturn(Optional.of(unit));
        // when
        borrowService.returnMovie(borrowReturnInformation);
        // then
        verify(unitRepository).save(unit);
    }

    @Test
    public void shouldReturnBorrowReturnInformationAfterSaveUnitUpdatedWithBorrowReturned() {
        // given
        given(borrowReturnInformation.getBorrowId()).willReturn(BORROW_ID);
        given(userValidator.validate(USER_ID)).willReturn(Boolean.TRUE);
        given(borrowReturnInformation.getUserId()).willReturn(USER_ID);
        given(unitBorrowReturnEndSetter.set(BORROW_ID)).willReturn(Optional.of(unit));
        // when
        BorrowReturnInformation actualValue = borrowService.returnMovie(borrowReturnInformation);
        // then
        assertEquals(borrowReturnInformation, actualValue);
    }

    @Test
    public void shouldThrowIllegalArgumentsExceptionWhenReturningMovieWithInvalidUser() {
        // given
        given(borrowReturnInformation.getUserId()).willReturn(USER_ID);
        given(userValidator.validate(USER_ID)).willReturn(Boolean.FALSE);
        // when
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            borrowService.returnMovie(borrowReturnInformation);
        });
        // then
    }

    @Test
    public void shouldThrowIllegalArgumentsExceptionWhenReturningMovieAndZeroUnitAreUpdated() {
        // given
        given(borrowReturnInformation.getUserId()).willReturn(USER_ID);
        given(userValidator.validate(USER_ID)).willReturn(Boolean.TRUE);
        given(borrowReturnInformation.getBorrowId()).willReturn(BORROW_ID);
        given(unitBorrowReturnEndSetter.set(BORROW_ID)).willReturn(Optional.empty());
        // when
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            borrowService.returnMovie(borrowReturnInformation);
        });
        // then
    }

    @Test
    public void shouldListRentsByLoggedUser() {
        // given
        given(loggedUserFactory.get()).willReturn(Optional.of(user));
        given(unitRepository.findRentedUnitsByUser(BDDMockito.eq(user), BDDMockito.any(Pageable.class)))
                .willReturn(unitsPage);
        given(rentedMovieShowPageFactory.create(unitsPage, pageEndpoint, CURRENT_PAGE)).willReturn(page);
        // when
        Page<RentedMovieShow> actualValue = borrowService.listRentsByLoggedUser(pageEndpoint, CURRENT_PAGE);
        // then
        assertEquals(page, actualValue);
    }

    @Test
    public void shouldThrowIllegalStateExceptionWhenListingRentsByLoggedUserWithoutLoggedUser() {
        // given
        given(loggedUserFactory.get()).willReturn(Optional.empty());
        // when
        Assertions.assertThrows(IllegalStateException.class, () -> {
            borrowService.listRentsByLoggedUser(pageEndpoint, CURRENT_PAGE);
        });
        // then
    }

}
