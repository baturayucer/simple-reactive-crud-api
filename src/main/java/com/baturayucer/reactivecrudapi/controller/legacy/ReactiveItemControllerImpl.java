package com.baturayucer.reactivecrudapi.controller.legacy;

import com.baturayucer.reactivecrudapi.dto.ItemDto;
import com.baturayucer.reactivecrudapi.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    public ResponseEntity<Flux<ItemDto>> getAllItems() {
        return ResponseEntity.ok(itemService.getAllItems());
    }

    public ResponseEntity<Mono<ItemDto>> findOne(@RequestParam(value = ID) String id) {
        return ResponseEntity.ok(itemService.finOne(id));
    }

    public ResponseEntity<Flux<ItemDto>> findByDescription(@RequestParam(value = DESCRIPTION) String description) {
        return ResponseEntity.ok(itemService.findByDescription(description));
    }

    public ResponseEntity<Mono<ItemDto>> createItem(@RequestBody ItemDto itemRequest) {
        return ResponseEntity.ok(itemService.createItem(itemRequest));
    }
}