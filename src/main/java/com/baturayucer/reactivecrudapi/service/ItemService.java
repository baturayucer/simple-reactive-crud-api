package com.baturayucer.reactivecrudapi.service;

import com.baturayucer.reactivecrudapi.dto.ItemDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author baturayucer.
 */
public interface ItemService {

    Flux<ItemDto> getAllItems();
    Mono<ItemDto> finOne(String id);
    Flux<ItemDto> findByDescription(String description);
    Mono<ItemDto> createItem(ItemDto itemRequest);
}
