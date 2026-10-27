package Inventory_Service.Inventory_Service.Exception;

public class InventoryNotFoundException extends RuntimeException {

    public InventoryNotFoundException(String skuCode){
         super("Inventory Not Found For this skuCode : "+skuCode);
    }
}
