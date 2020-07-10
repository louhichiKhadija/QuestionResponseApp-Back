package com.example.medTest.servicesIMPL;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.medTest.entities.Question;
import com.example.medTest.entities.User;
import com.example.medTest.exception.ResourceNotFoundException;
import com.example.medTest.repositories.QuestionRepository;
import com.example.medTest.repositories.UserRepository;
import com.example.medTest.services.QuestionService;
import com.example.medTest.utils.FileStorageService;



@Service(value="questionService")
public class QuestionServiceIMPL implements QuestionService{
	
	@Autowired
	private QuestionRepository questionRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private FileStorageService fileStorage;
	
	
	@Override
	public List<Question> getAllQuestions(){
		return questionRepository.findAll();
	}
	
	@Override
	public Question getOne(int id) {
		return questionRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Question not found with id " + id));
	}
	
	public List<Question> getByUser(int userId){
		return questionRepository.findByUserId(userId);
	}
	
	@Override
	public void add(int idUser,Question question) {
		User user=userRepository.getOne(idUser);
		question.setUser(user);
		questionRepository.save(question);
	}
	
	@Override
	public void delete(int id) {
		questionRepository.deleteById(id);
	}
	
	@Override
	public Question edit(int id, Question newQuestion) {
		if(!questionRepository.findById(id).isPresent())
			throw new ResourceNotFoundException("Question not found with id " + id);
		else {
			Question oldQuestion= questionRepository.findById(id).get();
			oldQuestion.setTitle(newQuestion.getTitle());
			oldQuestion.setContent(newQuestion.getContent());
			oldQuestion.setDocument(newQuestion.getDocument());
			oldQuestion.setSpeciality(newQuestion.getSpeciality());
			oldQuestion.setCategory(newQuestion.getCategory());
			return questionRepository.save(oldQuestion);
		}
	}
		
	@Override
	public void uploadDocument(MultipartFile file,int idQuestion) {
		String documentPath=fileStorage.storeFile(file);
		Question question=questionRepository.findById(idQuestion).get();
		question.setDocument(documentPath);
		questionRepository.save(question);
	}
	
	@Override
	public String getFileName(int id) {
		Question question=questionRepository.findById(id).get();
		return question.getDocument();
	}
		
	

}
