package com.otta.library.movie.mapper;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.BDDMockito.given;

import org.assertj.core.util.Lists;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import com.otta.library.movie.entity.Movie;
import com.otta.library.movie.entity.Unit;
import com.otta.library.movie.get.UnitsToRentGetter;
import com.otta.library.movie.model.MovieShow;

/**
 * Testes unitários do componente {@link MovieShowMapper}.
 * 
 * @author Guilherme
 *
 */
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class MovieShowMapperTest {
    private static final long UNIT_A_ID = 10;
    private static final long UNIT_B_ID = 15;
    private static final long MOVIE_ID = 1;
    private static final String MOVIE_NAME = "name";
    private static final String MOVIE_DIRECTOR = "director";

    @Mock
    private UnitsToRentGetter unitsToRentGetter;
    @InjectMocks
    private MovieShowMapper movieShowMapper;

    @Mock
    private Movie movie;
    @Mock
    private Unit unitA;
    @Mock
    private Unit unitB;

    @BeforeEach
    protected void setUp() throws Exception {
        given(movie.getId()).willReturn(MOVIE_ID);
        given(movie.getName()).willReturn(MOVIE_NAME);
        given(movie.getDirector()).willReturn(MOVIE_DIRECTOR);
        given(unitsToRentGetter.get(movie)).willReturn(Lists.list(unitA, unitB));
        given(unitA.getId()).willReturn(UNIT_A_ID);
        given(unitB.getId()).willReturn(UNIT_B_ID);
    }

    @Test
    public void shouldCorrectlyMapIdProperty() {
        // given
        // when
        MovieShow actualValue = movieShowMapper.map(movie);
        // then
        assertEquals(MOVIE_ID, actualValue.getMovieId());
    }

    @Test
    public void shouldCorrectlyMapNameProperty() {
        // given
        // when
        MovieShow actualValue = movieShowMapper.map(movie);
        // then
        assertEquals(MOVIE_NAME, actualValue.getMovieName());
    }

    @Test
    public void shouldCorrectlyMapDirectorProperty() {
        // given
        // when
        MovieShow actualValue = movieShowMapper.map(movie);
        // then
        assertEquals(MOVIE_DIRECTOR, actualValue.getMovieDirector());
    }

    @Test
    public void shouldCorrectlyMapUnitIdsProperty() {
        // given
        // when
        MovieShow actualValue = movieShowMapper.map(movie);
        // then
        assertThat(actualValue.getFreeUnits(), Matchers.containsInAnyOrder(UNIT_A_ID, UNIT_B_ID));
    }

    @Test
    public void shouldCorrectlyMapUnitIdsPropertyWhenUnitHasZeroUnitsToRent() {
        // given
        given(unitsToRentGetter.get(movie)).willReturn(Lists.list());
        // when
        MovieShow actualValue = movieShowMapper.map(movie);
        // then
        assertThat(actualValue.getFreeUnits(), empty());
    }
}
