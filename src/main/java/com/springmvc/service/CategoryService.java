package com.springmvc.service;

import com.springmvc.dto.CategoryDto;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author BAO
 * 6/22/2023
 */
public interface CategoryService {

     Page<CategoryDto> pageCategories ();

    void saveCategory(CategoryDto categoryDto);

    List<CategoryDto> getRootCategoryList();
}
