package com.baturayucer.reactivecrudapi.functional.handler;

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

import static com.baturayucer.reactivecrudapi.constant.ItemConstants.DESCRIPTION;
import static com.baturayucer.reactivecrudapi.constant.ItemConstants.ID;

/**
 * @author baturayucer.
 */
@Component
public class SimpleHandler {

    private ItemService itemServiceImpl;

    @Autowired
    SimpleHandler(ItemService itemServiceImpl) {
        this.itemServiceImpl = itemServiceImpl;
    }

    public Mono<ServerResponse> getAllItems(ServerRequest serverRequest) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(itemServiceImpl.getAllItems(), ItemDto.class);
    }

    public Mono<ServerResponse> findOne(ServerRequest serverRequest) {
            Mono<ItemDto> item = serverRequest.queryParam(ID)
                    .map(id -> itemServiceImpl.finOne(id))
                    .orElseThrow(RuntimeException::new);
            return ServerResponse.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(item, ItemDto.class);
    }

    public Mono<ServerResponse> findByDescription(ServerRequest serverRequest) {
        Flux<ItemDto> items = serverRequest.queryParam(DESCRIPTION)
                .map(desc -> itemServiceImpl.findByDescription(desc))
                .orElseThrow(RuntimeException::new);
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(items, ItemDto.class);
    }

//    public Mono<ServerResponse> createItem(ServerRequest serverRequest) {
//        serverRequest.bodyToMono(ItemDto.class)
//                .map(itemDto -> itemServiceImpl.createItem(itemDto));
//    }
}
