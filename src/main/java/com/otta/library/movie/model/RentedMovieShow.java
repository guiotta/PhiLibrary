package com.otta.library.movie.model;

import java.util.Objects;

public class RentedMovieShow {
    private long movieId;
    private String movieName;
    private long unitId;
    private long borrowId;

    public RentedMovieShow() {
        // Do nothing.
    }

    public RentedMovieShow(long movieId, String movieName, long unitId, long borrowId) {
        super();
        this.movieId = movieId;
        this.movieName = movieName;
        this.unitId = unitId;
        this.borrowId = borrowId;
    }

    public long getMovieId() {
        return movieId;
    }

    public void setMovieId(long movieId) {
        this.movieId = movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public long getUnitId() {
        return unitId;
    }

    public void setUnitId(long unitId) {
        this.unitId = unitId;
    }

    public long getBorrowId() {
        return borrowId;
    }

    public void setBorrowId(long borrowId) {
        this.borrowId = borrowId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(borrowId, movieId, movieName, unitId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof RentedMovieShow)) {
            return false;
        }
        RentedMovieShow other = (RentedMovieShow) obj;
        return borrowId == other.borrowId && movieId == other.movieId && Objects.equals(movieName, other.movieName)
                && unitId == other.unitId;
    }
}
