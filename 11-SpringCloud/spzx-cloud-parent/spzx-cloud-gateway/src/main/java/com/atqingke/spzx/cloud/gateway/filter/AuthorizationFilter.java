package com.atqingke.spzx.cloud.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class AuthorizationFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        // 1、获取请求参数
        MultiValueMap<String, String> params = exchange.getRequest().getQueryParams();

        // 2、判断是否包含authorization
        String authorization = params.getFirst("authorization");
        if(authorization == null || authorization.isEmpty()) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED) ;            // 设置一个响应状态码401
            return exchange.getResponse().setComplete() ;                                 // 结束请求
        }

        // 3、校验请求参数的值是否为admin，如果是放行，如果不是拦截
        if(!"admin".equals(authorization)) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED) ;            // 设置一个响应状态码401
            return exchange.getResponse().setComplete() ;                                 // 结束请求
        }

        // 放行
        return chain.filter(exchange);
    }
    
    @Override
    public int getOrder() {
        return -1;
    }

}