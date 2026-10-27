package Order_Service.Order_Service.service;

import Order_Service.Order_Service.client.InventoryFiegnClient;
import Order_Service.Order_Service.dto.OrderRequest;
import Order_Service.Order_Service.dto.OrderResponse;
import Order_Service.Order_Service.entity.Order;
import Order_Service.Order_Service.inventoryDto.InventoryResponse;
import Order_Service.Order_Service.mapper.OrderMapper;
import Order_Service.Order_Service.repo.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final InventoryFiegnClient inventoryFiegnClient;

    @Override
    public OrderResponse placeOrder(OrderRequest orderRequest) {

       InventoryResponse inventory = inventoryFiegnClient.isInStock(orderRequest.getSkuCode());
       if(!inventory.isInStock()){
           throw new RuntimeException("Product is out of Stock");
       }

        Order order = orderMapper.toEntity(orderRequest);
        order.setOrderNumber(UUID.randomUUID().toString());
        order.setOrderStatus("CREATED");
        orderRepository.save(order);

        return orderMapper.toResponse(order);
    }
}
