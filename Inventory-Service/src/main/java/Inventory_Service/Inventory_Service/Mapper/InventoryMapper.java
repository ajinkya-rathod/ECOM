package Inventory_Service.Inventory_Service.Mapper;

import Inventory_Service.Inventory_Service.dto.InventoryRequest;
import Inventory_Service.Inventory_Service.dto.InventoryResponse;
import Inventory_Service.Inventory_Service.entity.Inventory;
import org.springframework.stereotype.Component;

@Component
public class InventoryMapper {

    public Inventory toEntity(InventoryRequest request){
       Inventory inventory = new Inventory();
       inventory.setSkuCode(request.getSkuCode());
       inventory.setQuantity(request.getQuantity());
       return inventory;
    }


    public InventoryResponse toResponse(Inventory inventory){
       return new InventoryResponse(inventory.getSkuCode(),
               inventory.getQuantity()>0,inventory.getQuantity());
    }

}
