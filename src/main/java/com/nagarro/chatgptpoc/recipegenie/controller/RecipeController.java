package com.nagarro.chatgptpoc.recipegenie.controller;

import com.nagarro.chatgptpoc.recipegenie.model.PaginatedRecipe;
import com.nagarro.chatgptpoc.recipegenie.model.Recipe;
import com.nagarro.chatgptpoc.recipegenie.service.RecipeService;
import com.nagarro.chatgptpoc.recipegenie.utility.APIException;
import com.nagarro.chatgptpoc.recipegenie.utility.ErrorCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        } catch (APIException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new APIException(ErrorCodeEnum.INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }

    @DeleteMapping("/{recipeId}")
    public ResponseEntity<String> deleteRecipe(@PathVariable String recipeId) throws APIException {
        try {
            recipeService.deleteRecipe(recipeId);
            return new ResponseEntity<>("Recipe with id: " + recipeId + " deleted.", HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            throw new APIException(ErrorCodeEnum.RECIPE_BAD_REQUEST, ex.getMessage());
        } catch (APIException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new APIException(ErrorCodeEnum.INTERNAL_SERVER_ERROR, ex.getMessage());
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
            throw new APIException(ErrorCodeEnum.INTERNAL_SERVER_ERROR, ex.getMessage());
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
            throw new APIException(ErrorCodeEnum.INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }

    @GetMapping
    public List<Recipe> getAllRecipes() throws APIException {
        try {
            return recipeService.getAllRecipes();
        } catch (IllegalArgumentException ex) {
            throw new APIException(ErrorCodeEnum.RECIPE_BAD_REQUEST, ex.getMessage());
        } catch (Exception ex) {
            throw new APIException(ErrorCodeEnum.INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }

    @GetMapping("/page/{page}")
    public PaginatedRecipe getAllRecipesPaginated(@PathVariable int page) throws APIException {
        try {
            return recipeService.getAllRecipesPaginated(page);
        } catch (IllegalArgumentException ex) {
            throw new APIException(ErrorCodeEnum.RECIPE_BAD_REQUEST, ex.getMessage());
        } catch (Exception ex) {
            throw new APIException(ErrorCodeEnum.INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }

}
