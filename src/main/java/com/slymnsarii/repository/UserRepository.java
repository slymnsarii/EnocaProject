package com.slymnsarii.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.slymnsarii.domain.User;
import com.slymnsarii.exception.ResourceNotFoundException;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	
	Optional<User> findByUserName(String userName) throws ResourceNotFoundException;
	

}