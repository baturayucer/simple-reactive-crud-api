package com.baturayucer.reactivecrudapi.controller.functional.handler;

import com.baturayucer.reactivecrudapi.dto.ItemDto;
import com.baturayucer.reactivecrudapi.entity.Item;
import com.baturayucer.reactivecrudapi.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

import static com.baturayucer.reactivecrudapi.constant.ItemConstants.DESCRIPTION;
import static com.baturayucer.reactivecrudapi.constant.ItemConstants.ID;

/**
 * @author baturayucer.
 */
@Component
public class SimpleHandler {

    private ItemService itemService;

    @Autowired
    SimpleHandler(ItemService itemService) {
        this.itemService = itemService;
    }

    public Mono<ServerResponse> getAllItems(ServerRequest serverRequest) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(itemService.getAllItems(), ItemDto.class);
    }

    public Mono<ServerResponse> findOne(ServerRequest serverRequest) {
        Mono<ItemDto> item = serverRequest.queryParam(ID)
                .map(id -> itemService.finOne(id))
                .orElseThrow(RuntimeException::new);
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(item, ItemDto.class);
    }

    public Mono<ServerResponse> findByDescription(ServerRequest serverRequest) {
        Flux<ItemDto> items = serverRequest.queryParam(DESCRIPTION)
                .map(desc -> itemService.findByDescription(desc))
                .orElseThrow(RuntimeException::new);
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(items, ItemDto.class);
    }

    public Mono<ServerResponse> createItem(ServerRequest serverRequest) {
        Mono<ItemDto> itemDtoMono = serverRequest.bodyToMono(ItemDto.class)
                .flatMap(item -> itemService.createItem(item));
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(itemDtoMono, ItemDto.class);
    }
}
