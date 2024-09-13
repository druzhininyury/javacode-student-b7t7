package ru.javacode.student.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javacode.student.model.Item;
import ru.javacode.student.repository.ItemRepository;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    @Override
    @Transactional
    public Item addItem(Item item) {
        Item dbItem = itemRepository.save(item);
        return dbItem;
    }
}
