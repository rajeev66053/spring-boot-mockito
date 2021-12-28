package com.java.spring.mockito.api.repository;

import com.java.spring.mockito.api.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<User,Integer> {
    List<User> findByAddress(String address);

}