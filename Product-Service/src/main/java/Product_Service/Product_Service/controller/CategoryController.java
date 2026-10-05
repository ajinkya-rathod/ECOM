package Product_Service.Product_Service.controller;

import Product_Service.Product_Service.dto.CategoryDto;
import Product_Service.Product_Service.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {


    private final CategoryService categoryService;

    @PostMapping
    ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto dto){

        return ResponseEntity.ok().body(categoryService.createCategory(dto));

    }

    @PutMapping("{id}")
    ResponseEntity<CategoryDto> updateCategory(@PathVariable("id") Long id,@RequestBody CategoryDto dto){

        return ResponseEntity.ok().body(categoryService.updateCategory(id,dto));

    }

    @GetMapping("/{id}")
    ResponseEntity<CategoryDto> getCategoryById(@PathVariable("id") Long id){
      return ResponseEntity.ok().body(categoryService.getCategoryByid(id));
    }

    @GetMapping
    ResponseEntity<List<CategoryDto>> getAllCategory(){
        return ResponseEntity.ok().body(categoryService.getAllCategories());
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteCategory(@PathVariable("id") Long id){
        categoryService.deleteById(id);
        return ResponseEntity.ok("category deleted with id "+id);
    }

}
