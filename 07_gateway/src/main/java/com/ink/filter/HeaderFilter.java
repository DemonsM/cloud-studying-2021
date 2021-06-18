package com.ink.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Objects;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

/**
 * @author MT
 * @date 2021/6/18 13:43
 */
@Configuration
public class HeaderFilter implements GlobalFilter, Ordered {
    private static final Logger LOGGER = LoggerFactory.getLogger(HeaderFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        LOGGER.info("进入HeaderFilter");
        List<String> username = exchange.getRequest().getHeaders().get("username");
        if (Objects.nonNull(username)) {
            LOGGER.info("请求合法，放行username:[{}]", username);
            return chain.filter(exchange);
        }
        LOGGER.info("请求非法");
        exchange.getResponse().setStatusCode(INTERNAL_SERVER_ERROR);
        return exchange.getResponse().setComplete();
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
