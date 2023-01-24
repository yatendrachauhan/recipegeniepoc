package com.nagarro.chatgptpoc.recipegenie.service;

import com.nagarro.chatgptpoc.recipegenie.model.Recipe;
import com.nagarro.chatgptpoc.recipegenie.repository.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecipeService {

    private RecipeRepository recipeRepository;

    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public Recipe addRecipe(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    public void deleteRecipe(String recipeId) {
        recipeRepository.deleteById(recipeId);
    }

    public Optional<Recipe> getRecipeById(String recipeId) {
        return recipeRepository.findById(recipeId);
    }

    public List<Recipe> searchRecipes(String keyword) {
        return recipeRepository.findByTitleContainingIgnoreCase(keyword);
    }

    public Recipe updateRecipe(String recipeId, Recipe updatedRecipe) {
        Optional<Recipe> recipe = recipeRepository.findById(recipeId);
        if (recipe.isPresent()) {
            Recipe recipeToUpdate = recipe.get();
            recipeToUpdate.setDescription(updatedRecipe.getDescription());
            recipeToUpdate.setDescription(updatedRecipe.getDescription());
            recipeToUpdate.setIngredients(updatedRecipe.getIngredients());
            recipeToUpdate.setInstructions(updatedRecipe.getInstructions());
            recipeToUpdate.setEncodedImage(updatedRecipe.getEncodedImage());
            recipeToUpdate.setCookingTime(updatedRecipe.getCookingTime());
            recipeToUpdate.setServingSize(updatedRecipe.getServingSize());
            return recipeRepository.save(recipeToUpdate);
        } else {
            throw new RuntimeException("Recipe not found");
        }
    }

}
