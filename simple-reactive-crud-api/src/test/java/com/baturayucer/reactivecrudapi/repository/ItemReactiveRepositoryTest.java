package com.baturayucer.reactivecrudapi.repository;

import com.baturayucer.reactivecrudapi.entity.Item;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

//there is no need to set active profile here because we have @DataMongoTest
@DataMongoTest
@RunWith(SpringRunner.class)
public class ItemReactiveRepositoryTest {

    @Autowired
    ItemReactiveRepository repository;

    Logger logger = Logger.getLogger(ItemReactiveRepositoryTest.class.getName());

    List<Item> itemList = Arrays.asList(new Item(null, "Apple TV", 400.0),
            new Item(null, "Samsung Watch", 99.0),
            new Item("BEATS1", "Beats HeadPhones", 110.0));

    @Before
    public void setup() {
        repository.deleteAll()
                .thenMany(Flux.fromIterable(itemList))
                .flatMap(repository::save)
                .doOnNext( item -> {
                    logger.info("Item inserted: " + item);
                }).blockLast(); //blockLast is for waiting data to be inserted
    }

    @Test
    public void getAllItemsTest() {
        StepVerifier.create(repository.findAll())
                .expectSubscription()
                .expectNextCount(3)
                .verifyComplete();
    }

    @Test
    public void getItemByIdTest() {
        StepVerifier.create(repository.findById("BEATS1"))
                .expectSubscription()
                .expectNextMatches(item ->
                        item.getPrice().equals(Double.valueOf(110.0)))
                .verifyComplete();
    }

    @Test
    public void getItemByDescriptionTest() {
        StepVerifier.create(repository.findByDescription("Beats HeadPhones"))
                .expectSubscription()
                .expectNextCount(1)
                .verifyComplete();
    }

    @Test
    public void saveTest() {
        Item item = new Item(null, "Xiaomi Mi Band", 30.00);
        Mono<Item> savedItem = repository.save(item);
        StepVerifier.create(savedItem)
                .expectSubscription()
                .expectNextMatches(i -> i.getId() != null
                        && i.getDescription().contains("Xiaomi"))
                .verifyComplete();
    }

    @Test
    public void updateTest() {
        Double newPrice = 34.00;
        Flux<Item> apple_tv = repository.findByDescription("Apple TV")
                .map(item -> {
                    item.setPrice(newPrice);
                    return item;
                }).flatMap(item -> {
                    return repository.save(item);
                });
        StepVerifier.create(apple_tv)
                .expectSubscription()
                .expectNextMatches(item -> item.getPrice().equals(newPrice))
                .verifyComplete();
    }

    @Test
    public void deleteTest() {

        Flux<Void> apple_tv = repository.findByDescription("Apple TV")
                .flatMap(item -> repository.delete(item));

        Mono<Void> beats1 = repository.findById("BEATS1")
                .map(Item::getId)
                .flatMap(id -> {
                    return repository.deleteById(id);
                });
        StepVerifier.create(beats1)
                .expectSubscription()
                .verifyComplete();
        StepVerifier.create(apple_tv)
                .expectSubscription()
                .verifyComplete();
    }
}
