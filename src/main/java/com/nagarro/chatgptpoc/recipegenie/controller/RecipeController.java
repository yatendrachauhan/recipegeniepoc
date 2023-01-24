package com.nagarro.chatgptpoc.recipegenie.controller;

import com.nagarro.chatgptpoc.recipegenie.model.Recipe;
import com.nagarro.chatgptpoc.recipegenie.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @PostMapping
    public Recipe addRecipe(@RequestBody Recipe recipe) {
        return recipeService.addRecipe(recipe);
    }

    @DeleteMapping("/{recipeId}")
    public void deleteRecipe(@PathVariable String recipeId) {
        recipeService.deleteRecipe(recipeId);
    }

    @GetMapping("/{recipeId}")
    public Optional<Recipe> getRecipeById(@PathVariable String recipeId) {
        return recipeService.getRecipeById(recipeId);
    }

    @GetMapping
    public List<Recipe> searchRecipes(@RequestParam String keyword) {
        return recipeService.searchRecipes(keyword);
    }

    @PutMapping("/{recipeId}")
    public Recipe updateRecipe(@PathVariable String recipeId, @RequestBody Recipe updatedRecipe) {
        return recipeService.updateRecipe(recipeId, updatedRecipe);
    }
}
