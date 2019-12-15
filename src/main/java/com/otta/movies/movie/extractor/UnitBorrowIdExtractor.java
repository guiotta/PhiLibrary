package com.otta.movies.movie.extractor;

import org.springframework.stereotype.Component;

import com.otta.movies.movie.entity.Unit;

/**
 * Componente para extrair o ID do {@link Borrow} de uma {@link Unit}, filtando somente pelos filmes atualmente emprestados.</br>
 * Caso não encontre o elemento, dispara um {@link IllegalArgumentException}.
 * @author Guilherme
 *
 */
@Component
public class UnitBorrowIdExtractor {

    public long extract(Unit unit) throws IllegalArgumentException {
        return unit.getBorrowings().stream()
                .filter(borrow -> borrow.getEnd() == null)
                .map(borrow -> borrow.getId())
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Could not find Borrow ID for Unit in request. Try again."));
    }
}
