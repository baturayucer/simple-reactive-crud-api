package com.baturayucer.reactivecrudapi.imperative.controller;

import com.baturayucer.reactivecrudapi.dto.ItemDto;
import com.baturayucer.reactivecrudapi.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.baturayucer.reactivecrudapi.constant.ItemConstants.DESCRIPTION;
import static com.baturayucer.reactivecrudapi.constant.ItemConstants.ID;


/**
 * @author baturayucer.
 */
@RestController
public class ReactiveItemControllerImpl implements ReactiveItemController {

    private ItemService itemService;

    @Autowired
    ReactiveItemControllerImpl(ItemService itemService) {
        this.itemService = itemService;
    }

    public Flux<ItemDto> getAllItems() {
        return itemService.getAllItems();
    }

    public Mono<ItemDto> findOne(@RequestParam(value = ID) String id) {
        return itemService.finOne(id);
    }

    public Flux<ItemDto> findByDescription(@RequestParam(value = DESCRIPTION) String description) {
        return itemService.findByDescription(description);
    }

    public Mono<ItemDto> createItem(@RequestBody ItemDto itemRequest) {
        return itemService.createItem(itemRequest);
    }
}