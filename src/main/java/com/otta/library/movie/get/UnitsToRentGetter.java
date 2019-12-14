package com.otta.library.movie.get;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.otta.library.movie.entity.Movie;
import com.otta.library.movie.entity.Unit;
import com.otta.library.movie.repository.UnitRepository;

@Component
public class UnitsToRentGetter {
    private final UnitRepository unitRepository;

    @Autowired
    public UnitsToRentGetter(UnitRepository unitRepository) {
        this.unitRepository = unitRepository;
    }

    public Collection<Unit> get(Movie movie) {
        Collection<Unit> unitsByMovie = unitRepository.findByMovie(movie);
        Collection<Unit> rentedUnitsByMovie = unitRepository.findByMovieAndRented(movie);

        return unitsByMovie.stream()
                .filter(unitToFilter -> !rentedUnitsByMovie.contains(unitToFilter))
                .collect(Collectors.toList());
    }
}
