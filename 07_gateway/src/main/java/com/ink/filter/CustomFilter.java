package com.ink.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 自定义网关全局filter
 *
 * @author MT
 * @date 2021/6/18 13:33
 */
//@Configuration
public class CustomFilter implements GlobalFilter, Ordered {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        LOGGER.info("request:{}", request.getPath());
        System.out.println(exchange.getRequest().getQueryParams());
        ServerHttpResponse response = exchange.getResponse();
        LOGGER.info("response:{}", response.getStatusCode());
        System.out.println("经过全局Filter处理.......");
        //放行filter继续向后执行
        Mono<Void> filter = chain.filter(exchange);
        System.out.println("响应回来Filter处理......");
        return filter;
    }

    //order 排序  int数字:用来指定filter执行顺序  默认顺序按照自然数字进行排序  -1 在所有filter执行之前执行
    @Override
    public int getOrder() {
        return 1;
    }
}
