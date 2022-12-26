package com.cos.mysns.domain.user;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

//JpaRepository상속했으면 자동으로 Ioc됨.
public interface UserRepository extends JpaRepository<User, Integer>{

	// JPA query method
	User findByUsername(String username);
	List<User> findAll();



}
