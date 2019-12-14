package com.otta.library.movie.model;

import java.util.Objects;

public class MovieSearchInformation {
    private String movieName;

    public MovieSearchInformation() {
        // Do nothing.
    }

    public MovieSearchInformation(String movieName) {
        this.movieName = movieName;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieName);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MovieSearchInformation)) {
            return false;
        }
        MovieSearchInformation other = (MovieSearchInformation) obj;
        return Objects.equals(movieName, other.movieName);
    }
}
