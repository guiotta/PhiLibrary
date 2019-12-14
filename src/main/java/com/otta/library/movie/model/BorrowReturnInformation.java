package com.otta.library.movie.model;

import java.util.Objects;

public class BorrowReturnInformation {
    private long borrowId;
    private long userId;

    public BorrowReturnInformation() {
        // Do nothing.
    }

    public BorrowReturnInformation(long borrowId, long userId) {
        this.borrowId = borrowId;
        this.userId = userId;
    }

    public long getBorrowId() {
        return borrowId;
    }

    public void setBorrowId(long borrowId) {
        this.borrowId = borrowId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(borrowId, userId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof BorrowReturnInformation)) {
            return false;
        }
        BorrowReturnInformation other = (BorrowReturnInformation) obj;
        return borrowId == other.borrowId && userId == other.userId;
    }
}
