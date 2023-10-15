package com.example.demo_slow_feign_webflux_caller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@RestController
@Slf4j
public class NonBlockingController {

    @Autowired
    private ProductsFeignClient productsFeignClient;

    private static final int DEFAULT_PORT = 8090;

    private int serverPort = DEFAULT_PORT;

    public static final String SLOW_SERVICE_PRODUCTS_ENDPOINT_NAME = "/slow-service-products";


    @GetMapping(value = "/products-non-blocking", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Product> getProductsNonBlocking() {
        log.info("Starting NON-BLOCKING Controller!");

        Flux<Product> productFlux = WebClient.create()
                .get()
                .uri(getSlowServiceBaseUri() + SLOW_SERVICE_PRODUCTS_ENDPOINT_NAME)
                .retrieve()
                .bodyToFlux(Product.class);

        productFlux.subscribe(product -> log.info(product.toString()));

        log.info("Exiting NON-BLOCKING Controller!");
        return productFlux;
    }

    private String getSlowServiceBaseUri() {
        return "http://localhost:" + serverPort;
    }
}
