package com.baturayucer.reactiveclient.controller;

import com.baturayucer.reactiveclient.dto.ItemDto;
import org.springframework.http.HttpHeaders;
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

    WebClient webClient = WebClient
            .builder().baseUrl(API_URL)
            .defaultHeader(HttpHeaders.CONTENT_TYPE,
                    MediaType.APPLICATION_JSON_VALUE)
            .defaultHeader(HttpHeaders.ACCEPT,
                    MediaType.APPLICATION_JSON_VALUE).build();

    public ResponseEntity<Flux<ItemDto>> getAllItems() {
        Flux<ItemDto> itemDtoFlux = webClient.get()
                .uri(LEGACY_CONTROLLER_V1 + ITEMS_ALL)
                .retrieve()
                .bodyToFlux(ItemDto.class).log();
        return ResponseEntity.ok(itemDtoFlux);
    }

    public ResponseEntity<Mono<ItemDto>> findOne(@RequestParam(value = ID) String id) {
        Mono<ItemDto> itemDtoMono = webClient.get()
                .uri(LEGACY_CONTROLLER_V1 + FIND_ONE)
                .header(ID, id)
                .exchange()
                .flatMap(response ->
                    response.bodyToMono(ItemDto.class)).log();
        return ResponseEntity.ok(itemDtoMono);
    }

    public ResponseEntity<Flux<ItemDto>> findByDescription(@RequestParam(value = DESCRIPTION) String description) {
        Flux<ItemDto> itemDtoFlux = webClient.get()
                .uri(LEGACY_CONTROLLER_V1 + FIND_BY_DESC)
                .header(DESCRIPTION, description)
                .retrieve()
                .bodyToFlux(ItemDto.class).log();
        return ResponseEntity.ok(itemDtoFlux);
    }

    public ResponseEntity<Mono<ItemDto>> createItem(@RequestBody ItemDto itemRequest) {
        Mono<ItemDto> itemDtoFlux = webClient.post()
                .uri(LEGACY_CONTROLLER_V1 + CREATE_ITEM)
                .body(BodyInserters.fromPublisher(Mono
                        .just(itemRequest), ItemDto.class))
                .retrieve()
                .bodyToMono(ItemDto.class).log();
        return ResponseEntity.ok(itemDtoFlux);
    }
}