package com.example.demo_slow_feign_webflux_caller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.net.URI;
import java.util.List;

@FeignClient(value = "productsBlocking", url = "http://localhost:8090")
public interface ProductsFeignClient {

    @RequestMapping(method = RequestMethod.GET, value = "/slow-service-products", produces = "application/json")
    List<Product> getProductsBlocking(URI baseUrl);
}
