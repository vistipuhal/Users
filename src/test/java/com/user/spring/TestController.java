package com.user.spring;


import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.user.spring.domain.UserModel;

import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;




@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestController {

	  String user1;

	    @LocalServerPort
	    private int port;

	    TestRestTemplate restTemplate = new TestRestTemplate();

	    HttpHeaders headers = new HttpHeaders();

	    UserModel userModel;

	    @Before
	    public void setUp() throws Exception {
	         userModel = new UserModel(1,"abc",13,"aa");
	    }
	    private String createURLWithPort(String uri) {
	        return "http://localhost:" + port + uri;
	    }

	    @After
	    public void tearDown() throws Exception {
	    }

	    @Test
	    public void TestCreate() throws Exception {

	        HttpEntity<UserModel> entity = new HttpEntity<UserModel>(userModel, headers);
	        ResponseEntity<String> response = restTemplate.exchange(
	                createURLWithPort("/v0.1/UserService/Users"),
	                HttpMethod.POST, entity, String.class);
	        assertNotNull(response);
	        String actual = response.getBody();
	        System.out.println(actual);
	        assertEquals("New user is created",actual);
	    }
	    

	 
	    @Test
	    public void TestUpdate() throws Exception {

	        HttpEntity<UserModel> entity = new HttpEntity<UserModel>(userModel, headers);
	        ResponseEntity<String> response = restTemplate.exchange(
	                createURLWithPort("/v0.1/UserService/Users/update/2"),
	                HttpMethod.PUT, entity, String.class);
	        assertNotNull(response);
	        String actual = response.getBody();
	        System.out.println(actual);
	        assertEquals("updated successfully!",actual);
	    }
 
	    @Test
	    public void TestDelete() throws Exception {

	        HttpEntity<UserModel> entity = new HttpEntity<UserModel>(userModel, headers);
	        ResponseEntity<String> response = restTemplate.exchange(
	                createURLWithPort("/v0.1/UserService/delete/1"),
	                HttpMethod.DELETE, entity, String.class);
	        assertNotNull(response);
	        String actual = response.getBody();
	        System.out.println(actual);
	        assertEquals("Deleted succesfully",actual);
	    }


	    @Test
	    public void TestRead() throws Exception {

	        HttpEntity<UserModel> entity = new HttpEntity<UserModel>(userModel, headers);
	        ResponseEntity<String> response = restTemplate.exchange(
	                createURLWithPort("/v0.1/UserService/Users"),
	                HttpMethod.GET, entity, String.class);
	        assertNotNull(response);
	        String actual = response.getBody();
	        System.out.println(actual);
	        

	        assertEquals(true,actual.contains("id") && actual.contains("name") && 
	        		actual.contains("age") && actual.contains("email"));
	    }
	
}

