package com.springmvc.service.impl;

import com.springmvc.dao.CategoryDAO;
import com.springmvc.dto.CategoryDto;
import com.springmvc.service.CategoryService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author BAO 6/22/2023
 */
@Service
public class CategoryServiceImpl implements CategoryService {

  private final CategoryDAO categoryDAO;

  public CategoryServiceImpl(CategoryDAO categoryDAO) {
    this.categoryDAO = categoryDAO;
  }

  @Override
  public Page<CategoryDto> pageCategories() {
    return null;
  }

  @Override
  public void saveCategory(CategoryDto categoryDto) {
    categoryDAO.saveCategory(categoryDto);
  }

  @Override
  public List<CategoryDto> getRootCategoryList() {
    return null;
  }
}
