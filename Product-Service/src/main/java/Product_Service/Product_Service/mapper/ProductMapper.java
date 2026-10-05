package Product_Service.Product_Service.mapper;

import Product_Service.Product_Service.dto.ProductDto;
import Product_Service.Product_Service.entity.Category;
import Product_Service.Product_Service.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public ProductDto toDto(Product product){
      return ProductDto.builder()
               .id(product.getId())
               .name(product.getName())
               .description(product.getDescription())
               .price(product.getPrice())
               .discountPrice(product.getDiscountPrice())
               .quantity(product.getQuantity())
               .imageUrl(product.getImageUrl())
               .categoryId(product.getCategory().getId())
               .build();
    }


    public Product toEntity(ProductDto dto, Category category){
      return Product.builder()
                .id(dto.getId())
                .name(dto.getName())
                .description(dto.getDescription())
                .price(dto.getPrice())
                .discountPrice(dto.getDiscountPrice())
                .quantity(dto.getQuantity())
                .imageUrl(dto.getImageUrl())
                .category(category).build();
    }
}
