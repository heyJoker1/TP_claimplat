package com.claimplat.offline.config;


import com.claimplat.core.logback.JSONEventLayout;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.Layout;
import ch.qos.logback.core.UnsynchronizedAppenderBase;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * logback打印日志连接存储到Redis方法1
 * @author Joker
 *
 */
public class RedisAppender extends UnsynchronizedAppenderBase<ILoggingEvent>{
	
	private JedisPool pool;
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
			JedisPoolConfig config = new JedisPoolConfig();
			config.setMaxIdle(10);
			config.setMinIdle(2);
			config.setMaxTotal(100);
			config.setTestOnBorrow(true);
			pool = new JedisPool(config,host,port,timeout,password,database);
		} catch (Exception e) {
			System.out.println("初始化Redis连接池异常  "+e);
		}
	}
	
	@Override
	protected void append(ILoggingEvent event) {
		Jedis client = null;
		//Jedis client = pool.getResource();
		try {			
			if(pool != null) {
				client = pool.getResource();
				String json = layout.doLayout(event);
				client.lpush(key, json);
				//String json = layout == null ?? jsonlayout.doLayout(event) : layout.doLayout(event);
				//client.rpush(key,json);
			}
		} catch (Exception e) {
			System.out.println("连接Reids异常  "+e);
		} finally {
			if(client != null) {
				//pool.returnResouce(client);
				client.close();
			}
		}
	}
	
	@Override
	public void stop() {
		super.stop();
		if(pool != null) {
			pool.destroy();
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
