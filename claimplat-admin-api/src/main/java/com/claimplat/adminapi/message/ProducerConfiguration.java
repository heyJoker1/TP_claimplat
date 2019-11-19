package com.claimplat.adminapi.message;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProducerConfiguration {
	
	@Resource
	private ProducerProperties producerProperties;
	
	@Bean(name = "messageProducer",destroyMethod = "shutdown")
	public DefaultMQProducer getRocketMQProducer() {
		if(StringUtils.isBlank(producerProperties.getNamesrvAddr())) {
			throw new IllegalArgumentException("nameServerAddr is blank");
		}
		
		if(StringUtils.isBlank(producerProperties.getGroupName())) {
			throw new IllegalArgumentException("groupName is blank");
		}
		
		DefaultMQProducer producer = new DefaultMQProducer(producerProperties.getGroupName());
		producer.setNamesrvAddr(producerProperties.getNamesrvAddr());
		producer.setSendMsgTimeout(producerProperties.getSendMsgTomeout());
		
		try {
			producer.start();
		} catch (MQClientException e) {
			throw new RuntimeException(e);
		}
		return producer;
	}
}
