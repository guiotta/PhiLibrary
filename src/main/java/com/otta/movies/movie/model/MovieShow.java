package com.otta.movies.movie.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

public class MovieShow {
    private long movieId;
    private String movieName;
    private String movieDirector;
    private Collection<Long> freeUnits = new ArrayList<>();

    public MovieShow() {
        freeUnits = new ArrayList<>();
    }

    public MovieShow(long movieId, String movieName, String movieDirector, Collection<Long> freeUnits) {
        this.movieId = movieId;
        this.movieName = movieName;
        this.movieDirector = movieDirector;
        this.freeUnits = freeUnits;
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

    public Collection<Long> getFreeUnits() {
        return freeUnits;
    }

    public void setFreeUnits(Collection<Long> unitsFrees) {
        this.freeUnits = unitsFrees;
    }

    public void addFreeUnit(Long unitId) {
        this.freeUnits.add(unitId);
    }

    public void addAllUnitFree(Collection<Long> unitIdCollection) {
        this.freeUnits.addAll(unitIdCollection);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieDirector, movieId, movieName, freeUnits);
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
                && Objects.equals(movieName, other.movieName) && Objects.equals(freeUnits, other.freeUnits);
    }
}
