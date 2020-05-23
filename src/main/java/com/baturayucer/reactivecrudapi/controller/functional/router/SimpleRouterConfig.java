package com.baturayucer.reactivecrudapi.controller.functional.router;

import com.baturayucer.reactivecrudapi.controller.functional.handler.SimpleHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static com.baturayucer.reactivecrudapi.constant.ItemConstants.*;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;


/**
 * @author baturayucer.
 */
@Configuration
public class SimpleRouterConfig {
    @Bean
    public RouterFunction<ServerResponse> route(SimpleHandler handlerFunction) {
        return RouterFunctions
                .route(GET(FUNCTIONAL_ROUTER_V1 + ITEMS_ALL)
                        .and(accept(MediaType.APPLICATION_JSON)), handlerFunction::getAllItems)
                .andRoute(GET(FUNCTIONAL_ROUTER_V1 + FIND_ONE)
                        .and(accept(MediaType.APPLICATION_JSON)), handlerFunction::findOne)
                .andRoute(GET(FUNCTIONAL_ROUTER_V1 + FIND_BY_DESC)
                        .and(accept(MediaType.APPLICATION_JSON)), handlerFunction::findByDescription)
                .andRoute(GET(FUNCTIONAL_ROUTER_V1 + CREATE_ITEM)
                        .and(accept(MediaType.APPLICATION_JSON)), handlerFunction::createItem);

    }
}