package com.otta.movies.movie.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

import static org.mockito.BDDMockito.given;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;

import com.otta.movies.movie.entity.Movie;
import com.otta.movies.movie.factory.MovieShowPageFactory;
import com.otta.movies.movie.mapper.MovieMapper;
import com.otta.movies.movie.model.MovieInformation;
import com.otta.movies.movie.model.MovieSearchInformation;
import com.otta.movies.movie.model.MovieShow;
import com.otta.movies.movie.repository.MovieRepository;
import com.otta.movies.movie.service.MovieService;
import com.otta.movies.pagination.Page;
import com.otta.movies.pagination.model.PageEndpoint;

/**
 * Testes unitários do componente {@link MovieService}.
 * 
 * @author Guilherme
 *
 */
@ExtendWith(MockitoExtension.class)
public class MovieServiceTest {
    private static final int CURRENT_PAGE = 0;
    private static final String NAME = "name";

    @Mock
    private MovieMapper movieMapper;
    @Mock
    private MovieRepository movieRepository;
    @Mock
    private MovieShowPageFactory movieShowPageFactory;
    @InjectMocks
    private MovieService movieService;

    @Mock
    private MovieInformation movieInformation;
    @Mock
    private Movie movie;
    @Mock
    private org.springframework.data.domain.Page<Movie> moviesPage;
    @Mock
    private Page<MovieShow> page;
    @Mock
    private MovieSearchInformation movieSearchInformation;

    private PageEndpoint pageEndpoint = PageEndpoint.MOVIE;

    @BeforeEach
    protected void setUp() {
    }

    @Test
    public void shouldCorrectlySaveMovie() {
        // given
        given(movieMapper.map(movieInformation)).willReturn(movie);
        // when
        movieService.saveMovie(movieInformation);
        // then
        verify(movieRepository).save(movie);
    }

    @Test
    public void shouldReturnMovieInformationAfterSave() {
        // given
        given(movieMapper.map(movieInformation)).willReturn(movie);
        // when
        MovieInformation actualValue = movieService.saveMovie(movieInformation);
        // then
        assertEquals(movieInformation, actualValue);
    }

    @Test
    public void shouldListAllMovies() {
        // given
        given(movieRepository.findAll(BDDMockito.any(Pageable.class))).willReturn(moviesPage);
        given(movieShowPageFactory.create(moviesPage, pageEndpoint, CURRENT_PAGE)).willReturn(page);
        // when
        Page<MovieShow> actualValue = movieService.listMovies(pageEndpoint, CURRENT_PAGE);
        // then
        assertEquals(page, actualValue);
    }

    @Test
    public void shouldListMoviesByName() {
        // given
        given(movieSearchInformation.getMovieName()).willReturn(NAME);
        given(movieRepository.findByName(BDDMockito.eq(NAME), BDDMockito.any(Pageable.class))).willReturn(moviesPage);
        given(movieShowPageFactory.create(moviesPage, pageEndpoint, CURRENT_PAGE)).willReturn(page);
        // when
        Page<MovieShow> actualValue = movieService.listMovies(pageEndpoint, movieSearchInformation, CURRENT_PAGE);
        // then
        assertEquals(page, actualValue);
    }

}
