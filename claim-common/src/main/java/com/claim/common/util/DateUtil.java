package com.claim.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtil {
	
	public static final String DATEFORMATT_DATETIME = "yyyy-MM-dd HH:mm:ss";
	public static final String DATEFORMATT_DATE5 = "yyyy";
	public static final String DATEFORMATT_DATE6 = "yyyyMM";
	public static final String DATEFORMATT_DATE7 = "yyyy-MM";
	public static final String DATEFORMATT_DATE8 = "yyyyMMdd";
	public static final String DATEFORMATT_DATE10 = "yyyy-MM-dd";
	public static final String DATEFORMATT_DATETIME14 = "yyyyMMddHHmmss";
	public static final String DATEFORMATT_DATETIME16 = "yyyy-MM-dd HH:mm";
	public static final String DATEFORMATT_TIME2 = "HH";
	public static final String DATEFORMATT_TIME5 = "mm:ss";
	public static final String DATEFORMATT_TIME8 = "HH:mm:ss";
	public static final String DATEFORMATT_TIMESTAMP = "yyyy-MM-dd HH:mm:ss.SSS";
	public static final String DATEFORMATT_TIMESTAMP17 = "yyyyMMddHHmmssSSS";
	
	/*使用默认格式化日期*/
	public static String formatDate(Date date) {
		SimpleDateFormat fm = new SimpleDateFormat(DateUtil.DATEFORMATT_DATETIME);
		return fm.format(date);
	}
	
	/*指定格式化日期*/
	public static String formatDate(Date date,String formatStr) {
		SimpleDateFormat fm = new SimpleDateFormat(formatStr);
		return fm.format(date);
	}
	
	/*将LocalDate按格式转换成字符串*/
	public static String formatLocalDate(LocalDate date) {
		return date.format(DateTimeFormatter.ofPattern(DateUtil.DATEFORMATT_DATE10));
	}
	
	/*将LocalDateTime按格式转换成字符串*/
	public static String formatLocalDateTime(LocalDateTime dateTime) {
		return dateTime.format(DateTimeFormatter.ofPattern(DateUtil.DATEFORMATT_DATETIME));
	}
	
	/*将LocalDateTime按格式转换成字符串*/
	public static String formatLocalDateTime(LocalDateTime dateTime,String formatStr) {
		return dateTime.format(DateTimeFormatter.ofPattern(formatStr));
	}
	
	/*默认格式转换日期*/
	public static Date toDate(String date) {
		SimpleDateFormat fm = new SimpleDateFormat(DateUtil.DATEFORMATT_DATETIME);
		fm.setLenient(false);
		try {
			return fm.parse(date);
		} catch (ParseException e) {
			throw new RuntimeException("日期转换出错",e);
		}
	}
	
	/*指定格式转换日期*/
	public static Date toDate(String date,String formatStr) {
		SimpleDateFormat fm = new SimpleDateFormat(formatStr);
		fm.setLenient(false);
		try {
			return fm.parse(date);
		} catch (ParseException e) {
			throw new RuntimeException("日期转换出错",e);
		}
	}
	
	/*默认格式转换日期*/
	public static LocalDateTime toLocalDateTime(String dateTime) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DateUtil.DATEFORMATT_DATETIME);
		return LocalDateTime.parse(dateTime,formatter);
	}
	
	/*指定格式转换日期*/
	public static LocalDateTime toLocalDateTime(String dateTime,String formatStr) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatStr);
		return LocalDateTime.parse(dateTime,formatter);
	}
	
	/*将LocalDateTime转换为java.util.Date*/
	public static Date fromLocalDateTimeToDate(LocalDateTime dateTime) {
		Instant instant = dateTime.toInstant(ZoneOffset.ofHours(8));
		return Date.from(instant);
	}
	
	/*将java.util.Date转换为LocalDateTime*/
	public static LocalDateTime fromDateToLocalDateTime(Date date) {
		return LocalDateTime.ofInstant(date.toInstant(), ZoneOffset.ofHours(8));
	}
		
	/*将String转换为LocalDateTime*/
	public static LocalDateTime fromStringToLocalDateTime(String dateStr) {
		Date date = toDate(dateStr);
		return LocalDateTime.ofInstant(date.toInstant(), ZoneOffset.ofHours(8));	
	}
	
	public static LocalDateTime toDateToLocalDateTimeBegin(LocalDate localDate) {
		String dateTime = formatLocalDate(localDate)+" 00:00:00";	
		LocalDateTime localDateTime = toLocalDateTime(dateTime);
		return localDateTime;
	}
		
	public static LocalDateTime toDateToLocalDateTimeEnd(LocalDate localDate) {
		String dateTime = formatLocalDate(localDate)+" 23:59:59";	
		LocalDateTime localDateTime = toLocalDateTime(dateTime);
		return localDateTime;	
	}
}
