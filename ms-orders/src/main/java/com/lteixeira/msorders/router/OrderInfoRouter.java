package com.lteixeira.msorders.router;

import com.lteixeira.msorders.handler.OrderInfoHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RequestPredicates.path;

@Component
public class OrderInfoRouter {

    @Bean
    public RouterFunction<ServerResponse> route(OrderInfoHandler orderInfoHandler) {
        return RouterFunctions.nest(path("/ms-orders/v1"),
                RouterFunctions.route(GET("/orders").and(accept(APPLICATION_JSON)), orderInfoHandler::findAll));
    }
}
