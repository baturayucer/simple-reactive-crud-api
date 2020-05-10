package com.baturayucer.reactivecrudapi.service;

import com.baturayucer.reactivecrudapi.dto.ItemDto;
import com.baturayucer.reactivecrudapi.entity.Item;
import com.baturayucer.reactivecrudapi.mapper.ItemMapper;
import com.baturayucer.reactivecrudapi.repository.ItemReactiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class ItemService {

    ItemReactiveRepository itemRepository;
    ItemMapper itemMapper = ItemMapper.INSTANCE;

    @Autowired
    ItemService(ItemReactiveRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Flux<ItemDto> getAllItems() {
        Flux<Item> allItems = itemRepository.findAll();
        return allItems.map(item -> itemMapper.toItemDto(item));
    }
}
