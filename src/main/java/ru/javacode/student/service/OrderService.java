package ru.javacode.student.service;

import ru.javacode.student.model.Order;
import ru.javacode.student.model.OrderDtoNew;

public interface OrderService {

    Order addOrder(OrderDtoNew orderDto);

}
