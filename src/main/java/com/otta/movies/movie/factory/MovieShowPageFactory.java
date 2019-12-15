package com.otta.movies.movie.factory;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.otta.movies.movie.entity.Movie;
import com.otta.movies.movie.mapper.MovieShowMapper;
import com.otta.movies.movie.model.MovieShow;
import com.otta.movies.movie.model.pagination.MovieShowPage;
import com.otta.movies.pagination.Page;
import com.otta.movies.pagination.factory.PaginationStepsFactory;
import com.otta.movies.pagination.model.PageEndpoint;
import com.otta.movies.pagination.model.PaginationSteps;

/**
 * Componente para construir um {@link Page}<{@link MovieShow}>.
 * @author Guilherme
 *
 */
@Component
public class MovieShowPageFactory {
    private final MovieShowMapper movieShowMapper;
    private final PaginationStepsFactory paginationStepsFactory;

    @Autowired
    public MovieShowPageFactory(MovieShowMapper movieShowMapper, PaginationStepsFactory paginationStepsFactory) {
        this.movieShowMapper = movieShowMapper;
        this.paginationStepsFactory = paginationStepsFactory;
    }

    public Page<MovieShow> create(org.springframework.data.domain.Page<Movie> moviesPage, PageEndpoint pageEndpoint,
            int currentPage) {
        PaginationSteps paginationSteps = paginationStepsFactory.create(pageEndpoint, currentPage,
                moviesPage.hasPrevious(),moviesPage.hasNext());
        Collection<MovieShow> movieShowCollection = moviesPage.stream()
                .map(movie -> movieShowMapper.map(movie))
                .collect(Collectors.toList());

        return new MovieShowPage(currentPage, moviesPage.getTotalPages(), moviesPage.getNumberOfElements(),
                movieShowCollection, paginationSteps.getPrevious(), paginationSteps.getNext());
    }

}
