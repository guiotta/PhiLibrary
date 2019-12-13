package com.otta.library.movie.entity;

import java.util.Calendar;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.otta.library.user.entity.User;

@Entity
@Table(name = "Borrow")
public class Borrow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "begin")
    private Calendar begin;
    @Column(name = "end")
    private Calendar end;

    //@ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "id_unit")
    @Transient
    private Unit unit;
    @Column(name = "id_unit")
    public int idUnit = 1;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    private User user;

    public Borrow() {
        // Do nothing.
    }

    public Borrow(Calendar begin, Calendar end, Unit unit, User user) {
        this.begin = begin;
        this.end = end;
        this.unit = unit;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Calendar getBegin() {
        return begin;
    }

    public void setBegin(Calendar begin) {
        this.begin = begin;
    }

    public Calendar getEnd() {
        return end;
    }

    public void setEnd(Calendar end) {
        this.end = end;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        return Objects.hash(begin, end, id, unit, user);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Borrow)) {
            return false;
        }
        Borrow other = (Borrow) obj;
        return Objects.equals(begin, other.begin) && Objects.equals(end, other.end) && Objects.equals(id, other.id)
                && Objects.equals(unit, other.unit) && Objects.equals(user, other.user);
    }
}
