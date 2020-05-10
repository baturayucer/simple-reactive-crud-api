package com.baturayucer.reactivecrudapi.imperative.controller;

import com.baturayucer.reactivecrudapi.dto.ItemDto;
import com.baturayucer.reactivecrudapi.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import static com.baturayucer.reactivecrudapi.constant.ItemConstants.IMPERATIVE_CONTROLLER;
import static com.baturayucer.reactivecrudapi.constant.ItemConstants.ITEM_ENDPOINT_V1;

@RestController
public class ReactiveItemController {

    ItemService itemService;

    @Autowired
    ReactiveItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping(value = IMPERATIVE_CONTROLLER+ITEM_ENDPOINT_V1)
    public Flux<ItemDto> getAllItems() {
        return itemService.getAllItems();
    }
}
