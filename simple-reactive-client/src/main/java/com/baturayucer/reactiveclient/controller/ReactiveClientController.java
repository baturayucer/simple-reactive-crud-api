package com.baturayucer.reactiveclient.controller;

import com.baturayucer.reactiveclient.dto.ItemDto;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import static com.baturayucer.reactiveclient.constant.ReactiveClientConstants.*;

@RestController
public class ReactiveClientController {

    WebClient webClient = WebClient.create(API_URL);

    @GetMapping(value = LEGACY_CONTROLLER_V1 + ITEMS_ALL)
    public ResponseEntity<Flux<ItemDto>> getAllItems() {
        Flux<ItemDto> itemDtoFlux = webClient.get()
                .uri(LEGACY_CONTROLLER_V1 + ITEMS_ALL)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(ItemDto.class).log();
        return ResponseEntity.ok(itemDtoFlux);
    }
}
