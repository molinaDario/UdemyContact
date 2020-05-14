package com.molinadario.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.molinadario.project.entity.Users;

@Repository("usersRepository")
public interface UsersRepository extends JpaRepository<Users, Long> {

	public Users findByUsername(String username);
}
