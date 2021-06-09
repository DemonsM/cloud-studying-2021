package com.ink.controller;

import com.ink.client.ProductClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author MT
 * @date 2021/6/9 10:20
 */
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@RequestMapping("/user")
@RestController
public class UserController {
    private final ProductClient productClient;

    @Value("${server.port}")
    private int port;

    @GetMapping("/test1")
    public String user1(@RequestParam String username) {
        log.info("用户为:{}", username);
        return "hello user";
    }

    @GetMapping("/test2")
    public String user2(@RequestParam String username) {
        log.info("用户为:[{}], port:[{}]", username, port);
        String msg = productClient.product1(username);
        log.info(msg);
        return "hello user";
    }
}
