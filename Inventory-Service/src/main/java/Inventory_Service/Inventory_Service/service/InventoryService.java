package Inventory_Service.Inventory_Service.service;


import Inventory_Service.Inventory_Service.Exception.InventoryNotFoundException;
import Inventory_Service.Inventory_Service.Mapper.InventoryMapper;
import Inventory_Service.Inventory_Service.dto.InventoryRequest;
import Inventory_Service.Inventory_Service.dto.InventoryResponse;
import Inventory_Service.Inventory_Service.entity.Inventory;
import Inventory_Service.Inventory_Service.repo.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryService{

    private final InventoryRepository inventoryRepository;
    private final InventoryMapper inventoryMapper;

    public InventoryResponse checkInventory(String skuCode){

        Inventory inventory = inventoryRepository.
                findBySkuCode(skuCode).orElseThrow(()-> new InventoryNotFoundException(skuCode));

        return inventoryMapper.toResponse(inventory);

    }

    public void addInventory(InventoryRequest request){

        Inventory inventory = inventoryMapper.toEntity(request);

        inventoryRepository.save(inventory);

        log.info("inventory added successfully ");




    }

}
