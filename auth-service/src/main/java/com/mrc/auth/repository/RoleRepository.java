package com.mrc.auth.repository;

import org.springframework.data.repository.CrudRepository;

import com.mrc.auth.entity.Role;

public interface RoleRepository extends CrudRepository<Role, Long>{
	Role findByRoleName(String roleName);
}
