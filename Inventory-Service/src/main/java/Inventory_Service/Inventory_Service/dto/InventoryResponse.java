package Inventory_Service.Inventory_Service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventoryResponse {

    private String skuCode;
    private boolean inStock;
    private Integer availableQuantity;
}
