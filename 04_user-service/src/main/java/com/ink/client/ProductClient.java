package com.ink.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author MT
 * @date 2021/6/9 10:44
 */
@FeignClient(name = "PRODUCT-SERVICE")
@RequestMapping("/product")
public interface ProductClient {
    @GetMapping("/test1")
    String product1(@RequestParam String username);
}
