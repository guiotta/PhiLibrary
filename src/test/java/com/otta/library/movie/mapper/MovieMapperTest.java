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
import com.otta.library.movie.model.MovieInformation;

/**
 * Testes unitários do componente {@link MovieMapper}.
 * 
 * @author Guilherme
 *
 */
@ExtendWith(MockitoExtension.class)
public class MovieMapperTest {
    private static final String NAME = "name";
    private static final String DIRECTOR = "director";
    private static final int UNITS = 3;

    @InjectMocks
    private MovieMapper mapper;

    @Mock
    private MovieInformation movieInformation;

    @BeforeEach
    protected void setUp() throws Exception {
        given(movieInformation.getName()).willReturn(NAME);
        given(movieInformation.getDirector()).willReturn(DIRECTOR);
        given(movieInformation.getUnits()).willReturn(UNITS);
    }

    @Test
    public void shouldCorrectlyMapNameProperty() throws Exception {
        // given
        // when
        Movie actualValue = mapper.map(movieInformation);
        // then
        assertEquals(NAME, actualValue.getName());
    }

    @Test
    public void shouldCorrectlyMapDirectorProperty() throws Exception {
        // given
        // when
        Movie actualValue = mapper.map(movieInformation);
        // then
        assertEquals(DIRECTOR, actualValue.getDirector());
    }

    @Test
    public void shouldCorrectlyMapUnitsProperty() throws Exception {
        // given
        // when
        Movie actualValue = mapper.map(movieInformation);
        // then
        assertEquals(UNITS, actualValue.getUnits().size());
    }
}
