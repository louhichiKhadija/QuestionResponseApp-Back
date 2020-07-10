package com.example.medTest.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.medTest.entities.Question;

public interface QuestionRepository extends JpaRepository<Question, Integer>{
	List<Question> findByUserId(int userId);
}
