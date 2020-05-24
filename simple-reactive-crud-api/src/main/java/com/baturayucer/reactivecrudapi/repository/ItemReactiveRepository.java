package com.baturayucer.reactivecrudapi.repository;

import com.baturayucer.reactivecrudapi.entity.Item;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;


/**
 * @author baturayucer.
 */
public interface ItemReactiveRepository extends ReactiveMongoRepository<Item, String> {

    Flux<Item> findByDescription(String description);
}