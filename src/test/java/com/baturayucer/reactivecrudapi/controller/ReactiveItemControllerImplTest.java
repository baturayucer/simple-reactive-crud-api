package com.baturayucer.reactivecrudapi.controller;

import com.baturayucer.reactivecrudapi.constant.ItemConstants;
import com.baturayucer.reactivecrudapi.dto.ItemDto;
import com.baturayucer.reactivecrudapi.entity.Item;
import com.baturayucer.reactivecrudapi.repository.ItemReactiveRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.baturayucer.reactivecrudapi.constant.ItemConstants.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@DirtiesContext
@AutoConfigureWebTestClient
@ActiveProfiles("test")
public class ReactiveItemControllerImplTest {

    @Autowired
    private WebTestClient webTestClient;
    @Autowired
    private ItemReactiveRepository itemReactiveRepository;

    @Before
    public void setup() {
        Flux<Item> itemFlux = Flux.fromIterable(
                Arrays.asList(new Item(null, "Apple TV", 400.0),
                new Item(null, "Samsung Watch", 99.0),
                new Item("BEATS1", "Beats HeadPhones", 110.0)));
        itemReactiveRepository.deleteAll()
                .thenMany(itemFlux)
                .flatMap(itemReactiveRepository::save)
                .blockLast();
    }

    @Test
    public void getAllItemsTest() {
        webTestClient.get().uri(IMPERATIVE_CONTROLLER_V1 + V1_ITEMS_ALL)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_VALUE)
                .expectBodyList(Item.class)
                .hasSize(3);

        webTestClient.get().uri(IMPERATIVE_CONTROLLER_V1 + V1_ITEMS_ALL)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_VALUE)
                .expectBodyList(Item.class)
                .consumeWith(response -> {
                    List<Item> items = response.getResponseBody();
                    Optional.ofNullable(items).ifPresent(itemList ->
                            itemList.forEach(item -> Assert.assertNotNull(item.getId())));
                });
    }

    @Test
    public void createItemTest() {

        Item item = new Item(null, "Iphone 11", 799.99);
        webTestClient.post().uri(IMPERATIVE_CONTROLLER_V1 + V1_CREATE_ITEM)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(item), ItemDto.class)
                .exchange()
                .expectStatus().isCreated()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_VALUE)
                .expectBody(ItemDto.class)
                .consumeWith(response -> {
                    Optional.ofNullable(response.getResponseBody())
                            .ifPresent(itemDto -> Assert.assertNotNull(itemDto.getId()));
                });
    }
}
