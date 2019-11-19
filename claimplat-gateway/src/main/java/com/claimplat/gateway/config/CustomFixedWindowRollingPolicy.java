package com.claimplat.gateway.config;

import ch.qos.logback.core.rolling.FixedWindowRollingPolicy;

public class CustomFixedWindowRollingPolicy extends FixedWindowRollingPolicy{

	private static int MAX_WINDOW_SIZE = 100000;
	
	@Override
	protected int getMaxWindowSize() {
		return MAX_WINDOW_SIZE;
	}
	
}
