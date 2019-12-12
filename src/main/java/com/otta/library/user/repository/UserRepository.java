package com.otta.library.user.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.otta.library.user.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmail(String email);

}
