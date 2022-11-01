package com.nt.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.nt.model.User;

public interface IUserRepo extends PagingAndSortingRepository<User, Integer> {
	
	 @Query("SELECT u FROM User u WHERE u.uname = :username")
	 public Optional<User> getUserByUsername(@Param("username") String username);

}
