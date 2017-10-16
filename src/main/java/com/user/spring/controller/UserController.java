package com.user.spring.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.user.spring.domain.UserModel;
import com.user.spring.service.UserService;

@RestController
@RequestMapping("/v0.1/UserService")
public class UserController {

	@Autowired
	private UserService userService;
	
	 
	@RequestMapping( value="/Users", method=RequestMethod.POST, consumes="application/json")
     public ResponseEntity<?> create(@RequestBody UserModel user)
     {
         /*Add validation code*/        
          userService.addUser(user);
         return new ResponseEntity<String>("New user is created", HttpStatus.OK) ;
        
     }

	
	@RequestMapping(value="/Users", method=RequestMethod.GET)
	   public ResponseEntity<List<UserModel>> read() {
	         
	        List<UserModel> resultList1 = userService.getAllUsers();
	         
	        return new ResponseEntity<List<UserModel>>(resultList1, HttpStatus.OK) ;
	   }
	
	@RequestMapping(value="/Users/ID/{ID}", method=RequestMethod.GET)
	   public ResponseEntity<UserModel> findById(@PathVariable(value="ID") Integer id) {
		UserModel user = userService.findById(id);
		if (user == null) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<UserModel>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<UserModel>(user, HttpStatus.OK);
		
	   }
	
	@RequestMapping(method=RequestMethod.DELETE, value="/delete/{ID}", consumes="application/json")
    public ResponseEntity<String> delete(@PathVariable(value="ID") Integer id)
    {
        /*Add validation code*/        
         userService.deleteUser(id);
        return new ResponseEntity<String>("Deleted succesfully", HttpStatus.OK) ;
        
    }
	
	@RequestMapping(value = "/Users/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateUser(@PathVariable("id") Integer id, @RequestBody UserModel user) {
        System.out.println("updated successfully!");
         
        UserModel currentUser = userService.findById(id);
         
        if (currentUser==null) {
            System.out.println("User with id " + id + " not found");
            
        }
        currentUser.setId(user.getId());
        currentUser.setName(user.getName());
        currentUser.setAge(user.getAge());
        currentUser.setEmail(user.getEmail());
         
        userService.updateUser(currentUser);
//        return new ResponseEntity<UserModel>(currentUser, HttpStatus.OK) ;
        return new ResponseEntity<String>("updated successfully!", HttpStatus.OK) ;

    }
}
