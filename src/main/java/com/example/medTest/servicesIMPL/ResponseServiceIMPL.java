package com.example.medTest.servicesIMPL;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.medTest.entities.Question;
import com.example.medTest.entities.Response;
import com.example.medTest.exception.ResourceNotFoundException;
import com.example.medTest.repositories.QuestionRepository;
import com.example.medTest.repositories.ResponseRepository;
import com.example.medTest.services.ResponseService;

@Service
public class ResponseServiceIMPL implements ResponseService {
	
	@Autowired
	private ResponseRepository responseRepository;
	
	@Autowired
	private QuestionRepository questionRepository;
	
	
	@Override
	public List<Response> getAllByQuestion(int idQuestion){
		if(!questionRepository.existsById(idQuestion)) {
            throw new ResourceNotFoundException("Question not found with id " + idQuestion);
        }
		return responseRepository.findByQuestionId(idQuestion);
		
	}
	
	@Override
	public void add(int idQuestion,Response response) {
		if(!questionRepository.existsById(idQuestion)) {
            throw new ResourceNotFoundException("Question not found with id " + idQuestion);
        }
		
		Question question=questionRepository.findById(idQuestion).get();
		response.setQuestion(question);
		responseRepository.save(response);
	}
	
	
	@Override
	public void edit(int idQuestion,int idResponse,Response response) {
		if(!questionRepository.existsById(idQuestion)) {
            throw new ResourceNotFoundException("Question not found with id " + idQuestion);
        }
		
		responseRepository.findById(idResponse).map(resp -> {
                    resp.setContent(response.getContent());
                    return responseRepository.save(resp);
                }).orElseThrow(() -> new ResourceNotFoundException("Response with id " + idResponse+" not found"));
	}
	
	@Override
	public void delete(int id) {
		 if(!responseRepository.existsById(id)) {
	            throw new ResourceNotFoundException("Response with id " + id+" not found");
	        }
		responseRepository.deleteById(id);
		
	}

}
