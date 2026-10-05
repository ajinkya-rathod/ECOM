package Product_Service.Product_Service.controller;

import Product_Service.Product_Service.dto.CategoryDto;
import Product_Service.Product_Service.dto.ProductDto;
import Product_Service.Product_Service.service.CategoryService;
import Product_Service.Product_Service.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto dto){

        return ResponseEntity.ok().body(productService.createProduct(dto));

    }

    @PutMapping("/{id}")
    ResponseEntity<ProductDto> updateProduct(@PathVariable("id") Long id,@RequestBody ProductDto dto){

        return ResponseEntity.ok().body(productService.updateProduct(id,dto));

    }

    @DeleteMapping("/id")
    ResponseEntity<String> deleteProduct(@PathVariable("id") Long id){
       return ResponseEntity.ok().body(productService.deleteProduct(id));

    }

    @GetMapping("/id")
    ResponseEntity<ProductDto> getProductById(@PathVariable("id") Long id){

        return ResponseEntity.ok().body(productService.getByProductId(id));
    }

    @GetMapping
    public ResponseEntity<Page<ProductDto>> getAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir
    ){
        return ResponseEntity.ok().body(productService.getAllProduct(page,size,sortBy,sortDir));
    }

    @GetMapping("/search}")
    ResponseEntity<Page<ProductDto>> SearchProduct(@RequestParam String keyword,
                                             @RequestParam(defaultValue = "0") int page,
                                             @RequestParam(defaultValue = "10") int size){


     return ResponseEntity.ok().body(productService.searchProduct(keyword,page,size));
    }

    @GetMapping("/filter")
    ResponseEntity<Page<ProductDto>> filterProduct(
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) double minPrice,
            @RequestParam(required = false) double maxPrice,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){

        return ResponseEntity.ok().body(productService.filterProducts(categoryId,minPrice,maxPrice,page,size));

        }
}
