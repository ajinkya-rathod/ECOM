package Order_Service.Order_Service.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {

    private String skuCode;

    @Min(value = 1,message = "quantity must be atlist 1")
    private Integer quantity;

    @NotNull(message = "price is mandatory")
    private BigDecimal price;
}
