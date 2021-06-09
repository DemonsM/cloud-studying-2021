package com.ink.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author MT
 * @date 2021/6/9 10:33
 */
@Slf4j
@RequestMapping("/product")
@RestController
public class ProductController {
    @Value("${server.port}")
    private int port;

    @GetMapping("/test1")
    public String product1(@RequestParam String username) {
        log.info("[{}]用户进入商品服务, port:[{}]", username, port);
        return "hello,product[" + port + "]";
    }
}
