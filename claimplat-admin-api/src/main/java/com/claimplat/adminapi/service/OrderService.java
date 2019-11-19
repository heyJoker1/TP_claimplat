package com.claimplat.adminapi.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.claimplat.adminapi.repository.MerchantRepository;
import com.claimplat.adminapi.repository.OrderRepository;
import com.claimplat.adminapi.vo.PageInfoVo;
import com.claimplat.adminapi.vo.order.OrderItemVo;
import com.claimplat.adminapi.vo.order.OrderQuery;
import com.claimplat.adminapi.vo.order.OrderVo;
import com.claimplat.common.enums.OrderStatusEnum;
import com.claimplat.common.message.CallBusinessSystemMessageObject;
import com.claimplat.common.message.enums.MessageTagEnum;
import com.claimplat.common.message.enums.MessageTopicEnum;
import com.claimplat.common.util.DateUtil;
import com.claimplat.core.entity.Order;
import com.google.gson.GsonBuilder;

@Service
@Transactional
public class OrderService extends BaseService{
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private DefaultMQProducer messageProducer;
	
	@Autowired
	private MerchantRepository merchantRepository;

	/**
	 * 请求查询
	 * @param orderQuery
	 * @return
	 */
	public PageInfoVo<OrderVo> getOrderList(OrderQuery orderQuery) {
		Pageable pageable = PageRequest.of(orderQuery.getPageIndex(), orderQuery.getPageSize());
		if(pageable.getPageSize() > 20) {
			throw new IllegalStateException("页面容量不能超过20！");
		}
		if(pageable.getPageNumber() < 0) {
			throw new IllegalStateException("页码不能为负数！");
		}
		LocalDateTime beginDateTime = null;
		LocalDateTime endDateTime = null;
		if(orderQuery.getBeginDate() != null) {
			beginDateTime = DateUtil.toDateToLocalDateTimeBegin(orderQuery.getBeginDate());
		}
		if(orderQuery.getEndDate() != null) {
			endDateTime = DateUtil.toDateToLocalDateTimeEnd(orderQuery.getEndDate());
		}
		
		Page<Order> page = orderRepository.getPage(orderQuery,pageable,beginDateTime,endDateTime);
		List<Order> orders = page.getContent();
		List<OrderVo> orderVos = new ArrayList<OrderVo>();
		for(Order order : orders) {
			String merchantName = merchantRepository.findByCode(order.getMerchantCode()).getName();
			OrderVo orderVo = new OrderVo();
			if(order.getStatus() == OrderStatusEnum.COMPLETE) {
				orderVo.setNeedSend(false);
			}
			orderVo.setCode(order.getCode());
			String createDateTime = DateUtil.formatLocalDateTime(order.getCreateDateTime());
			orderVo.setCreateDateTime(createDateTime);
			orderVo.setMerchantCode(order.getMerchantCode());
			orderVo.setBizContentBefore(order.getBizContentBefore());
			orderVo.setBizContentAfter(order.getBizContentAfter());
			orderVo.setBusinessType(order.getBusinessType());
			orderVo.setBusinessTypeName(order.getBusinessType().getName());
			orderVo.setStatus(order.getStatus());
			orderVo.setStatusName(order.getStatus().getName());
			orderVo.setRequestId(order.getRequestId());
			orderVo.setBizModelAfter(order.getBizContentAfter());
			orderVo.setBizContentBefore(order.getBizContentBefore());
			orderVo.setMerchantName(merchantName);
			String completeDateTime = null;
			if(order.getCompleteDateTime() != null) {
				completeDateTime = DateUtil.formatLocalDateTime(order.getCompleteDateTime());
			}
			orderVo.setCompleteDateTime(completeDateTime);
			orderVos.add(orderVo);
		}
		PageInfoVo<OrderVo> pageInfoVo = new PageInfoVo<OrderVo>(page.getTotalElements(), page.getNumber(), page.getSize(), orderVos);
		return pageInfoVo;
		
	}

	public OrderItemVo findByCode(String code) {
		Order order = orderRepository.findByCode(code);
		OrderItemVo orderItemVo = new OrderItemVo();
		String createDateTime = null;
		String readyDateTime = null;
		if(order.getCreateDateTime() != null) {
			createDateTime = DateUtil.formatLocalDateTime(order.getCreateDateTime());
		}
		orderItemVo.setReadyDateTime(readyDateTime);
		orderItemVo.setCode(order.getCode());
		orderItemVo.setCreateDateTime(createDateTime);
		orderItemVo.setMerchantCode(order.getMerchantCode());
		orderItemVo.setBizContentBefore(order.getBizContentBefore());
		orderItemVo.setBizContentAfter(order.getBizContentAfter());
		orderItemVo.setBusinessType(order.getBusinessType());
		orderItemVo.setStatus(order.getStatus());
		String completeDateTime = null;
		if(order.getCompleteDateTime() != null) {
			completeDateTime = DateUtil.formatLocalDateTime(order.getCompleteDateTime());
		}
		orderItemVo.setCompleteDateTime(completeDateTime);
		orderItemVo.setExtraData(order.getExtraData());
		orderItemVo.setNotifyUrl(order.getNotifyUrl());
		orderItemVo.setSuccessDateTime(order.getSuccessDateTime());
		orderItemVo.setRequestTimestamp(order.getRequestTimestamp());
		orderItemVo.setRequestNonce(order.getRequestNonce());
		orderItemVo.setRequestId(order.getRequestId());
		orderItemVo.setRemark(order.getRemark());
		orderItemVo.setPassBackData(order.getPassbackData());
		return orderItemVo;
	}

	public void sendOrderRest(String orderCode) {
		Order order = orderRepository.findByCode(orderCode);
		CallBusinessSystemMessageObject callMessage = new CallBusinessSystemMessageObject();
		callMessage.setMerchantCode(order.getMerchantCode());
		callMessage.setOrderCode(order.getCode());
		
		String sumBankAccountBody = new GsonBuilder().create().toJson(callMessage);
		Message message = new Message(MessageTopicEnum.BUSINESS_SYSTEM.toString(),MessageTagEnum.CALL_BUSINESS_SYSTEM.toString(),
				UUID.randomUUID().toString(),sumBankAccountBody.getBytes());
		SendResult sumBankAccountSendResult = null;
		try {
			sumBankAccountSendResult = messageProducer.send(message);
		} catch (Exception e) {
			throw new RuntimeException("发送调用核心系统接口消息通知出错",e);
		}
	}
	
}
