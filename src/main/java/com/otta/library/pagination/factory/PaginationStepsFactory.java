package com.otta.library.pagination.factory;

import org.springframework.stereotype.Component;

import com.otta.library.pagination.model.PaginationSteps;

@Component
public class PaginationStepsFactory {
    private static final int CHANGE_PAGE_OFFSET = 1;

    public PaginationSteps create(int currentPage, boolean hasPrevious, boolean hasNext) {
        String next = hasNext ? "http://localhost:8080/api/movie/" + (currentPage + CHANGE_PAGE_OFFSET) : null;
        String previous = hasPrevious ? "http://localhost:8080/api/movie/" + (currentPage - CHANGE_PAGE_OFFSET) : null;

        return new PaginationSteps(previous, next);
    }
}
