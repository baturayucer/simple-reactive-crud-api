package com.baturayucer.reactivecrudapi.service;

import com.baturayucer.reactivecrudapi.dto.ItemDto;
import com.baturayucer.reactivecrudapi.entity.Item;
import com.baturayucer.reactivecrudapi.mapper.ItemMapper;
import com.baturayucer.reactivecrudapi.repository.ItemReactiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.logging.Logger;

/**
 * @author baturayucer.
 */
@Service
public class ItemServiceImpl implements ItemService {

    private ItemReactiveRepository itemRepository;
    private ItemMapper itemMapper = ItemMapper.INSTANCE;

    private Logger serviceLogger =
            Logger.getLogger(ItemService.class.getName());

    @Autowired
    public ItemServiceImpl(ItemReactiveRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Flux<ItemDto> getAllItems() {
        Flux<Item> allItems = itemRepository.findAll();
        serviceLogger.info("Fetched All Items.");
        return allItems.map(item -> itemMapper.toItemDto(item));
    }

    public Mono<ItemDto> finOne(String id) {
        Mono<Item> itemEntity = itemRepository.findById(id);
        serviceLogger.info("Fetched Item by Id.");
        return itemEntity.map(item -> itemMapper.toItemDto(item));
    }

    public Flux<ItemDto> findByDescription(String description) {
        Flux<Item> allItems = itemRepository.findByDescription(description);
        serviceLogger.info("Fetched Items by Description.");
        return allItems.map(item -> itemMapper.toItemDto(item));
    }

    public Mono<ItemDto> createItem(ItemDto itemRequest) {
        Mono<Item> insertedItem =
                itemRepository.save(itemMapper.toItemEntity(itemRequest));
        serviceLogger.info("Item created: " + itemRequest.getDescription());
        return insertedItem.map(item -> itemMapper.toItemDto(item));
    }
}