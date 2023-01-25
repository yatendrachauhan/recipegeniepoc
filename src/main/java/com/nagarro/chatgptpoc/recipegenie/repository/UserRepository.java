package com.nagarro.chatgptpoc.recipegenie.repository;

import com.nagarro.chatgptpoc.recipegenie.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    User findByUsername(String username);
    User findByEmail(String email);
}
