package com.bcp.api.reactive.cambiomoneda.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

import java.util.List;
import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import reactor.core.publisher.Mono;
import reactor.core.publisher.Signal;

import org.slf4j.MDC;
import lombok.extern.slf4j.Slf4j;

import reactor.util.context.Context;

import java.util.UUID;




@Slf4j
@Configuration
public class MonedaConvertidorFilter implements WebFilter {

	  @Override
	  public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
	    ServerHttpRequest request = exchange.getRequest();
	    String requestId = getRequestId(request.getHeaders());
	    return chain
	        .filter(exchange)
	        .doOnEach(logOnEach(r -> log.info("{} {}", request.getMethod(), request.getURI())))
	        .contextWrite(Context.of("CONTEXT_KEY", requestId));
	  }

	  private String getRequestId(HttpHeaders headers) {
	    List<String> requestIdHeaders = headers.get("X-Request-ID");
	    return requestIdHeaders == null || requestIdHeaders.isEmpty()
	        ? UUID.randomUUID().toString()
	        : requestIdHeaders.get(0);
	  }

	  public static <T> Consumer<Signal<T>> logOnEach(Consumer<T> logStatement) {
	    return signal -> {
	      String contextValue = signal.getContextView().get("CONTEXT_KEY");
	      try (MDC.MDCCloseable cMdc = MDC.putCloseable("MDC_KEY", contextValue)) {
	        logStatement.accept(signal.get());
	      }
	    };
	  }

	  public static <T> Consumer<Signal<T>> logOnNext(Consumer<T> logStatement) {
	    return signal -> {
	      if (!signal.isOnNext()) return;
	      String contextValue = signal.getContextView().get("CONTEXT_KEY");
	      try (MDC.MDCCloseable cMdc = MDC.putCloseable("MDC_KEY", contextValue)) {
	        logStatement.accept(signal.get());
	      }
	    };
	  }
	}