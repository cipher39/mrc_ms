package com.mrc.auth.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.mrc.auth.entity.User;

public interface UserRepository extends CrudRepository<User, Long>{
	Optional<User> findByUsername(String username);
	boolean existsByUsername(String username);
	boolean existsByEmail(String email);
}
