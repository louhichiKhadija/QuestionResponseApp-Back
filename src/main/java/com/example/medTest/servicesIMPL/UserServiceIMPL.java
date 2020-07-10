package com.example.medTest.servicesIMPL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.medTest.entities.User;
import com.example.medTest.repositories.UserRepository;
import com.example.medTest.services.UserService;

@Service(value = "userService")
public class UserServiceIMPL implements UserDetailsService, UserService{
	
	@Autowired
	private BCryptPasswordEncoder bcryptEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	
	@Override
	public void register(User user) {
		user.setPassword(bcryptEncoder.encode(user.getPassword()));
		userRepository.save(user);
	}
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(email);
		if(user == null){
			throw new UsernameNotFoundException("Invalid Email or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), AuthorityUtils.NO_AUTHORITIES);
	}
	
	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	

}
