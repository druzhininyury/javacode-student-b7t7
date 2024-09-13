package ru.javacode.student.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "orders_items")
public class OrderItem {

    @Id
    @ManyToOne
    @JoinColumn(name = "order_id")
    @JsonIgnore
    private Order order;

    @Id
    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @Column(name = "amount")
    private Integer amount;

}
