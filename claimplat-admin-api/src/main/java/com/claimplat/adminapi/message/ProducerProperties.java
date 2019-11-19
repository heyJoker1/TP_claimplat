package com.claimplat.adminapi.message;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ProducerProperties {
	
	@Value("${rocktmq.producer.groupName}")
	private String groupName;
	
	@Value("${rocktmq.producer.namesrvAddr}")
	private String namesrvAddr;
	
	@Value("${rocktmq.producer.sendMsgTomeout}")
	private Integer sendMsgTomeout;
	
	public String getGroupName() {
		return groupName;
	}
	public String getNamesrvAddr() {
		return namesrvAddr;
	}
	public Integer getSendMsgTomeout() {
		return sendMsgTomeout;
	}

}
