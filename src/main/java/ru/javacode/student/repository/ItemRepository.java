package ru.javacode.student.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.javacode.student.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {



}
