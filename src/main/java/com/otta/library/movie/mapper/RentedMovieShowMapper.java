package com.otta.library.movie.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.otta.library.movie.entity.Unit;
import com.otta.library.movie.extractor.UnitBorrowIdExtractor;
import com.otta.library.movie.model.RentedMovieShow;

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
