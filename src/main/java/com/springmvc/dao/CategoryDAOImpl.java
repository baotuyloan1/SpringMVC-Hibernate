package com.springmvc.dao;

import com.springmvc.dto.CategoryDto;
import com.springmvc.entity.Category;
import com.springmvc.mapper.MapStructMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

/**
 * @author BAO 6/22/2023
 */
@Repository
public class CategoryDAOImpl implements CategoryDAO {

  private final EntityManagerFactory entityManagerFactory;

  private final MapStructMapper mapStructMapper;

  public CategoryDAOImpl(
      EntityManagerFactory entityManagerFactory, MapStructMapper mapStructMapper) {
    this.entityManagerFactory = entityManagerFactory;
    this.mapStructMapper = mapStructMapper;
  }

  @Override
  public Page<CategoryDto> pageCategories(PageRequest pageRequest) {
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    Query query = entityManager.createQuery("SELECT ca FROM Category ca");
    int pageNumber = pageRequest.getPageNumber();
    int pageSize = pageRequest.getPageSize();
    query.setFirstResult((pageNumber) * pageSize);
    query.setMaxResults(pageSize);
    List<CategoryDto> categoryDtoList = query.getResultList();

    Query queryTotal = entityManager.createQuery("SELECT count (ca.id) FROM Category ca");
    long countResult = (long) queryTotal.getSingleResult();
    return null;
  }

  @Override
  public void saveCategory(CategoryDto categoryDto) {
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    Category category = mapStructMapper.categoryDtoToCategory(categoryDto);
    entityManager.persist(category);
    entityManager.flush();
    entityManager.clear();
  }
}
