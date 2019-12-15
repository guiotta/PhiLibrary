package com.otta.movies.movie.mapper;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.otta.movies.movie.entity.Movie;
import com.otta.movies.movie.get.UnitsToRentGetter;
import com.otta.movies.movie.model.MovieShow;

/**
 * Componente para mapear um {@link Movie} em um {@link MovieShow}.
 * @author Guilherme
 *
 */
@Component
public class MovieShowMapper {
    private final UnitsToRentGetter unitsToRentGetter;

    @Autowired
    public MovieShowMapper(UnitsToRentGetter unitsToRentGetter) {
        this.unitsToRentGetter = unitsToRentGetter;
    }

    public MovieShow map(Movie movie) {
        Collection<Long> unitsId = unitsToRentGetter.get(movie).stream()
                .map(unit -> unit.getId())
                .collect(Collectors.toList());

        return new MovieShow(movie.getId(), movie.getName(), movie.getDirector(), unitsId);
    }
}
