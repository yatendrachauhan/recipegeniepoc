package com.nagarro.chatgptpoc.recipegenie.repository;

import com.nagarro.chatgptpoc.recipegenie.model.AdminUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AdminUserRepository extends MongoRepository<AdminUser, String> {
    AdminUser findByUsernameAndPassword(String username, String password);

    AdminUser findByUsername(String username);
}

