package com.nagarro.chatgptpoc.recipegenie.repository;

import com.nagarro.chatgptpoc.recipegenie.model.Recipe;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends MongoRepository<Recipe, String> {
    List<Recipe> findByTitleContainingIgnoreCase(String keyword);
}
