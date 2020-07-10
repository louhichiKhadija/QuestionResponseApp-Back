package com.example.medTest.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.medTest.entities.Question;

public interface QuestionService {
	public List<Question> getAllQuestions();
	public Question getOne(int id);
	public List<Question> getByUser(int idUser);
	public void add(int idUser, Question question);
	public void delete(int id);
	public Question edit(int id,Question newQuestion);
	public void uploadDocument(MultipartFile file, int id);
	public String getFileName(int id);


}
