package Order_Service.Order_Service.controller;

import Order_Service.Order_Service.dto.OrderRequest;
import Order_Service.Order_Service.dto.OrderResponse;
import Order_Service.Order_Service.service.OrderService;
import jakarta.validation.Valid;
import jakarta.ws.rs.POST;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @POST
    public ResponseEntity<OrderResponse> placeOrder(@RequestBody @Valid OrderRequest request){
       return ResponseEntity.status(HttpStatus.CREATED).body(orderService.placeOrder(request));
    }

}
