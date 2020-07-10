package com.example.medTest.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.medTest.entities.Question;
import com.example.medTest.services.QuestionService;
import com.example.medTest.utils.FileStorageService;

@CrossOrigin(origins = "*")
@RestController
public class QuestionController {
	
	@Autowired
	 private QuestionService questionService;
	
	@Autowired
	private FileStorageService fileStorage; 
	
	
	@GetMapping(value="/questions")
	public ResponseEntity<?> getAll(){
		return new ResponseEntity<>(questionService.getAllQuestions(),HttpStatus.OK);
	}
	
	@GetMapping(value="/question/{id}")
	public ResponseEntity<?> getOneQuestion(@PathVariable int id){
		return new ResponseEntity<>(questionService.getOne(id),HttpStatus.OK);
	}
	
	@GetMapping(value="/questions/{userId}")
	public ResponseEntity<?> getQuestionByUser(@PathVariable int userId){
		return new ResponseEntity<>(questionService.getByUser(userId),HttpStatus.OK);
	}
	
	
	@PostMapping(value="/add-question/{idUser}")
	public ResponseEntity<?> add(@RequestBody Question question, @PathVariable int idUser){
		questionService.add(idUser, question);
		return new ResponseEntity<>("Question added successfully", HttpStatus.OK);
	}
	
	@DeleteMapping(value="/delete-question/{id}")
	public ResponseEntity<?> delete(@PathVariable int id){
		questionService.delete(id);
		return new ResponseEntity<>("Question deleted successfully", HttpStatus.OK);
	}
	
	
	@PutMapping(value="/edit-question/{id}")
	public ResponseEntity<?> update(@RequestBody Question question, @PathVariable int id){
		questionService.edit(id, question);
		return new ResponseEntity<>("Question edited successfully", HttpStatus.OK);
	}
	
	@PostMapping(value="question/{id}/upload-document", consumes =  {"multipart/form-data"})
    public void uploadImage(@RequestParam("file")  MultipartFile file, @PathVariable int id) {
    	questionService.uploadDocument(file,id);
    }
	
	@GetMapping(value = "question/{id}/get-document")
	public Resource getPhoto(@PathVariable int id) throws IOException {
	    	String fileName = questionService.getFileName(id);
	    	return fileStorage.loadFileAsResource(fileName);}
	

}
