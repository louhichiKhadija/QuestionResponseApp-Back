package com.example.medTest.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.medTest.entities.User;
import com.example.medTest.securityConfig.AuthToken;
import com.example.medTest.securityConfig.TokenProvider;
import com.example.medTest.services.UserService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/auth")
public class AuthController {
	
	 @Autowired
	 private AuthenticationManager authenticationManager;

	 @Autowired
	 private TokenProvider jwtTokenUtil;

	 @Autowired
	 private UserService  userService;
	 
	 
	 @PostMapping(value = "/login")
	 public ResponseEntity<?> register(@RequestBody User loginUser) throws AuthenticationException{
		 final Authentication authentication = authenticationManager.authenticate(
				 new UsernamePasswordAuthenticationToken(
						 loginUser.getEmail(),
						 loginUser.getPassword()
						 )
				 );
		 SecurityContextHolder.getContext().setAuthentication(authentication);
	     final String token = jwtTokenUtil.generateToken(authentication);
	     return ResponseEntity.ok(new AuthToken(token));
	    }
	 
	 
	 @PostMapping(value = "/register")
	 public ResponseEntity<?> saveUser(@Valid @RequestBody User user){
	    	userService.register(user);
	        return  new ResponseEntity<String>("Congratulations! Your account has been created", HttpStatus.ACCEPTED);
	    }
	 
	    

}
