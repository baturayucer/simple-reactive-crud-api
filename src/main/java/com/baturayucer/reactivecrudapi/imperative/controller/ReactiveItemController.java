package com.baturayucer.reactivecrudapi.imperative.controller;

import com.baturayucer.reactivecrudapi.dto.ItemDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.baturayucer.reactivecrudapi.constant.ItemConstants.*;
import static com.baturayucer.reactivecrudapi.constant.ItemConstants.ID;

/**
 * @author baturayucer.
 */
@RequestMapping(value = IMPERATIVE_CONTROLLER_V1)
public interface ReactiveItemController {

    @GetMapping(value = V1_ITEMS_ALL)
    Flux<ItemDto> getAllItems();

    @GetMapping(value = V1_FIND_ONE)
    Mono<ItemDto> findOne(@RequestParam(value = ID) String id);

    @GetMapping(value = V1_FIND_BY_DESC)
    Flux<ItemDto> findByDescription(@RequestParam(value = DESCRIPTION) String description);

    @PostMapping(value = V1_CREATE_ITEM)
    @ResponseStatus(HttpStatus.CREATED)
    Mono<ItemDto> createItem(@RequestBody ItemDto itemRequest);
}