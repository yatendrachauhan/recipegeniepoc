package com.nagarro.chatgptpoc.recipegenie.controller;

import com.nagarro.chatgptpoc.recipegenie.model.Recipe;
import com.nagarro.chatgptpoc.recipegenie.service.RecipeService;
import com.nagarro.chatgptpoc.recipegenie.utility.APIException;
import com.nagarro.chatgptpoc.recipegenie.utility.ErrorCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE )
    public Recipe addRecipe(@RequestBody Recipe recipe) throws APIException {
        try {
            return recipeService.addRecipe(recipe);
        } catch (IllegalArgumentException ex) {
            throw new APIException(ErrorCodeEnum.RECIPE_BAD_REQUEST, ex.getMessage());
        } catch (Exception ex) {
            throw new APIException(ErrorCodeEnum.INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }

    @DeleteMapping(value = "/{recipeId}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String deleteRecipe(@PathVariable String recipeId) throws APIException {
        try {
            recipeService.deleteRecipe(recipeId);
            return "Recipe Deleted SuccessFully";
        } catch (IllegalArgumentException ex) {
            throw new APIException(ErrorCodeEnum.RECIPE_BAD_REQUEST, ex.getMessage());
        } catch (Exception ex) {
            throw new APIException(ErrorCodeEnum.INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }

    @GetMapping(value = "/{title}",
            produces = MediaType.APPLICATION_JSON_VALUE)
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

    @PutMapping(value = "/{recipeId}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
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

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Recipe> getAllRecipes() throws APIException {
        try {
            return recipeService.getAllRecipes();
        } catch (IllegalArgumentException ex) {
            throw new APIException(ErrorCodeEnum.RECIPE_BAD_REQUEST, ex.getMessage());
        } catch (Exception ex) {
            throw new APIException(ErrorCodeEnum.INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }

    @GetMapping(value = "/page/{page}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Recipe> getAllRecipesPaginated(@PathVariable int page) throws APIException {
        try {
            return recipeService.getAllRecipesPaginated(page);
        } catch (IllegalArgumentException ex) {
            throw new APIException(ErrorCodeEnum.RECIPE_BAD_REQUEST, ex.getMessage());
        } catch (Exception ex) {
            throw new APIException(ErrorCodeEnum.INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }

}
