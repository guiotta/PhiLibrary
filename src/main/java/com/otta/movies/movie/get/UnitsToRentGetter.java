package com.otta.movies.movie.get;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.otta.movies.movie.entity.Movie;
import com.otta.movies.movie.entity.Unit;
import com.otta.movies.movie.repository.UnitRepository;

/**
 * Componente para recuperar sa {@link Unit Units} de um {@link Movie} que estão livres para ser alugadas.
 * @author Guilherme
 *
 */
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
