package com.user.spring.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.user.spring.domain.UserModel;


@Repository
public interface UserRepository extends CrudRepository<UserModel, Integer>{

}
