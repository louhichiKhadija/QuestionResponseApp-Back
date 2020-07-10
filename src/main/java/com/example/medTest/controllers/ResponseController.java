package com.example.medTest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.medTest.entities.Response;
import com.example.medTest.services.ResponseService;

@CrossOrigin(origins = "*")
@RestController
public class ResponseController {
	
	@Autowired
	private ResponseService responseService;
	
	@GetMapping(value="/question/{idQuestion}/responses")
	public ResponseEntity<?> getAllByQuestion(@PathVariable int idQuestion){
		return new ResponseEntity<>(responseService.getAllByQuestion(idQuestion),HttpStatus.OK);
	}
	
	@PostMapping(value="/question/{id}/response")
	public ResponseEntity<?> add(@PathVariable int id, @RequestBody Response response){
		responseService.add(id, response);
		return new ResponseEntity<>("Response added successfully",HttpStatus.OK);
	}
	
	@PutMapping(value="/question/{idQuestion}/edit-response/{idResponse}")
	public ResponseEntity<?> edit(@PathVariable int idQuestion, 
			                      @PathVariable int idResponse,
			                      @RequestBody Response response){
		responseService.edit(idQuestion, idResponse, response);
		return new ResponseEntity<>("Response edited successfully",HttpStatus.OK);
	}
	
	@DeleteMapping(value="delete-response/{id}")
	public  ResponseEntity<?> delete(@PathVariable int id){
		responseService.delete(id);
		return new ResponseEntity<>("Response deleted successfully",HttpStatus.OK);
	}

}
