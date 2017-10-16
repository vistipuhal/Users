package com.user.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.spring.Interface.UserInterface;
import com.user.spring.domain.UserModel;
import com.user.spring.repo.UserRepository;

@Service
public class UserService implements UserInterface{

	@Autowired
	public UserRepository userRepository;
	
	public UserRepository getUserRepository() {
		return userRepository;
	}

	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public List<UserModel> getAllUsers() {
	
		 List <UserModel> movieList= (List<UserModel>)userRepository.findAll();
		   return movieList;
	}

	public UserModel findById(Integer id) {
		UserModel user = userRepository.findOne(id);
		return user;
		
	}
	
	@Override
	public UserModel addUser(UserModel usermodel) {
		 userRepository.save(usermodel);
		 return usermodel;
	}
	

	@Override
	public boolean deleteUser(Integer id) {
		userRepository.delete(id);
		return true;
	}

	@Override
	public void updateUser(UserModel currentUser) {
		userRepository.save(currentUser);
		
	}



}
