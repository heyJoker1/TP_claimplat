package com.claimplat.adminapi.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.claimplat.adminapi.service.OrderService;
import com.claimplat.adminapi.vo.PageInfoVo;
import com.claimplat.adminapi.vo.order.OrderItemVo;
import com.claimplat.adminapi.vo.order.OrderQuery;
import com.claimplat.adminapi.vo.order.OrderVo;
import com.claimplat.common.constant.RestResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api("请求查询")
@RestController
@RequestMapping("/order")
public class OrderRest {
	
	@Autowired
	private OrderService orderService;
	
	/**
	 * 请求查询接口
	 * @param orderQuery
	 * @return
	 */
	@PostMapping("getOrder")
	@ApiImplicitParam(name = "token",paramType = "header")
	@ApiOperation(value = "请求查询",httpMethod = "POST",response = OrderVo.class)
	public RestResult getOrder(@ApiParam(value = "查询条件") @RequestBody OrderQuery orderQuery) {
		PageInfoVo<OrderVo> page = orderService.getOrderList(orderQuery);
		RestResult restResult = new RestResult();
		restResult.setData(page);
		return restResult;
	}
	
	@PostMapping("findOrderByCode")
	@ApiImplicitParam(name = "token",paramType = "header")
	@ApiOperation(value = "查询详情",httpMethod = "POST",response = OrderItemVo.class)
	public RestResult getOrder(@ApiParam(value = "查询条件") @RequestParam String code) {
		OrderItemVo orderItemVo = orderService.findByCode(code);
		RestResult restResult = new RestResult();
		restResult.setData(orderItemVo);
		return restResult;
	}
	
	@PostMapping("sendOrderRest")
	@ApiImplicitParam(name = "token",paramType = "header")
	@ApiOperation(value = "请求重发",httpMethod = "POST")
	public RestResult sendOrderRest(@ApiParam(value = "orderCode") @RequestParam String orderCode) {
		orderService.sendOrderRest(orderCode);
		return new RestResult();
	}
}
