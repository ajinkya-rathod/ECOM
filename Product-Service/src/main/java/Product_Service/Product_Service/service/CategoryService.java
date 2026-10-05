package Product_Service.Product_Service.service;

import Product_Service.Product_Service.dto.CategoryDto;
import Product_Service.Product_Service.entity.Category;

import java.util.List;

public interface CategoryService {

    CategoryDto createCategory(CategoryDto dto);
    CategoryDto updateCategory(Long id,CategoryDto dto);
    void deleteById(Long id);
    CategoryDto getCategoryByid(Long id);

    List<CategoryDto> getAllCategories();


}
