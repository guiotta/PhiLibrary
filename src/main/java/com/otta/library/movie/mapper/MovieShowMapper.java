package com.otta.library.movie.mapper;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.otta.library.movie.entity.Movie;
import com.otta.library.movie.get.UnitsToRentGetter;
import com.otta.library.movie.model.MovieShow;

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
