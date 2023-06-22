package com.springmvc.mapper;

import com.springmvc.dto.CategoryDto;
import com.springmvc.entity.Category;
import org.mapstruct.Mapper;

/**
 * @author BAO
 * 6/22/2023
 */
@Mapper(componentModel = "spring")
public interface MapStructMapper {


    Category categoryDtoToCategory(CategoryDto categoryDto);

    CategoryDto categoryToCategoryDto(Category category);

}
