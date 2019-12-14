package com.otta.library.pagination.model;

import java.util.Objects;

public class PaginationSteps {
    private String previous;
    private String next;

    public PaginationSteps() {
        // Do nothing.
    }

    public PaginationSteps(String previous, String next) {
        this.previous = previous;
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    @Override
    public int hashCode() {
        return Objects.hash(next, previous);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PaginationSteps)) {
            return false;
        }
        PaginationSteps other = (PaginationSteps) obj;
        return Objects.equals(next, other.next) && Objects.equals(previous, other.previous);
    }

}
