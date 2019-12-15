package com.otta.movies.pagination.factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.otta.movies.pagination.factory.PaginationStepsFactory;
import com.otta.movies.pagination.model.PageEndpoint;
import com.otta.movies.pagination.model.PaginationSteps;

/**
 * Testes unitário do componente {@link PaginationStepsFactory}.
 * 
 * @author Guilherme
 *
 */
@ExtendWith(MockitoExtension.class)
public class PaginationStepsFactoryTest {
    private static final int CURRENT_PAGE = 1;

    @InjectMocks
    private PaginationStepsFactory factory;

    private PageEndpoint pageEndpoint = PageEndpoint.MOVIE;

    @BeforeEach
    protected void setUp() throws Exception {
    }

    @Test
    public void shouldCorrectlyBuildPaginationSteps() throws Exception {
        // given
        // when
        PaginationSteps actualValue = factory.create(pageEndpoint, CURRENT_PAGE, Boolean.TRUE, Boolean.TRUE);
        // then
        assertEquals("/api/movie/0", actualValue.getPrevious());
        assertEquals("/api/movie/2", actualValue.getNext());
    }

    @Test
    public void shouldCorrectlyBuildPaginationStepsWithoutPrevious() throws Exception {
        // given
        // when
        PaginationSteps actualValue = factory.create(pageEndpoint, CURRENT_PAGE, Boolean.FALSE, Boolean.TRUE);
        // then
        assertNull(actualValue.getPrevious());
        assertEquals("/api/movie/2", actualValue.getNext());
    }

    @Test
    public void shouldCorrectlyBuildPaginationStepsWithoutNext() throws Exception {
        // given
        // when
        PaginationSteps actualValue = factory.create(pageEndpoint, CURRENT_PAGE, Boolean.TRUE, Boolean.FALSE);
        // then
        assertEquals("/api/movie/0", actualValue.getPrevious());
        assertNull(actualValue.getNext());
    }

    @Test
    public void shouldCorrectlyBuildPaginationStepsWithoutAnyValue() throws Exception {
        // given
        // when
        PaginationSteps actualValue = factory.create(pageEndpoint, CURRENT_PAGE, Boolean.FALSE, Boolean.FALSE);
        // then
        assertNull(actualValue.getPrevious());
        assertNull(actualValue.getNext());
    }
}
