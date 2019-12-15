package com.otta.movies.movie.model;

import java.util.Objects;

public class MovieInformation {
    private String name;
    private String director;
    private int units;

    public MovieInformation() {
        // Do nothing.
    }

    public MovieInformation(String name, String director, int units) {
        this.name = name;
        this.director = director;
        this.units = units;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }

    @Override
    public int hashCode() {
        return Objects.hash(director, name, units);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MovieInformation)) {
            return false;
        }
        MovieInformation other = (MovieInformation) obj;
        return Objects.equals(director, other.director) && Objects.equals(name, other.name) && units == other.units;
    }

}
