package com.otta.library.movie.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

public class MovieShow {
    private long movieId;
    private String movieName;
    private String movieDirector;
    private Collection<Long> unitsFrees = new ArrayList<>();

    public MovieShow() {
        unitsFrees = new ArrayList<>();
    }

    public MovieShow(long movieId, String movieName, String movieDirector, Collection<Long> unitsFrees) {
        this.movieId = movieId;
        this.movieName = movieName;
        this.movieDirector = movieDirector;
        this.unitsFrees = unitsFrees;
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

    public String getMovieDirector() {
        return movieDirector;
    }

    public void setMovieDirector(String movieDirector) {
        this.movieDirector = movieDirector;
    }

    public Collection<Long> getUnitsFrees() {
        return unitsFrees;
    }

    public void setUnitsFrees(Collection<Long> unitsFrees) {
        this.unitsFrees = unitsFrees;
    }

    public void addUnitFree(Long unitId) {
        this.unitsFrees.add(unitId);
    }

    public void addAllUnitFree(Collection<Long> unitIdCollection) {
        this.unitsFrees.addAll(unitIdCollection);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieDirector, movieId, movieName, unitsFrees);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MovieShow)) {
            return false;
        }
        MovieShow other = (MovieShow) obj;
        return Objects.equals(movieDirector, other.movieDirector) && movieId == other.movieId
                && Objects.equals(movieName, other.movieName) && Objects.equals(unitsFrees, other.unitsFrees);
    }
}
