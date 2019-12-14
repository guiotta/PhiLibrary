package com.otta.library.movie.extractor;

import org.springframework.stereotype.Component;

import com.otta.library.movie.entity.Unit;

@Component
public class UnitBorrowIdExtractor {

    public long extract(Unit unit) {
        return unit.getBorrowings().stream()
                .filter(borrow -> borrow.getEnd() == null)
                .map(borrow -> borrow.getId())
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Could not find Borrow ID for Unit in request. Try again."));
    }
}
