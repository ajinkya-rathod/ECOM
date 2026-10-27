package Order_Service.Order_Service.service;

import Order_Service.Order_Service.dto.OrderRequest;
import Order_Service.Order_Service.dto.OrderResponse;

public interface OrderService {

    OrderResponse placeOrder(OrderRequest orderRequest);
}
