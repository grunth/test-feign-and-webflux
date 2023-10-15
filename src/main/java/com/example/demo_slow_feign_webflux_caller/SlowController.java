package com.example.demo_slow_feign_webflux_caller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class SlowController {

    @GetMapping("/slow-service-products")
    public List<Product> getAllProducts() throws InterruptedException {
        Thread.sleep(5000L); // delay
        return Arrays.asList(
                new Product("Fancy Smartphone", "A stylish phone you need"),
                new Product("Cool Watch", "The only device you need"),
                new Product("Smart TV", "Cristal clean images")
        );
    }

}
