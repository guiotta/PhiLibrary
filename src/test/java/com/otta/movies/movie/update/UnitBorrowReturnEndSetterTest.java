package com.otta.movies.movie.update;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

import static org.mockito.BDDMockito.given;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.otta.movies.movie.entity.Borrow;
import com.otta.movies.movie.entity.Unit;
import com.otta.movies.movie.extractor.BorrowUnitExtractor;
import com.otta.movies.movie.repository.UnitRepository;
import com.otta.movies.movie.update.UnitBorrowReturnEndSetter;

/**
 * Testes unitários do componente {@link UnitBorrowReturnEndSetter}.
 * 
 * @author Guilherme
 *
 */
@ExtendWith(MockitoExtension.class)
public class UnitBorrowReturnEndSetterTest {
    private static final long BORROW_ID = 5;

    @Mock
    private BorrowUnitExtractor borrowUnitExtractor;
    @Mock
    private UnitRepository unitRepository;
    @InjectMocks
    private UnitBorrowReturnEndSetter unitBorrowReturnEndSetter;

    @Mock
    private Unit unit;
    @Mock
    private Borrow borrow;

    @BeforeEach
    protected void setUp() throws Exception {
    }

    @Test
    public void shouldCorrectlyUpdateBorrowObject() throws Exception {
        // given
        given(unitRepository.findByBorrowId(BORROW_ID)).willReturn(Optional.of(unit));
        given(borrowUnitExtractor.extract(unit, BORROW_ID)).willReturn(Optional.of(borrow));
        // when
        unitBorrowReturnEndSetter.set(BORROW_ID);
        // then
        verify(borrow).setEnd(BDDMockito.any());
    }

    @Test
    public void shouldCorrectlyReturnUnitWithUpdatedBorrow() throws Exception {
        // given
        given(unitRepository.findByBorrowId(BORROW_ID)).willReturn(Optional.of(unit));
        given(borrowUnitExtractor.extract(unit, BORROW_ID)).willReturn(Optional.of(borrow));
        // when
        Optional<Unit> actualValue = unitBorrowReturnEndSetter.set(BORROW_ID);
        // then
        assertEquals(Optional.of(unit), actualValue);
    }

    @Test
    public void shouldReturnOptionalEmptyWhenExtractorReturnAnEmptyObjet() throws Exception {
        // given
        given(unitRepository.findByBorrowId(BORROW_ID)).willReturn(Optional.of(unit));
        given(borrowUnitExtractor.extract(unit, BORROW_ID)).willReturn(Optional.empty());
        // when
        Optional<Unit> actualValue = unitBorrowReturnEndSetter.set(BORROW_ID);
        // then
        assertEquals(Optional.empty(), actualValue);
    }

    @Test
    public void shouldThrowIllegalExceptionWhenRepositoryThrows() throws Exception {
        // given
        given(unitRepository.findByBorrowId(BORROW_ID)).willThrow(IllegalArgumentException.class);
        // when
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            unitBorrowReturnEndSetter.set(BORROW_ID);
        });
        // then
    }
}
