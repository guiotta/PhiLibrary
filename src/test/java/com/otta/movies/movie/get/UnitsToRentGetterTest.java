package com.otta.movies.movie.get;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;

import static org.mockito.BDDMockito.given;

import java.util.Collection;

import org.assertj.core.util.Lists;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.otta.movies.movie.entity.Movie;
import com.otta.movies.movie.entity.Unit;
import com.otta.movies.movie.get.UnitsToRentGetter;
import com.otta.movies.movie.repository.UnitRepository;

/**
 * Testes unitários do componente {@link UnitsToRentGetter}.
 * 
 * @author Guilherme
 *
 */
@ExtendWith(MockitoExtension.class)
public class UnitsToRentGetterTest {
    @Mock
    private UnitRepository unitRepository;
    @InjectMocks
    private UnitsToRentGetter unitsToRentGetter;

    @Mock
    private Movie movie;
    @Mock
    private Unit unitA;
    @Mock
    private Unit unitB;
    @Mock
    private Unit unitC;

    @BeforeEach
    protected void setUp() throws Exception {
    }

    @Test
    public void shouldReturnAllFreeUnitsFromMovie() throws Exception {
        // given
        given(unitRepository.findByMovie(movie)).willReturn(Lists.list(unitA, unitB, unitC));
        given(unitRepository.findByMovieAndRented(movie)).willReturn(Lists.list(unitA));
        // when
        Collection<Unit> actualValue = unitsToRentGetter.get(movie);
        // then
        assertThat(actualValue, Matchers.containsInAnyOrder(unitB, unitC));
    }

    @Test
    public void shouldReturnEmptyListWhenAllUnitsAreRented() throws Exception {
        // given
        given(unitRepository.findByMovie(movie)).willReturn(Lists.list(unitA, unitB, unitC));
        given(unitRepository.findByMovieAndRented(movie)).willReturn(Lists.list(unitA, unitB, unitC));
        // when
        Collection<Unit> actualValue = unitsToRentGetter.get(movie);
        // then
        assertThat(actualValue, empty());
    }

    @Test
    public void shouldReturnEmptyListWhenFindZeroUnitsFromMovie() throws Exception {
        // given
        given(unitRepository.findByMovie(movie)).willReturn(Lists.list());
        // when
        Collection<Unit> actualValue = unitsToRentGetter.get(movie);
        // then
        assertThat(actualValue, empty());
    }
}
