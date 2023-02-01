package com.nagarro.chatgptpoc.recipegenie.service;

import com.nagarro.chatgptpoc.recipegenie.model.PaginatedRecipe;
import com.nagarro.chatgptpoc.recipegenie.model.Recipe;
import com.nagarro.chatgptpoc.recipegenie.repository.RecipeRepository;
import com.nagarro.chatgptpoc.recipegenie.utility.APIException;
import com.nagarro.chatgptpoc.recipegenie.utility.ErrorCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
public class RecipeService {

    private RecipeRepository recipeRepository;

    @Autowired
    public EmailServiceSendGrid emailService;

    @Autowired
    public EmailServiceMailTrap emailService2;

    @Autowired
    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Value("${app.page-size}")
    private int pageSize;

    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    public PaginatedRecipe getAllRecipesPaginated(int page) {
        Pageable pageable = PageRequest.of(page-1, pageSize);
        PaginatedRecipe recipePage = new PaginatedRecipe();
        Page<Recipe> pageContent = recipeRepository.findAll(pageable);
        recipePage.setRecipes(pageContent.getContent());
        recipePage.setTotalRecipes(pageContent.getTotalElements());
        recipePage.setTotalPages(pageContent.getTotalPages());
        recipePage.setPageSize(pageContent.getNumberOfElements());
        return recipePage;
    }

    public Recipe addRecipe(Recipe recipe) throws APIException {
        if (recipe == null) {
            throw new IllegalArgumentException("Recipe cannot be null");
        }
        if (!StringUtils.hasText(recipe.getTitle())) {
            throw new IllegalArgumentException("Recipe title cannot be null or empty");
        }
        if(recipeRepository.findByTitle(recipe.getTitle()) != null){
            throw new APIException(ErrorCodeEnum.RECIPE_ALREADY_EXIST);
        }

        Recipe newRecipe = recipeRepository.save(recipe);
       /* emailService.sendEmail("Recipe Added", "Recipe Added: " + newRecipe);*/
        emailService2.sendEmail("Recipe Added", "Recipe Added: " + newRecipe);
        return newRecipe;
    }

    public void deleteRecipe(String recipeId) throws APIException {
        if (!StringUtils.hasText(recipeId)) {
            throw new IllegalArgumentException("Recipe id cannot be null or empty");
        }
        if (recipeRepository.existsById(recipeId)) {
            recipeRepository.deleteById(recipeId);
            /*emailService.sendEmail("Recipe Deleted", "Recipe with id: " + recipeId + " deleted.");*/
            emailService2.sendEmail("Recipe Deleted", "Recipe with id: " + recipeId + " deleted.");
        } else {
            throw new APIException(ErrorCodeEnum.RECIPE_NOT_FOUND);
        }
    }

    public Optional<Recipe> getRecipeById(String recipeId) {
        return recipeRepository.findById(recipeId);
    }

    public List<Recipe> getRecipeByTitle(String title) throws APIException {
        if (!StringUtils.hasText(title)) {
            throw new IllegalArgumentException("Recipe title cannot be null or empty");
        }
        List<Recipe> recipe =  recipeRepository.findByTitleContainingIgnoreCase(title);
        if(recipe.isEmpty()){
            throw new APIException(ErrorCodeEnum.RECIPE_NOT_FOUND);
        }
        return recipe;
    }

    public Recipe updateRecipe(String recipeId, Recipe updatedRecipe) throws APIException {
        Recipe recipeToUpdate = recipeRepository.findById(recipeId).orElseThrow(() -> new APIException(ErrorCodeEnum.RECIPE_NOT_FOUND));
        if (updatedRecipe == null) {
            throw new IllegalArgumentException("Recipe cannot be null");
        }
        if (!StringUtils.hasText(updatedRecipe.getTitle())) {
            throw new IllegalArgumentException("Recipe title cannot be null or empty");
        }
        recipeToUpdate.setTitle(updatedRecipe.getTitle());
        recipeToUpdate.setDescription(updatedRecipe.getDescription());
        recipeToUpdate.setIngredients(updatedRecipe.getIngredients());
        recipeToUpdate.setInstructions(updatedRecipe.getInstructions());
        recipeToUpdate.setEncodedImage(updatedRecipe.getEncodedImage());
        recipeToUpdate.setCookingTime(updatedRecipe.getCookingTime());
        recipeToUpdate.setServingSize(updatedRecipe.getServingSize());
        recipeRepository.save(recipeToUpdate);
       /* emailService.sendEmail("Recipe Updated", "Updated Recipe: " + recipeToUpdate);*/
        emailService2.sendEmail("Recipe Updated", "Updated Recipe: " + recipeToUpdate);
        return recipeToUpdate;
    }

}
