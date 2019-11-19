package com.claimplat.adminapi.util;

public class CodeUtil {
	
	public static String fetchMerchantCode() {
		return "M" + System.currentTimeMillis();
	}
	
	public static String fetchRuleConfigCode() {
		return "RC" + System.currentTimeMillis();
	}
	
	public static String fetchUrlConfigCode() {
		return "UC" + System.currentTimeMillis();
	}
	
	public static String fetchRuleItemCode() {
		return "RL" + System.currentTimeMillis();
	}
	
	public static String fetchParameterCode() {
		return "PT" + System.currentTimeMillis();
	}
	
	public static String fetchMerchantUrlCode() {
		return "MUC" + System.currentTimeMillis();
	}
	
}
