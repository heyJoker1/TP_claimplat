package com.test;

import static org.junit.Assert.assertEquals;

import java.net.InetAddress;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import ch.qos.logback.core.util.StatusPrinter;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisAppenderTest {
	
	String key = "logstash";
	Jedis redis;
	
	@Test
	public void logTestCustomLayout() throws Exception {
		// refer to logback-custom-layout.xml in test folder
		configLogger("/logback.xml");
		Logger logger = LoggerFactory.getLogger(RedisAppenderTest.class);
		logger.debug("Test Custom Layout Log");
		long len = redis.llen(key);
		assertEquals(1L, len);
		String content = redis.lpop(key);
		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = mapper.readTree(content);

		assertEquals("test-application", node.get("source").asText());
		assertEquals("Test Custom Layout Log", node.get("message").asText());
		assertEquals(InetAddress.getLocalHost().getHostName(), node.get("host").asText());
	}

	@Before
	public void setUp() {
		System.out.println("Before Test, clearing Redis");
		JedisPool pool = new JedisPool("localhost");
		redis = pool.getResource();
		// clear the redis list first
		redis.ltrim(key, 1, 0);
	}

	protected void configLogger(String loggerxml) {
		LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();

		try {
			JoranConfigurator configurator = new JoranConfigurator();
			configurator.setContext(context);
			context.reset();
			configurator.doConfigure(this.getClass().getResourceAsStream(loggerxml));
		} catch (JoranException je) {
			// StatusPrinter will handle this
		}
		StatusPrinter.printInCaseOfErrorsOrWarnings(context);
	}
	
}
