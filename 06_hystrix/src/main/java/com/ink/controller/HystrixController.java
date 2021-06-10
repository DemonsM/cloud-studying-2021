package com.ink.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author MT
 * @date 2021/6/10 10:12
 */
@Slf4j
@RestController
public class HystrixController {
    //服务熔断
    @GetMapping("/product/break")
    @HystrixCommand(fallbackMethod = "testBreakFall")
    public String testBreak(int id) {
        log.info("接收的商品id为: " + id);
        if (id <= 0) {
            throw new RuntimeException("数据不合法!!!");
        }
        return "当前接收商品id: " + id;
    }

    public String testBreakFall(int id) {
        log.info("请求参数:[{}]", id);
        return "熔断";
    }
}
