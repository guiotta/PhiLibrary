package com.otta.movies.user.repository;

import org.springframework.data.repository.CrudRepository;

import com.otta.movies.user.entity.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {

}
