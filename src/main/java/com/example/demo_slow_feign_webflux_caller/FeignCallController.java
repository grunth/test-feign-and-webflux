package com.example.demo_slow_feign_webflux_caller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
@Slf4j
public class FeignCallController {

    @Autowired
    private ProductsFeignClient productsFeignClient;

    private static final int DEFAULT_PORT = 8090;

    private int serverPort = DEFAULT_PORT;

    public static final String SLOW_SERVICE_PRODUCTS_ENDPOINT_NAME = "/slow-service-products";

    @GetMapping("/products-blocking")
    public List<Product> getProductsBlocking() {
        log.info("Starting BLOCKING FeignCallController!");
        final URI uri = URI.create(getSlowServiceBaseUri());

        List<Product> result = productsFeignClient.getProductsBlocking(uri);
        result.forEach(product -> log.info(product.toString()));

        log.info("Exiting BLOCKING FeignCallController!");
        return result;
    }

    private String getSlowServiceBaseUri() {
        return "http://localhost:" + serverPort;
    }
}
