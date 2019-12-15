package com.otta.library.movie.update;

import java.util.Calendar;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.otta.library.movie.entity.Borrow;
import com.otta.library.movie.entity.Unit;
import com.otta.library.movie.extractor.BorrowUnitExtractor;
import com.otta.library.movie.repository.UnitRepository;

/**
 * Componente para atualizar o objeto {@link Borrow}, com a data de final de aluguel.</br>
 * Caso consiga atualizar o objeto, retorna um {@link Optional} contendo a {@link Unit} com o objeto atualizado. Caso
 * contrário, retorna um {@link Optional#empty()}.
 *
 * @author Guilherme
 *
 */
@Component
public class UnitBorrowReturnEndSetter {
    private final UnitRepository unitRepository;
    private final BorrowUnitExtractor borrowUnitExtractor;

    @Autowired
    public UnitBorrowReturnEndSetter(UnitRepository unitRepository, BorrowUnitExtractor borrowUnitExtractor) {
        this.unitRepository = unitRepository;
        this.borrowUnitExtractor = borrowUnitExtractor;
    }

    public Optional<Unit> set(long borrowId) {
        Unit unitWithBorrowToReturn = unitRepository.findByBorrowId(borrowId)
                .orElseThrow(() -> new IllegalArgumentException("Could not find rented Unit."));
        Optional<Borrow> optionalBorrow = borrowUnitExtractor.extract(unitWithBorrowToReturn, borrowId);

        if (optionalBorrow.isPresent()) {
            Borrow borrow = optionalBorrow.get();
            borrow.setEnd(Calendar.getInstance());

            return Optional.of(unitWithBorrowToReturn);
        }

        return Optional.empty();
    }

}
