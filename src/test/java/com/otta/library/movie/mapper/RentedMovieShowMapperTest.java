package com.otta.library.movie.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.BDDMockito.given;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.otta.library.movie.entity.Movie;
import com.otta.library.movie.entity.Unit;
import com.otta.library.movie.extractor.UnitBorrowIdExtractor;
import com.otta.library.movie.model.RentedMovieShow;

/**
 * Teste unitário do componente {@link RentedMovieShowMapper}.
 * 
 * @author Guilherme
 *
 */
@ExtendWith(MockitoExtension.class)
public class RentedMovieShowMapperTest {
    private static final long BORROW_ID = 10;
    private static final long UNIT_ID = 5;
    private static final long MOVIE_ID = 1;
    private static final String MOVIE_NAME = "name";

    @Mock
    private UnitBorrowIdExtractor unitBorrowIdExtractor;
    @InjectMocks
    private RentedMovieShowMapper rentedMovieShowMapper;

    @Mock
    private Movie movie;
    @Mock
    private Unit unit;

    @BeforeEach
    protected void setUp() throws Exception {
        given(unitBorrowIdExtractor.extract(unit)).willReturn(BORROW_ID);
        given(unit.getMovie()).willReturn(movie);
        given(unit.getId()).willReturn(UNIT_ID);
        given(movie.getId()).willReturn(MOVIE_ID);
        given(movie.getName()).willReturn(MOVIE_NAME);
    }

    @Test
    public void shouldCorrectlyMapMovieIdProperty() {
        // given
        // when
        RentedMovieShow actualValue = rentedMovieShowMapper.map(unit);
        // then
        assertEquals(MOVIE_ID, actualValue.getMovieId());
    }

    @Test
    public void shouldCorrectlyMapMovieNameProperty() {
        // given
        // when
        RentedMovieShow actualValue = rentedMovieShowMapper.map(unit);
        // then
        assertEquals(MOVIE_NAME, actualValue.getMovieName());
    }

    @Test
    public void shouldCorrectlyMapUnitIdProperty() {
        // given
        // when
        RentedMovieShow actualValue = rentedMovieShowMapper.map(unit);
        // then
        assertEquals(UNIT_ID, actualValue.getUnitId());
    }

    @Test
    public void shouldCorrectlyMapBorrowIdProperty() {
        // given
        // when
        RentedMovieShow actualValue = rentedMovieShowMapper.map(unit);
        // then
        assertEquals(BORROW_ID, actualValue.getBorrowId());
    }
}
