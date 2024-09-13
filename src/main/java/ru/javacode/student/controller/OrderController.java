package ru.javacode.student.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.javacode.student.model.Order;
import ru.javacode.student.model.OrderDtoNew;
import ru.javacode.student.service.OrderService;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public Order addOrder(@RequestBody OrderDtoNew orderDto) {
        return orderService.addOrder(orderDto);
    }

}
