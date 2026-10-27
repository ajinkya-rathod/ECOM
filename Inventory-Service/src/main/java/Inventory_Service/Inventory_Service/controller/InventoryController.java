package Inventory_Service.Inventory_Service.controller;

import Inventory_Service.Inventory_Service.dto.InventoryRequest;
import Inventory_Service.Inventory_Service.dto.InventoryResponse;
import Inventory_Service.Inventory_Service.service.InventoryService;
import jakarta.ws.rs.POST;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.micrometer.observation.autoconfigure.ObservationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping("/{skuCode}")
    public ResponseEntity<InventoryResponse> isInStock(@PathVariable("skuCode") String skuCode){
        return ResponseEntity.ok(inventoryService.checkInventory(skuCode));
    }

    @POST
    public ResponseEntity<String> addInventory(@RequestBody InventoryRequest request){
      inventoryService.addInventory(request);
      return ResponseEntity.status(HttpStatus.CREATED).body("Inventory addeded succsfully..");
    }


}
