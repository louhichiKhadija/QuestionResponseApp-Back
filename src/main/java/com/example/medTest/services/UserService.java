package com.example.medTest.services;

import com.example.medTest.entities.User;

public interface UserService {
	public void register(User user);
    public User findByEmail(String email);

}
