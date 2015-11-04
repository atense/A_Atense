/*
 *  StringUtil.java
 *
 *  Created by Kyle on 2015-11-04.
 *  Copyright (c) 2015 Atense. All rights reserved.
 */
package com.atense.utils;

/**
 * String utility
 * 
 * @author <a href="#" target="_black">Kyle</a> 2015-11-04
 */
public class StringUtil {

	public static String formatXML2String(String strData) {
		strData = strData.replaceAll("&lt;", "<");
		strData = strData.replaceAll("&gt;", ">");
		strData = strData.replaceAll("&apos;", "'");
		strData = strData.replaceAll("&quot;", "\"");
		strData = strData.replaceAll("&amp;", "&");
		return strData;
	}

	public static String formatString2XML(String strData) {
		strData = strData.replaceAll("&", "&amp;");
		strData = strData.replaceAll("<", "&lt;");
		strData = strData.replaceAll(">", "&gt;");
		strData = strData.replaceAll("'", "&apos;");
		strData = strData.replaceAll("\"", "&quot;");
		return strData;
	}
	
	/** default join separator **/
    public static final String DEFAULT_JOIN_SEPARATOR = ",";

	/**
	 * join array to string. if separator is null, use {@link #DEFAULT_JOIN_SEPARATOR}
	 * 
	 * <pre>
     * join(null, "#")     =   "";
     * join([], "#$")      =   "";
     * join([a,b,c], null) =   "a,b,c";
     * join([a,b,c], "")   =   "abc";
     * join([a,b,c], "#")  =   "a#b#c";
     * join([a,b,c], "#$") =   "a#$b#$c";
     * </pre>
	 * 
	 * @param array
	 * @param separator
	 * @return join array to string with separator. if list is empty, return ""
	 */
	public static String join(String[] array, String separator) {
		String r = "";
		if (array != null && array.length > 0) {
			if(separator == null) {
				separator = DEFAULT_JOIN_SEPARATOR;
			}
			for (String str : array) {
				r += str + separator;
			}
			r = r.substring(0, r.length() - separator.length());
		}
		return r;
	}

	/**
	 * is null or its length is 0
	 * 
	 * <pre>
	 * isEmpty(null) = true;
	 * isEmpty(&quot;&quot;) = true;
	 * isEmpty(&quot; &quot;) = false;
	 * </pre>
	 * 
	 * @param input
	 * @return if string is null or its size is 0, return true, else return
	 *         false.
	 */
	public static boolean isEmpty(String input) {
		return (input == null || input.length() == 0);
	}
	
	/**
	 * is null or its length is 0 or it is made by space
	 * 
	 * <pre>
	 * isBlank(null) = true;
	 * isBlank(&quot;&quot;) = true;
	 * isBlank(&quot; &quot;) = true;
	 * isBlank(&quot;a&quot;) = false;
	 * isBlank(&quot;a &quot;) = false;
	 * isBlank(&quot; a&quot;) = false;
	 * isBlank(&quot;a b&quot;) = false;
	 * </pre>
	 * 
	 * @param input
	 * @return if string is null or its size is 0 or it is made by space, return
	 *         true, else return false.
	 */
	public static boolean isBlank(String input) {
		return (input == null || input.trim().length() == 0);
	}

}
