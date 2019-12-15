package com.otta.movies.movie.model;

import java.util.Objects;

public class MovieBorrow {
    private long idMovie;

    public MovieBorrow() {
        // Do nothing.
    }

    public MovieBorrow(long idMovie) {
        this.idMovie = idMovie;
    }

    public long getIdMovie() {
        return idMovie;
    }

    public void setIdMovie(long idMovie) {
        this.idMovie = idMovie;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idMovie);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MovieBorrow)) {
            return false;
        }
        MovieBorrow other = (MovieBorrow) obj;
        return idMovie == other.idMovie;
    }
}
