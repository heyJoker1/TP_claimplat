package com.claimplat.gateway.exception;

import java.util.List;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.codec.HttpMessageWriter;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.reactive.result.view.ViewResolver;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebExceptionHandler;

import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.GatewayCallbackManager;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.fastjson.JSON;
import com.claimplat.common.constant.RestResult;
import com.claimplat.common.constant.ReturnCode;
import com.google.gson.Gson;

import reactor.core.publisher.Mono;

public class JsonSentinelGatewayBlockExceptionHandler implements WebExceptionHandler{

	private final List<ViewResolver> viewResolvers;
	private final List<HttpMessageWriter<?>> messageWriters;
	
	public JsonSentinelGatewayBlockExceptionHandler(List<ViewResolver> viewResolvers,
			ServerCodecConfigurer serverCodecConfigurer) {
		this.viewResolvers = viewResolvers;
		this.messageWriters = serverCodecConfigurer.getWriters();
	}

	@Override
	public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
		if (exchange.getResponse().isCommitted()) {
			return Mono.error(ex);
		}
		if (!BlockException.isBlockException(ex)) {
			return Mono.error(ex);
		}
		return handleBlockedRequest(exchange,ex).flatMap(response -> writeResponse(response,exchange));
	}

	/*重写writeResponse（），自定义返回的异常数据*/
	private Mono<Void> writeResponse(ServerResponse response, ServerWebExchange exchange) {
		ServerHttpResponse serverHttpResponse = exchange.getResponse();
		serverHttpResponse.getHeaders().add("Context-Type", "application/json;charset=UTF-8");
		//利用RestResult将异常数据封装，并转换呈Json格式
		RestResult restResult = new RestResult();
		restResult.setStatus(ReturnCode.REC_21.getCode());
		restResult.setMsg(ReturnCode.REC_21.getName());
		//Gson gson = new Gson();
		//String json = gson.toJson(restResult);
		String json = JSON.toJSONString(restResult);
		//响应客户端异常数据
		DataBuffer buffer = serverHttpResponse.bufferFactory().wrap(json.getBytes());
		return serverHttpResponse.writeWith(Mono.just(buffer));
	}

	private Mono<ServerResponse> handleBlockedRequest(ServerWebExchange exchange, Throwable ex) {
		return GatewayCallbackManager.getBlockHandler().handleRequest(exchange, ex);
	}

}
