package ru.javacode.student.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javacode.student.model.*;
import ru.javacode.student.repository.ItemRepository;
import ru.javacode.student.repository.OrderRepository;
import ru.javacode.student.repository.UserRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final UserRepository userRepository;
    private final ItemRepository itemRepository;

    @Override
    @Transactional
    public Order addOrder(OrderDtoNew orderDto) {
        Optional<User> customerOptional = userRepository.findById(orderDto.getCustomerId());
        if (customerOptional.isEmpty()) {
            throw new EntityNotFoundException("User with id=" + orderDto.getCustomerId() + " doesn't exist.");
        }
        User customer = customerOptional.get();

        List<Item> items = itemRepository.findAllById(orderDto.getItemIdToAmount().keySet());
        if (items.size() < orderDto.getItemIdToAmount().size()) {
            throw new EntityNotFoundException("Not all items found by ids.");
        }

        Order order = new Order();
        order.setCustomer(customer);
        order.setOrderItems(new HashSet<>());

        Set<OrderItem> orderItemSet = order.getOrderItems();
        for (Item item : items) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setItem(item);
            orderItem.setAmount(orderDto.getItemIdToAmount().get(item.getId()));
            orderItemSet.add(orderItem);
        }

        Order dbOrder = orderRepository.save(order);

        return dbOrder;
    }
}
