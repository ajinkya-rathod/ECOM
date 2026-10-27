package Order_Service.Order_Service.mapper;

import Order_Service.Order_Service.dto.OrderRequest;
import Order_Service.Order_Service.dto.OrderResponse;
import Order_Service.Order_Service.entity.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    public Order toEntity(OrderRequest orderRequest){
         Order order = new Order();
         order.setSkuCode(orderRequest.getSkuCode());
         order.setQuantity(orderRequest.getQuantity());
         order.setPrice(orderRequest.getPrice());

         return order;

    }

    public OrderResponse toResponse(Order order){
       return new OrderResponse(order.getOrderNumber(),order.getOrderStatus());
    }
}
