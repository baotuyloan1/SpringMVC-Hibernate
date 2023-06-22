package com.springmvc.restcontroller;

import com.springmvc.entity.Category;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author BAO
 * 6/22/2023
 */
@RestController
@RequestMapping("/api/categories")
public class CategoryRestController {

    @GetMapping(value = "/",  produces = MediaType.APPLICATION_JSON_VALUE)
    public Category listCategories() {
        Category category = new Category();
        category.setCategoryName("hello");
        return category;
    }

}