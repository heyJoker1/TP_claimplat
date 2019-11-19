package com.claimplat.adminapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.ContextStoppedEvent;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@ServletComponentScan
@EntityScan(basePackages = {"com.claimplat.core.entity","com.claimplat.adminapi.entity"})
public class PlatAdminApiApplication implements ApplicationListener<ApplicationEvent>{

	private static final Logger LOGGER = LoggerFactory.getLogger(PlatAdminApiApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(PlatAdminApiApplication.class, args);
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
	
}
