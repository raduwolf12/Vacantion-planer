package com.baeldung.springsecuritythymeleaf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.baeldung.springsecuritythymeleaf.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

//	@Query(nativeQuery = true, value = "select * from accounts")
//	List<User> getAcc();

}
