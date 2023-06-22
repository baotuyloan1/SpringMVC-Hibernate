package com.springmvc.dao;

import com.springmvc.dto.CategoryDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * @author BAO
 * 6/22/2023
 */

public interface CategoryDAO {

    Page<CategoryDto> pageCategories(PageRequest pageRequest);

    void saveCategory (CategoryDto categoryDto);
}
