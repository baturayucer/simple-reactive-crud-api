package com.baturayucer.reactivecrudapi.service;

import com.baturayucer.reactivecrudapi.dto.ItemDto;
import com.baturayucer.reactivecrudapi.entity.Item;
import com.baturayucer.reactivecrudapi.mapper.ItemMapper;
import com.baturayucer.reactivecrudapi.repository.ItemReactiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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

    public Mono<ItemDto> finOne(String id) {
        Mono<Item> itemEntity = itemRepository.findById(id);
        return itemEntity.map(item -> itemMapper.toItemDto(item));
    }

    public Flux<ItemDto> findByDescription(String description) {
        Flux<Item> allItems = itemRepository.findByDescription(description);
        return allItems.map(item -> itemMapper.toItemDto(item));
    }
}
