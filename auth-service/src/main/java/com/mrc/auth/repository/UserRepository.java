package com.mrc.auth.repository;

import org.springframework.data.repository.CrudRepository;

import com.mrc.auth.entity.User;

public interface UserRepository extends CrudRepository<User, Long>{
	User findByUsername(String username);
	boolean existsByUsername(String username);
	boolean existsByEmail(String email);
}
