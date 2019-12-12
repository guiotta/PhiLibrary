package com.otta.library.user.repository;

import org.springframework.data.repository.CrudRepository;

import com.otta.library.user.entity.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {

}
