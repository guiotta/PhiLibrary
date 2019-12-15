package com.otta.movies.movie.extractor;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.BDDMockito.given;

import java.util.Calendar;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.otta.movies.movie.entity.Borrow;
import com.otta.movies.movie.entity.Unit;
import com.otta.movies.movie.extractor.UnitBorrowIdExtractor;

/**
 * Testes unitários do componente {@link UnitBorrowIdExtractor}.
 * 
 * @author Guilherme
 *
 */
@ExtendWith(MockitoExtension.class)
public class UnitBorrowIdExtractorTest {
    private final long BORROW_ID = 1;

    @InjectMocks
    private UnitBorrowIdExtractor extractor;

    @Mock
    private Unit unit;
    @Mock
    private Borrow borrowA;
    @Mock
    private Borrow borrowB;

    @BeforeEach
    protected void setUp() throws Exception {
    }

    @Test
    public void shouldCorrectlyExtractyBorrowId() throws Exception {
        // given
        given(borrowA.getEnd()).willReturn(Calendar.getInstance());
        given(borrowB.getId()).willReturn(BORROW_ID);
        given(unit.getBorrowings()).willReturn(Lists.list(borrowA, borrowB));
        // when
        long actualValue = extractor.extract(unit);
        // then
        assertEquals(BORROW_ID, actualValue);
    }

    @Test
    public void shouldThrowExceptionWhenUnitDontHasBorrowsWithEndNull() {
        // given
        given(borrowA.getEnd()).willReturn(Calendar.getInstance());
        given(borrowB.getEnd()).willReturn(Calendar.getInstance());
        given(unit.getBorrowings()).willReturn(Lists.list(borrowA, borrowB));
        // when
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            extractor.extract(unit);
        });
        // then
    }

    @Test
    public void shouldThrowExceptionWhenUnitBorrowListIsEmpty() {
        // given
        given(unit.getBorrowings()).willReturn(Lists.list());
        // when
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            extractor.extract(unit);
        });
        // then
    }
}
