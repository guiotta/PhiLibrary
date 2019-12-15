package com.otta.movies.movie.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 * Entidade mapeando as informações de uma Unidade de um Filme, que serão salvas na base de dados.
 * @author Guilherme
 *
 */
@Entity
@Table(name = "Unit")
public class Unit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_movie")
    private Movie movie;
    //@OneToMany(mappedBy = "unit", cascade = CascadeType.ALL, orphanRemoval = true)
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_unit")
    private List<Borrow> borrowings = new ArrayList<>();

    @Version
    @Column(name = "version")
    private int version;
    
    public Unit() {
        // Do nothing.
    }

    public Unit(Movie movie, List<Borrow> borrowings) {
        this.movie = movie;
        this.borrowings = borrowings;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public List<Borrow> getBorrowings() {
        return borrowings;
    }

    public void setBorrowings(List<Borrow> borrowings) {
        this.borrowings = borrowings;
    }

    public void addBorrow(Borrow borrow) {
        this.borrowings.add(borrow);
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public int hashCode() {
        return Objects.hash(borrowings, id, movie, version);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Unit)) {
            return false;
        }
        Unit other = (Unit) obj;
        return Objects.equals(borrowings, other.borrowings) && Objects.equals(id, other.id)
                && Objects.equals(movie, other.movie) && version == other.version;
    }
}
