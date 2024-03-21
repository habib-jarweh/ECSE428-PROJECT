package ecse428.backend.controller;

import ecse428.backend.service.OrderService;
import ecse428.backend.dto.OrderDto;
import ecse428.backend.service.MealService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @PostMapping("/create")
    public ResponseEntity<?> createOrder(@Valid @RequestBody OrderDto orderDto, BindingResult result, HttpServletRequest request) {

        if (result.hasErrors()) {
            String errors = result.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(", "));
            return ResponseEntity.badRequest().body(errors);
        }
        try {
            return ResponseEntity.ok(orderService.createOrder(orderDto));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
    
}
