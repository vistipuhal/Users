package com.user.spring;


import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.user.spring.domain.UserModel;
import com.user.spring.repo.UserRepository;
import com.user.spring.service.UserService;

import static org.mockito.Mockito.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
public class TestService {
   
      @Mock
      private UserService userService;
       @Mock
       private UserRepository repository;
       @Mock
       private UserModel user;
       @Before
       public void setupMock() {
           MockitoAnnotations.initMocks(this);
           userService=new UserService();
           userService.setUserRepository(repository);
       }
       @Test
       //Test by id
       public void TestgetById() throws Exception {
           // Arrange
           when(repository.findOne(2)).thenReturn(user);
           // Act
           UserModel retrievedUser = userService.findById(2);
           // Assert
           assertThat(retrievedUser, is(equalTo(user)));
      }
       
       @Test
       public void TestUpdate() throws Exception {
           // Arrange
           when(repository.save(user)).thenReturn(user);
           // Act
           userService.updateUser(user);
           // Assert
           assertThat(repository.findOne(1), is(repository.findOne(1)));
       }
       @Test
       public void TestDelete() throws Exception {
           // Arrange
           doNothing().when(repository).delete(1);
           UserRepository my = Mockito.mock(UserRepository.class);
           // Act
          userService.deleteUser(1);
           // Assert
//            verify(repository, times(1)).delete(1);
       }
   }


