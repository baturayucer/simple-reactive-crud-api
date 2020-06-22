package com.baturayucer.reactivecrudapi.init;

import com.baturayucer.reactivecrudapi.entity.Item;
import com.baturayucer.reactivecrudapi.repository.ItemReactiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author baturayucer.
 */
//do not run ItemDataInitializer if the active profile is test
@Component
@Profile("!test")
public class ItemDataInitializer {

    private ItemReactiveRepository itemReactiveRepository;
    private static final Logger logger =
            Logger.getLogger(ItemDataInitializer.class.getName());

    @Autowired
    public ItemDataInitializer(ItemReactiveRepository itemReactiveRepository) {
        this.itemReactiveRepository = itemReactiveRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void init(String... args) {
        dataSetup();
    }

    public List<Item> generateItems() {
        return Arrays.asList(new Item(null, "Apple TV", 400.0),
            new Item(null, "Samsung Watch", 99.0),
            new Item("BEATS1", "Beats HeadPhones", 110.0));
    }

    private void dataSetup() {
        itemReactiveRepository.deleteAll()
            .thenMany(Flux.fromIterable(generateItems()))
            .flatMap(itemReactiveRepository::save)
            .thenMany(itemReactiveRepository.findAll())
            .subscribe(item -> logger.info("Item inserted: " + item.getDescription()));

    }
}