package com.baturayucer.reactiveclient.controller;

import com.baturayucer.reactiveclient.dto.ItemDto;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.baturayucer.reactiveclient.constant.ReactiveClientConstants.*;

/**
 * @author baturayucer.
 */
@RestController
public class ReactiveClientControllerImpl implements ReactiveClientController{

    WebClient webClient = WebClient.create(API_URL);

    public ResponseEntity<Flux<ItemDto>> getAllItems() {
        Flux<ItemDto> itemDtoFlux = webClient.get()
                .uri(LEGACY_CONTROLLER_V1 + ITEMS_ALL)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(ItemDto.class).log();
        return ResponseEntity.ok(itemDtoFlux);
    }

    public ResponseEntity<Mono<ItemDto>> findOne(@RequestParam(value = ID) String id) {
        Mono<ItemDto> itemDtoMono = webClient.get()
                .uri(LEGACY_CONTROLLER_V1 + FIND_ONE)
                .accept(MediaType.APPLICATION_JSON)
                .header(ID, id)
                .retrieve()
                .bodyToMono(ItemDto.class).log();
        return ResponseEntity.ok(itemDtoMono);
    }

    public ResponseEntity<Flux<ItemDto>> findByDescription(
            @RequestParam(value = DESCRIPTION) String description) {
        Flux<ItemDto> itemDtoFlux = webClient.get()
                .uri(LEGACY_CONTROLLER_V1 + FIND_BY_DESC)
                .accept(MediaType.APPLICATION_JSON)
                .header(DESCRIPTION, description)
                .retrieve()
                .bodyToFlux(ItemDto.class).log();
        return ResponseEntity.ok(itemDtoFlux);
    }

    public ResponseEntity<Mono<ItemDto>> createItem(@RequestBody ItemDto itemRequest) {
        Mono<ItemDto> itemDtoFlux = webClient.post()
                .uri(LEGACY_CONTROLLER_V1 + CREATE_ITEM)
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromPublisher(
                        Mono.just(itemRequest), ItemDto.class))
                .retrieve()
                .bodyToMono(ItemDto.class).log();
        return ResponseEntity.ok(itemDtoFlux);
    }
}