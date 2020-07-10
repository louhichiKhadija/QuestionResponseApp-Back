package com.example.medTest.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.medTest.entities.User;


public interface UserRepository extends JpaRepository<User, Integer> {
	User findByEmail(String email);
}
