package com.otta.library.movie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.otta.library.movie.entity.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

}
