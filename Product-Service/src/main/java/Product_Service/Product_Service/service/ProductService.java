package Product_Service.Product_Service.service;

import Product_Service.Product_Service.dto.ProductDto;
import Product_Service.Product_Service.entity.Product;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;

public interface ProductService {

    ProductDto createProduct(ProductDto dto);

    ProductDto updateProduct(Long id,ProductDto dto);

    String deleteProduct(Long id);

    ProductDto getByProductId(Long id);

    Page<ProductDto> getAllProduct(int page,int size,String sortBy,String sortDir);

    Page<ProductDto> searchProduct(String keyword,int page,int size);

    Page<ProductDto> filterProducts(Long categoryId,double minPrice,double maxPrice,int page,int size);

    Page<ProductDto> advancedFilter(String Keyword,Long categoryId,
                                    double minPrice,double maxPrice,int page,int size,String sortBy,String sortDir);
}
