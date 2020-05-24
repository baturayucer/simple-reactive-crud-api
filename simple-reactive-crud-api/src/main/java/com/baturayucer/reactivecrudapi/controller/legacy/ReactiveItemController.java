package com.baturayucer.reactivecrudapi.controller.legacy;

import com.baturayucer.reactivecrudapi.dto.ItemDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.baturayucer.reactivecrudapi.constant.ItemConstants.*;
import static com.baturayucer.reactivecrudapi.constant.ItemConstants.ID;

/**
 * @author baturayucer.
 */
@RequestMapping(value = LEGACY_CONTROLLER_V1)
public interface ReactiveItemController {

    @GetMapping(value = ITEMS_ALL)
    ResponseEntity<Flux<ItemDto>> getAllItems();

    @GetMapping(value = FIND_ONE)
    ResponseEntity<Mono<ItemDto>> findOne(@RequestParam(value = ID) String id);

    @GetMapping(value = FIND_BY_DESC)
    ResponseEntity<Flux<ItemDto>> findByDescription(@RequestParam(value = DESCRIPTION) String description);

    @PostMapping(value = CREATE_ITEM)
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<Mono<ItemDto>> createItem(@RequestBody ItemDto itemRequest);
}