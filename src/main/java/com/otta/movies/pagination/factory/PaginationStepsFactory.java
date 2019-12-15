package com.otta.movies.pagination.factory;

import org.springframework.stereotype.Component;

import com.otta.movies.pagination.model.PageEndpoint;
import com.otta.movies.pagination.model.PaginationSteps;

/**
 * Componente para criar um {@link PaginationSteps}.
 * @author Guilherme
 *
 */
@Component
public class PaginationStepsFactory {
    private static final int CHANGE_PAGE_OFFSET = 1;

    public PaginationSteps create(PageEndpoint pageEndpoint, int currentPage, boolean hasPrevious, boolean hasNext) {
        String next = hasNext ? pageEndpoint.getSpecificUrl() + (currentPage + CHANGE_PAGE_OFFSET) : null;
        String previous = hasPrevious ? pageEndpoint.getSpecificUrl() + (currentPage - CHANGE_PAGE_OFFSET) : null;

        return new PaginationSteps(previous, next);
    }
}
