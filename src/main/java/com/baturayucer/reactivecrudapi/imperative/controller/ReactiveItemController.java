package com.baturayucer.reactivecrudapi.imperative.controller;

import com.baturayucer.reactivecrudapi.dto.ItemDto;
import com.baturayucer.reactivecrudapi.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.baturayucer.reactivecrudapi.constant.ItemConstants.*;

@RestController
public class ReactiveItemController {

    ItemService itemService;

    @Autowired
    ReactiveItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping(value = IMPERATIVE_CONTROLLER + V1_ITEMS_ALL)
    public Flux<ItemDto> getAllItems() {
        return itemService.getAllItems();
    }

    @GetMapping(value = IMPERATIVE_CONTROLLER + V1_FIND_ONE)
    public Mono<ItemDto> findOne(@RequestParam(value = ID) String id) {
        return itemService.finOne(id);
    }

    @GetMapping(value = IMPERATIVE_CONTROLLER + V1_FIND_BY_DESC)
    public Flux<ItemDto> findByDescription(@RequestParam(value = DESCRIPTION) String description) {
        return itemService.findByDescription(description);
    }
}
