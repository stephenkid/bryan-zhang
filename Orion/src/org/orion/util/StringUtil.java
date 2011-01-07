
package org.orion.util;

import java.text.SimpleDateFormat;
import java.util.Locale;


public class StringUtil {
	/**
	 * 分割符 (默认为: , 或 ; 或 |)
	 */
	private static final String SPLIT_DELIMITER = ",|;|\\|";
	
	private StringUtil() {
	}

	

	/**
	 * 按照格式串格式化Java数据对象
	 * @param pattern 格式串
	 * @param obj Java数据对象
	 * @return 格式化后的字符串
	 */
	public static String format(String pattern, Object obj) {
		if (obj == null)
			return "";

		if (obj instanceof String) {
			return (String) obj;
		}

		if (pattern == null) {
			return obj.toString();
		}

		if (obj instanceof java.util.Date) {
			return format(pattern, (java.util.Date) obj);
		}

		if (obj instanceof java.math.BigDecimal) {
			return format(pattern, (java.math.BigDecimal) obj);
		}

		if (obj instanceof Double) {
			return format(pattern, (Double) obj);
		}

		if (obj instanceof Float) {
			return format(pattern, (Float) obj);
		}

		if (obj instanceof Long) {
			return format(pattern, (Long) obj);
		}

		if (obj instanceof Integer) {
			return format(pattern, (Integer) obj);
		}

		return obj.toString();
	}

	/**
	 * 按照格式串格式化Date对象
	 * @param pattern 格式串
	 * @param locale 区域属性
	 * @param d Date对象
	 * @return 格式化后的字符串
	 */
	public static String format(String pattern, Locale locale, java.util.Date d) {
		if (d == null)
			return "";
		SimpleDateFormat sdf = new SimpleDateFormat(pattern, locale);
		return sdf.format(d);
	}

	/**
	 * 按照格式串格式化Date对象
	 * @param pattern 格式串
	 * @param d Date对象
	 * @return 格式化后的字符串
	 */
	public static String format(String pattern, java.util.Date d) {
		if (d == null)
			return "";
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(d);
	}

}