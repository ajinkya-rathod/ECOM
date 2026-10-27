package Order_Service.Order_Service.client;

import Order_Service.Order_Service.inventoryDto.InventoryResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "Inventory-Service")
public interface InventoryFiegnClient {

      @GetMapping("/api/inventory/{skuCode}")
    InventoryResponse isInStock(@PathVariable String skuCode);

}
