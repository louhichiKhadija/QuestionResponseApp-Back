package com.example.medTest.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.medTest.entities.Response;

public interface ResponseRepository extends JpaRepository<Response, Integer>{
	List<Response> findByQuestionId(int questionId);

}
