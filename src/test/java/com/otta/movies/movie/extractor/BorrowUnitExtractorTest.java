package com.otta.movies.movie.extractor;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.BDDMockito.given;

import java.util.Optional;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.otta.movies.movie.entity.Borrow;
import com.otta.movies.movie.entity.Unit;
import com.otta.movies.movie.extractor.BorrowUnitExtractor;

/**
 * Testes unitários do componente {@link BorrowUnitExtractor}.
 * 
 * @author Guilherme
 *
 */
@ExtendWith(MockitoExtension.class)
public class BorrowUnitExtractorTest {
    private final long BORROW_ID_A = 1;
    private final long BORROW_ID_B = 2;
    private final long BORROW_ID_UNKNOWN = 3;

    @InjectMocks
    private BorrowUnitExtractor extractor;

    @Mock
    private Unit unit;
    @Mock
    private Borrow borrowA;
    @Mock
    private Borrow borrowB;

    @Test
    public void shouldCorrectlyExtractBorrow() throws Exception {
        // given
        given(borrowA.getId()).willReturn(BORROW_ID_A);
        given(borrowB.getId()).willReturn(BORROW_ID_B);
        given(unit.getBorrowings()).willReturn(Lists.list(borrowA, borrowB));
        // when
        Optional<Borrow> actualValue = extractor.extract(unit, BORROW_ID_B);
        // then
        assertEquals(Optional.of(borrowB), actualValue);
    }

    @Test
    public void shouldReturnEmptyOptionalWhenBorrowIsNotFound() throws Exception {
        // given
        given(borrowA.getId()).willReturn(BORROW_ID_A);
        given(borrowB.getId()).willReturn(BORROW_ID_B);
        given(unit.getBorrowings()).willReturn(Lists.list(borrowA, borrowB));
        // when
        Optional<Borrow> actualValue = extractor.extract(unit, BORROW_ID_UNKNOWN);
        // then
        assertEquals(Optional.empty(), actualValue);
    }

    @Test
    public void shouldReturnEmptyOptionalWhenUnitNotHasBorrowings() throws Exception {
        // given
        given(unit.getBorrowings()).willReturn(Lists.list());
        // when
        Optional<Borrow> actualValue = extractor.extract(unit, BORROW_ID_A);
        // then
        assertEquals(Optional.empty(), actualValue);
    }
}
