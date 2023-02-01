package com.nagarro.chatgptpoc.recipegenie.controller;

import com.nagarro.chatgptpoc.recipegenie.model.GenericResponse;
import com.nagarro.chatgptpoc.recipegenie.model.PaginatedRecipe;
import com.nagarro.chatgptpoc.recipegenie.model.Recipe;
import com.nagarro.chatgptpoc.recipegenie.service.RecipeService;
import com.nagarro.chatgptpoc.recipegenie.utility.APIException;
import com.nagarro.chatgptpoc.recipegenie.utility.ErrorCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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

    @DeleteMapping(value = "/{recipeId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public GenericResponse deleteRecipe(@PathVariable String recipeId) throws APIException {
        try {
            recipeService.deleteRecipe(recipeId);
            return new GenericResponse("recipe deleted with id: " + recipeId);
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
