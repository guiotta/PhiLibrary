package com.otta.library.movie.model.pagination;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

import com.otta.library.movie.model.MovieShow;
import com.otta.library.pagination.Page;

public class MovieShowPage implements Page<MovieShow> {
    private long currentPage;
    private long totalPages;
    private long elementsInPage;
    private Collection<MovieShow> content;
    private String next;
    private String previous;

    public MovieShowPage() {
        content = new ArrayList<>();
    }

    public MovieShowPage(long currentPage, long totalPages, long elementsInPage, Collection<MovieShow> content,
            String previous, String next) {
        this.currentPage = currentPage;
        this.totalPages = totalPages;
        this.elementsInPage = elementsInPage;
        this.content = content;
        this.next = next;
        this.previous = previous;
    }

    @Override
    public long getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(long currentPage) {
        this.currentPage = currentPage;
    }

    @Override
    public long getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(long totalPages) {
        this.totalPages = totalPages;
    }

    @Override
    public long getElementsInPage() {
        return elementsInPage;
    }

    public void setElementsInPage(long elementsInPage) {
        this.elementsInPage = elementsInPage;
    }

    @Override
    public Collection<MovieShow> getContent() {
        return content;
    }

    public void setContent(Collection<MovieShow> content) {
        this.content = content;
    }

    @Override
    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    @Override
    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    @Override
    public int hashCode() {
        return Objects.hash(content, currentPage, elementsInPage, next, previous, totalPages);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MovieShowPage)) {
            return false;
        }
        MovieShowPage other = (MovieShowPage) obj;
        return Objects.equals(content, other.content) && currentPage == other.currentPage
                && elementsInPage == other.elementsInPage && Objects.equals(next, other.next)
                && Objects.equals(previous, other.previous) && totalPages == other.totalPages;
    }
}
