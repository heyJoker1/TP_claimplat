package com.claimplat.offline.config;


import com.claimplat.core.logback.JSONEventLayout;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.Layout;
import ch.qos.logback.core.UnsynchronizedAppenderBase;
import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;

/**
 * logback打印日志连接存储到Redis方法2
 * @author Joker
 *
 */
public class RedisAppender2 extends UnsynchronizedAppenderBase<ILoggingEvent>{
	
	private RedisClient client;
	private Layout<ILoggingEvent> layout;
	JSONEventLayout jsonlayout;

	//logback.xml中取值
	private String host = "";
	private String password = null;
	private int port = 0;
	private int timeout = 0;
	private int database = 0;
	private String key = "";
	
	@Override
	public void start() {
		super.start();
		try {
			client = RedisClient.create(RedisURI.create(host+port+database));
		} catch (Exception e) {
			System.out.println("初始化Redis连接池异常  "+e);
		}
	}
	
	@Override
	protected void append(ILoggingEvent event) {
		StatefulRedisConnection<String, String> connect = null;
		try {
			connect = client.connect();
			RedisCommands<String, String> commands = connect.sync();
			String json = layout.doLayout(event);
			commands.lpush(key, json);
		} catch (Exception e) {
			System.out.println("连接Reids异常  "+e);
		} finally {
			if(connect != null) {
				connect.close();
			}
		}
	}
	
	@Override
	public void stop() {
		super.stop();
		if(client != null) {
			client.shutdown();
		}
	}

	public Layout<ILoggingEvent> getLayout() {
		return layout;
	}

	public void setLayout(Layout<ILoggingEvent> layout) {
		this.layout = layout;
	}
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public int getDatabase() {
		return database;
	}

	public void setDatabase(int database) {
		this.database = database;
	}
	
}
