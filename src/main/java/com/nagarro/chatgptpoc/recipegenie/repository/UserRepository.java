package com.nagarro.chatgptpoc.recipegenie.repository;

import com.nagarro.chatgptpoc.recipegenie.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    User findByUsername(String username);
    @Query(value = "{}", fields = "{'_id' : 0}")
    List<String> findAllEmails();
}
