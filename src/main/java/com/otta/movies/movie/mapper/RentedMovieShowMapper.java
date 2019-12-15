package com.otta.movies.movie.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.otta.movies.movie.entity.Unit;
import com.otta.movies.movie.extractor.UnitBorrowIdExtractor;
import com.otta.movies.movie.model.RentedMovieShow;

/**
 * Componente para mapear uma {@link Unit} em um {@link RentedMovieShow}.
 * @author Guilherme
 *
 */
@Component
public class RentedMovieShowMapper {
    private final UnitBorrowIdExtractor unitBorrowIdExtractor;

    @Autowired
    public RentedMovieShowMapper(UnitBorrowIdExtractor unitBorrowIdExtractor) {
        this.unitBorrowIdExtractor = unitBorrowIdExtractor;
    }

    public RentedMovieShow map(Unit unit) {
        long borrowId = unitBorrowIdExtractor.extract(unit);

        return new RentedMovieShow(unit.getMovie().getId(), unit.getMovie().getName(), unit.getId(), borrowId);
    }
}
