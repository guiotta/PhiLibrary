package com.otta.library.movie.repository;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.otta.library.movie.entity.Movie;
import com.otta.library.movie.entity.Unit;

@Repository
public interface UnitRepository extends JpaRepository<Unit, Long> {

    @Query("SELECT distinct u FROM Unit u LEFT JOIN u.borrowings b WHERE u.movie = :movie")
    Collection<Unit> findByMovie(@Param("movie") Movie movie);

    @Query("SELECT distinct u FROM Unit u LEFT JOIN u.borrowings b WHERE u.movie = :movie AND (b.begin IS NOT NULL AND b.end IS NULL)")
    Collection<Unit> findByMovieAndRented(@Param("movie") Movie movie);

    @Query("SELECT distinct u FROM Unit u LEFT JOIN u.borrowings b WHERE b.id = :id_borrow")
    Optional<Unit> findByBorrowId(@Param("id_borrow") long borrowId);

}
