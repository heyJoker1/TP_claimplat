package com.claimplat.gateway;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.ContextStoppedEvent;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.claimplat.gateway.filter.MonitoringGatewayFilter;

@EnableDiscoveryClient
@SpringBootApplication
public class GatewayApplication implements ApplicationListener<ApplicationEvent>{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(GatewayApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}
	
	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		if(event instanceof ApplicationEnvironmentPreparedEvent) {
			LOGGER.info("初始化环境变量");
		}else if(event instanceof ApplicationPreparedEvent) {
			LOGGER.info("初始化完成");
		}else if(event instanceof ContextRefreshedEvent) {
			LOGGER.info("应用刷新");
		}else if(event instanceof ApplicationReadyEvent) {
			LOGGER.info("应用已启动完成");
		}else if(event instanceof ContextStartedEvent) {
			LOGGER.info("应用启动");
		}else if(event instanceof ContextStoppedEvent) {
			LOGGER.info("应用停止");
		}else if(event instanceof ContextClosedEvent) {
			LOGGER.info("应用关闭");
		}else {
			//LOGGER.info("其他事件"+event.toString());
		}		
	}
	
	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(r -> r.path("/serviceApi/**").filters(f -> f.filter(new MonitoringGatewayFilter()))
						.uri("lb://claimplat-service-API"))
				.route(r -> r.path("/offline/**").filters(f -> f.filter(new MonitoringGatewayFilter()))
						.uri("lb://claimplat-offline"))
				
				.route(r -> r.order(11000).path("/baidu")
						.filters(f -> f.addRequestHeader("x-request-uuid", UUID.randomUUID().toString())
								.filter(new MonitoringGatewayFilter()))
						.uri("https://www.baidu.com/"))
				.build();
	}

	@Bean
	public RouterFunction<ServerResponse> defaultRouterFunction(){
		RouterFunction<ServerResponse> route = RouterFunctions.route(RequestPredicates.path("ack"),
				request -> ServerResponse.ok().body(BodyInserters.fromObject("ack ok")));
		return route;
	}
}
