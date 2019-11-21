package com.claimplat.core.logback;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.IThrowableProxy;
import ch.qos.logback.classic.spi.ThrowableProxyUtil;
import ch.qos.logback.core.LayoutBase;

public class JSONEventLayout  extends LayoutBase<ILoggingEvent>{
	private final int DEFAULT_SIZE = 256;
	private final int UPPER_LIMIT = 2048;
	private final static char DBL_QUOTE = '"';
	private final static char COMMA = '\n';

	private StringBuilder buf = new StringBuilder(DEFAULT_SIZE);
	private DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
	private Pattern MDC_VAR_PATTERN = Pattern.compile("\\@\\{([^}^:-]*)(:-([^}]*)?)?\\}");

	private boolean locationInfo = false;
	private int callerStackIdx = 0;
	private boolean properties = false;

	String project;							//属性需要与logback.xml文件中标签对应
	String env;	
	List<AdditionalField> additionalFields;

	@Override
	public void start() {
		super.start();
	}

	@Override
	public String doLayout(ILoggingEvent event) {
		if (buf.capacity() > UPPER_LIMIT) {
			buf = new StringBuilder(DEFAULT_SIZE);
		} else {
			buf.setLength(0);
		}

		Map<String, String> mdc = event.getMDCPropertyMap();
		buf.append("{");
		buf.append(COMMA);
		//日志时间
		appendKeyValue(buf, "Timestamp",
				df.format(new Date(event.getTimeStamp())), null);
		buf.append(COMMA);
		
		//日志级别
		appendKeyValue(buf, "Level", event.getLevel().toString(), null);
		buf.append(COMMA);
		
		//线程名
		appendKeyValue(buf, "Thread", event.getThreadName(), null);
		IThrowableProxy tp = event.getThrowableProxy();
		if (tp != null) {
			buf.append(COMMA);
			String throwable = ThrowableProxyUtil.asString(tp);
			appendKeyValue(buf, "throwable", throwable, null);
		}
		buf.append(COMMA);
		
		//工程名
		appendKeyValue(buf, "project", project, mdc);
		buf.append(COMMA);
		
		//测试或者生产环境
		appendKeyValue(buf, "env", env, mdc);
		buf.append(COMMA);
		
		//ip地址
		try {
			appendKeyValue(buf, "Host", InetAddress.getLocalHost().getHostAddress(), null);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		buf.append(COMMA);
		
		appendKeyValue(buf, "Logger", event.getLoggerName(), null);
		buf.append(COMMA);
		
		//日志信息
		appendKeyValue(buf, "Message", event.getFormattedMessage(), null);
		buf.append(COMMA);
				
		if (locationInfo) {
			StackTraceElement[] callerDataArray = event.getCallerData();
			if (callerDataArray != null
					&& callerDataArray.length > callerStackIdx) {
				buf.append(COMMA);
				buf.append("\"location\":{");
				StackTraceElement immediateCallerData = callerDataArray[callerStackIdx];
				appendKeyValue(buf, "class",
						immediateCallerData.getClassName(), null);
				buf.append(COMMA);
				appendKeyValue(buf, "method",
						immediateCallerData.getMethodName(), null);
				buf.append(COMMA);
				appendKeyValue(buf, "file", immediateCallerData.getFileName(),
						null);
				buf.append(COMMA);
				appendKeyValue(buf, "line",
						Integer.toString(immediateCallerData.getLineNumber()),
						null);
				buf.append("}");
			}
		}
		/*
		 * <log4j:properties> <log4j:data name="name" value="value"/>
		 * </log4j:properties>
		 */
		if (properties) {
			Map<String, String> propertyMap = event.getMDCPropertyMap();
			if ((propertyMap != null) && (propertyMap.size() != 0)) {
				Set<Entry<String, String>> entrySet = propertyMap.entrySet();
				buf.append(COMMA);
				buf.append("\"properties\":{");
				Iterator<Entry<String, String>> i = entrySet.iterator();
				while (i.hasNext()) {
					Entry<String, String> entry = i.next();
					appendKeyValue(buf, entry.getKey(), entry.getValue(), null);
					if (i.hasNext()) {
						buf.append(COMMA);
					}
				}
				buf.append("}");
			}
		}

		if(additionalFields != null) {
			for(AdditionalField field : additionalFields) {
				buf.append(COMMA);
				appendKeyValue(buf, field.getKey(), field.getValue(), mdc);
			}
		}
		buf.append("}");
		return buf.toString();
	}
	
	private void appendKeyValue(StringBuilder buf, String key, String value,
			Map<String, String> mdc) {
		if (value != null) {
			buf.append(DBL_QUOTE);
			buf.append(escape(key));
			buf.append(DBL_QUOTE);
			buf.append(':');
			buf.append(DBL_QUOTE);
			buf.append(escape(mdcSubst(value, mdc)));
			buf.append(DBL_QUOTE);
		} else {
			buf.append(DBL_QUOTE);
			buf.append(escape(key));
			buf.append(DBL_QUOTE);
			buf.append(':');
			buf.append(" ");
		}
	}

	private String mdcSubst(String v, Map<String, String> mdc) {
		if (mdc != null && v != null && v.contains("@{")) {
			Matcher m = MDC_VAR_PATTERN.matcher(v);
			StringBuffer sb = new StringBuffer(v.length());
			while (m.find()) {
				String val = mdc.get(m.group(1));
				if (val == null) {
					// If a default value exists, use it
					val = (m.group(3) != null) ? val = m.group(3) : m.group(1) + "_NOT_FOUND";
				}
				m.appendReplacement(sb, Matcher.quoteReplacement(val));
			}
			m.appendTail(sb);
			return sb.toString();
		}
		return v;
	}

	private String escape(String s) {
		if (s == null)
			return null;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			switch (ch) {
			case '"':
				sb.append("\\\"");
				break;
			case '\\':
				sb.append("\\\\");
				break;
			case '\b':
				sb.append("\\b");
				break;
			case '\f':
				sb.append("\\f");
				break;
			case '\n':
				sb.append("\\n");
				break;
			case '\r':
				sb.append("\\r");
				break;
			case '\t':
				sb.append("\\t");
				break;
			case '/':
				sb.append("\\/");
				break;
			default:
				if (ch >= '\u0000' && ch <= '\u001F') {
					String ss = Integer.toHexString(ch);
					sb.append("\\u");
					for (int k = 0; k < 4 - ss.length(); k++) {
						sb.append('0');
					}
					sb.append(ss.toUpperCase());
				} else {
					sb.append(ch);
				}
			}
		}// for
		return sb.toString();
	}
	
	@Override
	public String getContentType() {
		return "application/json";
	}
	
	public boolean setLocationInfo() {
		return locationInfo;
	}

	public void setLocationInfo(boolean locationInfo) {
		this.locationInfo = locationInfo;
	}

	public int getCallerStackIdx() {
		return callerStackIdx;
	}
	
	public void setCallerStackIdx(int callerStackIdx) {
		this.callerStackIdx = callerStackIdx;
	}
	
	public boolean setProperties() {
		return properties;
	}

	public void setProperties(boolean properties) {
		this.properties = properties;
	}
	
	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project= project;
	}

	
	public String getEnv() {
		return env;
	}

	public void setEnv(String env) {
		this.env = env;
	}

	public void addAdditionalField(AdditionalField p) {
		if(additionalFields == null) {
			additionalFields = new ArrayList<AdditionalField>();
		}
		additionalFields.add(p);
	}
}

