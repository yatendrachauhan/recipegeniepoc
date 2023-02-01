package com.nagarro.chatgptpoc.recipegenie.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PaginatedRecipe {
    List<Recipe> recipes;
    Integer pageSize;
    Integer totalPages;
    Long totalRecipes;
}
