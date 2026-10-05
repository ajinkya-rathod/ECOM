package Product_Service.Product_Service.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto {

    private Long id;

    @NotBlank(message = "name must be required ")
    private String name;

    @NotBlank
    private String description;

    @Column(nullable = false)

    @Positive(message = "price must be positive")
    private double price;
    private double discountPrice;

    @Min(value = 0,message = "quantity must be 0 or more ")
    private int quantity;
    private String brand;
    private String imageUrl;

    @NotNull(message = "category is required")
    private Long categoryId;


}
