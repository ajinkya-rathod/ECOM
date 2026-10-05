package Product_Service.Product_Service.service;

import Product_Service.Product_Service.dto.CategoryDto;
import Product_Service.Product_Service.entity.Category;
import Product_Service.Product_Service.mapper.CategoryMapper;
import Product_Service.Product_Service.repo.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{


    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;


    @Override
    public CategoryDto createCategory(CategoryDto dto) {
        Category category = categoryMapper.toEntity(dto);
        return categoryMapper.toDto(categoryRepository.save(category));
    }

    @Override
    public CategoryDto updateCategory(Long id, CategoryDto dto) {

        Category category = categoryRepository
                .findById(id).orElseThrow(()->new RuntimeException("category not found with this categoryid "+id));

        category.setName(dto.getName());
        category.setDescription(dto.getDescription());
        category.setParentId(dto.getParentId());


        return categoryMapper.toDto(categoryRepository.save(category));
    }

    @Override
    public void deleteById(Long id) {
         categoryRepository.deleteById(id);
         log.info("Category deleted successfully with id: {}", id);

    }

    @Override
    public CategoryDto getCategoryByid(Long id) {

        Category category = categoryRepository
                .findById(id).orElseThrow(()-> new RuntimeException("category not found"));

        return categoryMapper.toDto(category);
    }

    @Override
    public List<CategoryDto> getAllCategories() {

        List<Category> allCategory = categoryRepository.findAll();
        return allCategory.stream().map(categoryMapper::toDto).toList();
    }
}
