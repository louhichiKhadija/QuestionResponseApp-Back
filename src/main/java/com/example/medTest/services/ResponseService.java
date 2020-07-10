package com.example.medTest.services;

import java.util.List;
import com.example.medTest.entities.Response;


public interface ResponseService {
	public List<Response> getAllByQuestion(int idQuestion);
	public void add(int idQuestion, Response response);
	public void edit(int idQuestion,int idResponse,Response response);
	public void delete(int id);
	
}
