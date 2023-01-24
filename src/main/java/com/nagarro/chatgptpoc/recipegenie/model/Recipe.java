package com.nagarro.chatgptpoc.recipegenie.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.annotation.Generated;
import java.util.List;

@Document(collection = "recipes")
@Getter
@Setter
public class Recipe {

    @Id
    private String id;

    private String title;
    private String description;
    private String ingredients;
    private String instructions;
    private String cookingTime;
    private String servingSize;
    private String encodedImage;

}
