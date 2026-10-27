package Inventory_Service.Inventory_Service.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class    InventoryRequest {

    @NotBlank(message = "SKU must not be blank")
    private String skuCode;

    @Min(value = 0,message = "quantity must be 0 or greater than 0 ")
    private Integer quantity;
}
