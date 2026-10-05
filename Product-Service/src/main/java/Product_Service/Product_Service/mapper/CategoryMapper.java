package Product_Service.Product_Service.mapper;

import Product_Service.Product_Service.dto.CategoryDto;
import Product_Service.Product_Service.entity.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public CategoryDto toDto(Category category){

       return CategoryDto.builder()
               .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .parentId(category.getParentId()).build();
    }

    public Category toEntity(CategoryDto categoryDto){
      return Category.builder().id(categoryDto.getId())
               .name(categoryDto.getName())
               .description(categoryDto.getDescription())
               .parentId(categoryDto.getParentId())
               .build();


    }
}
