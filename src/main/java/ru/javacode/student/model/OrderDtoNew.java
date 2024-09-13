package ru.javacode.student.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class OrderDtoNew {

    private Long customerId;

    private Map<Long, Integer> itemIdToAmount;

}
