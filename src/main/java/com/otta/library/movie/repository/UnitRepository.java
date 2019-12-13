package com.otta.library.movie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.otta.library.movie.entity.Unit;

@Repository
public interface UnitRepository extends JpaRepository<Unit, Long> {

}
