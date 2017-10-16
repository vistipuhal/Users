package com.user.spring.Interface;

import java.util.List;

import com.user.spring.domain.UserModel;



public interface UserInterface {

	public List<UserModel> getAllUsers();
	public UserModel addUser(UserModel usermodel);
	public boolean  deleteUser(Integer id);
	public void updateUser(UserModel currentUser);
	
}
