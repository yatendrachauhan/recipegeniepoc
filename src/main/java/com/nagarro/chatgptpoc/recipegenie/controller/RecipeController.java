package com.nagarro.chatgptpoc.recipegenie.controller;

import com.nagarro.chatgptpoc.recipegenie.model.Recipe;
import com.nagarro.chatgptpoc.recipegenie.service.RecipeService;
import com.nagarro.chatgptpoc.recipegenie.utility.APIException;
import com.nagarro.chatgptpoc.recipegenie.utility.ErrorCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @PostMapping
    public Recipe addRecipe(@RequestBody Recipe recipe) throws APIException {
        try {
            return recipeService.addRecipe(recipe);
        } catch (IllegalArgumentException ex) {
            throw new APIException(ErrorCodeEnum.RECIPE_BAD_REQUEST, ex.getMessage());
        } catch (Exception ex) {
            throw new APIException(ErrorCodeEnum.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{recipeId}")
    public void deleteRecipe(@PathVariable String recipeId) throws APIException {
        try {
            recipeService.deleteRecipe(recipeId);
        } catch (IllegalArgumentException ex) {
            throw new APIException(ErrorCodeEnum.RECIPE_BAD_REQUEST, ex.getMessage());
        } catch (Exception ex) {
            throw new APIException(ErrorCodeEnum.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{title}")
    public List<Recipe> searchRecipes(@PathVariable String title) throws APIException {
        try {
            return recipeService.getRecipeByTitle(title);
        } catch (IllegalArgumentException ex) {
            throw new APIException(ErrorCodeEnum.RECIPE_BAD_REQUEST, ex.getMessage());
        } catch (APIException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new APIException(ErrorCodeEnum.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{recipeId}")
    public Recipe updateRecipe(@PathVariable String recipeId, @RequestBody Recipe updatedRecipe) throws APIException {
        try {
            return recipeService.updateRecipe(recipeId, updatedRecipe);
        } catch (IllegalArgumentException ex) {
            throw new APIException(ErrorCodeEnum.RECIPE_BAD_REQUEST, ex.getMessage());
        } catch (APIException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new APIException(ErrorCodeEnum.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public List<Recipe> getAllRecipes() throws APIException {
        try {
            return recipeService.getAllRecipes();
        } catch (IllegalArgumentException ex) {
            throw new APIException(ErrorCodeEnum.RECIPE_BAD_REQUEST, ex.getMessage());
        } catch (Exception ex) {
            throw new APIException(ErrorCodeEnum.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/page/{page}")
    public List<Recipe> getAllRecipesPaginated(@PathVariable int page) throws APIException {
        try {
            return recipeService.getAllRecipesPaginated(page);
        } catch (IllegalArgumentException ex) {
            throw new APIException(ErrorCodeEnum.RECIPE_BAD_REQUEST, ex.getMessage());
        } catch (Exception ex) {
            throw new APIException(ErrorCodeEnum.INTERNAL_SERVER_ERROR);
        }
    }

}
