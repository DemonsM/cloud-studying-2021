package com.ink.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Objects;

/**
 * @author MT
 * @date 2021/6/18 14:17
 */
@Configuration
public class RequestTimeFilter implements GlobalFilter, Ordered {
    private static final Logger LOGGER = LoggerFactory.getLogger(RequestTimeFilter.class);
    private static final String REQUEST_TIME_BEGIN = "request-time-begin";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        exchange.getAttributes().put(REQUEST_TIME_BEGIN, System.currentTimeMillis());
        return chain.filter(exchange).then(
                Mono.fromRunnable(() -> {
                    long startTime = Objects.requireNonNull(exchange.getAttribute(REQUEST_TIME_BEGIN));
                    long usedTime = System.currentTimeMillis() - startTime;
                    LOGGER.info("requestId:[{}],[{}]请求用时:[{}ms]", exchange.getRequest().getId(), exchange.getRequest().getURI().getRawPath(), usedTime);
                })
        );
    }

    @Override
    public int getOrder() {
        return -1;
    }
}
