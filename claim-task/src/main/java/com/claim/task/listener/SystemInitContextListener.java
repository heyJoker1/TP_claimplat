package com.claim.task.listener;

import java.time.Duration;
import java.time.LocalDateTime;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.claim.common.util.DateUtil;


@WebListener
public class SystemInitContextListener implements ServletContextListener{
	
	private static Logger logger = LoggerFactory.getLogger(SystemInitContextListener.class);
	
	private LocalDateTime beginDateTime;
	
	@Override
	public void contextInitialized(ServletContextEvent event) {
		beginDateTime = LocalDateTime.now();
		logger.info("系统启动时间={}",DateUtil.formatLocalDateTime(beginDateTime));
		logger.info("系统相关属性如下：");
		logger.info("java.vm.vendor={}",System.getProperty("java.vm.vendor"));
		logger.info("java.vm.name={}",System.getProperty("java.vm.name"));
		logger.info("java.vm.version={}",System.getProperty("java.vm.version"));
		logger.info("java.runtime.version={}",System.getProperty("java.runtime.version"));
		
		logger.info("java.vm.specification.vendor={}",System.getProperty("java.vm.specification.vendor"));
		logger.info("java.vm.specification.name={}",System.getProperty("java.vm.specification.name"));
		
		logger.info("os.arch={}",System.getProperty("os.arch"));
		logger.info("os.name={}",System.getProperty("os.name"));
		logger.info("os.version={}",System.getProperty("os.version"));
		
		logger.info("user.country={}",System.getProperty("user.country"));
		logger.info("user.language={}",System.getProperty("user.language"));
		logger.info("file.encoding={}",System.getProperty("file.encoding"));
		
		String contextPath = event.getServletContext().getContextPath();
		logger.info("上下文路径={ }",contextPath);
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				logger.info("shutdownhook执行时间="+DateUtil.formatLocalDateTime(LocalDateTime.now()));
			}
		});
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		LocalDateTime endDateTime = LocalDateTime.now();
		Duration duration = Duration.between(beginDateTime, endDateTime);
		logger.info("系统停止时间={},运行时长={}s",DateUtil.formatLocalDateTime(endDateTime),duration.getSeconds());
	}
}
