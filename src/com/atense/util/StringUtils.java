/*
 *  StringUtil.java
 *
 *  Created by Kyle on 2015-11-04.
 *  Copyright (c) 2015 Atense. All rights reserved.
 */
package com.atense.util;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

/**
 * String utility
 * 
 * @author <a href="#" target="_black">Kyle</a> 2015-11-04
 */
public class StringUtils {

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
	 * join array to string. if separator is null, use
	 * {@link #DEFAULT_JOIN_SEPARATOR}
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
			if (separator == null) {
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

	/**
	 * 清除str前面的不可见字符
	 * 
	 * @param str
	 * @return
	 */
	public static String trimLeadingWhitespace(String str) {
		if (isEmpty(str)) {
			return str;
		}
		StringBuffer buf = new StringBuffer(str);
		while (buf.length() > 0 && Character.isWhitespace(buf.charAt(0))) {
			buf.deleteCharAt(0);
		}
		return buf.toString();
	}

	/**
	 * 使用delimiter来分割str，生成数组
	 * 
	 * @param str
	 * @param delimiter
	 * @return
	 */
	public static String[] delimitedListToStringArray(String str,
			String delimiter) {
		if (str == null) {
			return new String[0];
		}
		if (delimiter == null) {
			return new String[] { str };
		}

		List<String> result = new ArrayList<String>();
		if ("".equals(delimiter)) {
			result.add(str);
		} else {
			int pos = 0;
			int delPos = 0;
			while ((delPos = str.indexOf(delimiter, pos)) != -1) {
				result.add(str.substring(pos, delPos));
				pos = delPos + delimiter.length();
			}
			if (str.length() > 0 && pos <= str.length()) {
				// Add rest of String, but not in case of empty input.
				result.add(str.substring(pos));
			}
		}
		return result.toArray(new String[result.size()]);
	}

	/**
	 * 返回文件名，去掉路径
	 * 
	 * @param path
	 * @return
	 */
	public static String getFilename(String path) {
		if (path == null) {
			return null;
		}
		int separatorIndex = path.lastIndexOf(File.separator);
		return (separatorIndex != -1 ? path.substring(separatorIndex + 1)
				: path);
	}

	/**
	 * 返回文件的扩展名
	 */
	public static String getFilenameExtension(String path) {
		if (path == null) {
			return null;
		}
		int sepIndex = path.lastIndexOf(".");
		return (sepIndex != -1 ? path.substring(sepIndex + 1) : null);
	}

	/**
	 * 返回唯一性字符串
	 * 
	 * @return
	 */
	public static String getUUID() {
		UUID uuid = UUID.randomUUID();
		String str = uuid.toString();

		return str.replaceAll("-", "");
	}

	/**
	 * 判断字符串的长度是否合法返回boolean则合法，返回string则超过判断长度，并返回长度内的字符串
	 * 
	 * @param str
	 *            判断的字符串
	 * @param length
	 *            判断字符串的长度
	 * @return
	 */
	public static Object validateLength(String str, int length) {
		// 如果输入的字符串大于长度则返回正确的字符串
		String reStr = null;
		try {
			// 将字符串转成gbk模式
			byte[] bytes = str.getBytes("gbk");
			// 长度给l赋值
			int l = length;
			// 如果字符数组大于长度表示字符大于规定长度主要是为了判断文字与字母同在的时候
			if (bytes.length > length) {
				// 如果长度判断小于2
				if (length < 2) {
					l = 1;
				} else if (bytes[length - 2] > 0 && bytes[length - 1] < 0) {
					l = length - 1;
				}

			}
			byte[] b = new byte[l];
			if (bytes.length > length) {
				for (int i = 0; i < l; i++) {
					b[i] = bytes[i];
				}
				reStr = new String(new String(b, "gbk").getBytes("utf-8"),
						"utf-8");
			} else {
				return true;
			}

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return reStr;
	}

	public static String stringAsCData(String unescaped) {
		String escaped = unescaped.replaceAll("]]>", "]]]]><![CDATA[>");
		return "<![CDATA[" + escaped + "]]>";
	}

	/**
	 * unicode转中文
	 */
	public static String decodeUnicode(String theString) {
		char aChar;
		int len = theString.length();
		StringBuffer outBuffer = new StringBuffer(len);
		for (int x = 0; x < len;) {
			aChar = theString.charAt(x++);
			if (aChar == '\\') {
				aChar = theString.charAt(x++);
				if (aChar == 'u') {
					int value = 0;
					for (int i = 0; i < 4; i++) {
						aChar = theString.charAt(x++);
						switch (aChar) {
						case '0':
						case '1':
						case '2':
						case '3':
						case '4':
						case '5':
						case '6':
						case '7':
						case '8':
						case '9':
							value = (value << 4) + aChar - '0';
							break;
						case 'a':
						case 'b':
						case 'c':
						case 'd':
						case 'e':
						case 'f':
							value = (value << 4) + 10 + aChar - 'a';
							break;
						case 'A':
						case 'B':
						case 'C':
						case 'D':
						case 'E':
						case 'F':
							value = (value << 4) + 10 + aChar - 'A';
							break;
						default:
							throw new IllegalArgumentException(
									"Malformed      encoding.");
						}
					}
					outBuffer.append((char) value);
				} else {
					if (aChar == 't') {
						aChar = '\t';
					} else if (aChar == 'r') {
						aChar = '\r';
					} else if (aChar == 'n') {
						aChar = '\n';
					} else if (aChar == 'f') {
						aChar = '\f';
					}
					outBuffer.append(aChar);
				}
			} else {
				outBuffer.append(aChar);
			}
		}
		return outBuffer.toString();
	}

	/**
	 * 判断是否为正整数
	 * 
	 * @param str
	 *            传入的字符串
	 * @return 是正整数返回true,否则返回false
	 */
	public static boolean isPositiveInteger(String str) {
		boolean isPostiveInteger = false;
		if (!isEmpty(str)) {// 判断str不是（null或者""
			String strtrim = StringUtils.trimLeadingWhitespace(str);// 清除str前面的不可见字符
			Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");// 判断str是否为整数
			if (pattern.matcher(strtrim).matches()) {
				if (Integer.parseInt(strtrim) > 0) {
					isPostiveInteger = true;
				}
			} else {
				isPostiveInteger = false;
			}
		}
		return isPostiveInteger;
	}

	/**
	 * 把一个字符串中的小写转换为大写
	 * 
	 * @param str
	 * @return
	 */
	public static String exChange(String str) {
		StringBuffer sb = new StringBuffer();
		if (str != null) {
			for (int i = 0; i < str.length(); i++) {
				char c = str.charAt(i);
				if (Character.isLowerCase(c)) {
					sb.append(Character.toUpperCase(c));
				} else {
					sb.append(c);
				}
			}
		}
		return sb.toString();
	}
}
