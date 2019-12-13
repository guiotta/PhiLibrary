package com.otta.library.movie.model;

import java.util.Objects;

public class MovieBorrow {
    private long idUnit;

    public MovieBorrow() {
        // Do nothing.
    }

    public MovieBorrow(int idUnit) {
        this.idUnit = idUnit;
    }

    public long getIdUnit() {
        return idUnit;
    }

    public void setIdUnit(long idUnit) {
        this.idUnit = idUnit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUnit);
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
        return idUnit == other.idUnit;
    }
}
