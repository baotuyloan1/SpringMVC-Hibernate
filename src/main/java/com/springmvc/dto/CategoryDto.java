package com.springmvc.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

/**
 * @author BAO
 * 6/22/2023
 */
public class CategoryDto {


    private int id;

    @NotBlank(message = "Category cannot be blank")
    @Length(min = 5, max = 200, message = "Product name must be between 5-512 characters")
    private String categoryName;

    private String categoryDescription;


    private int parentId;


    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @JsonIgnore
    private int level;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }


}
