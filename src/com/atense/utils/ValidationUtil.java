package com.atense.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 验证工具类
 * 
 * @author <a href="#" target="_blank">Kyle</a> 2015-11-04
 */
public class ValidationUtil {
	/*** 代表对手机号码的格式进行验证 ***/
	public final static int TELEPHONE = 0;
	/*** 代表对电话号码的格式进行验证 ***/
	public final static int PHONE = 1;
	/*** 代表对传真号码的格式进行验证 ***/
	public final static int FAX = 2;
	/*** 代表对电子邮箱的格式进行验证 ***/
	public final static int EMAIL = 3;
	/*** 代表对QQ号码的格式进行验证 ***/
	public final static int QQ = 4;
	/*** 代表对微信号码的格式进行验证 ***/
	public final static int WEIXIN = 5;
	/*** 代表对网站网址的格式进行验证 ***/
	public final static int WEBURL = 6;
	/*** 代表对身份证格式进行验证 ***/
	public final static int ID_CARD = 7;
	
	/**
	 * 正则表达式验证用户输入的信息格式是否正确
	 * 
	 * @param input
	 *            用户输入的信息
	 * @param regex
	 *            正则表达式
	 * @return boolean类型的验证结果
	 */
	public static Boolean validate(String input, String regex) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);
		return matcher.find();
	}

	/**
	 * 根据输入的的内容验证输入是否合法
	 * 
	 * @param input
	 *            用户输入的信息
	 * @param inputType
	 *            可选择的验证类型
	 * @return boolean类型的验证结果
	 */
	public static Boolean validate(String input, int inputType) {
		boolean flag = false;
		switch (inputType) {
		case TELEPHONE:
			// flag = validate(input,
			// "^[0]{1}[0-9]{2,3}[0-9]{7,8}$");//固定电话
			flag = validate(input, "[\\d\\-\\(\\)]*");// 只允许输入数字与'-'
			break;
		case PHONE:
			flag = validate(
					input,
					"^\\d{11}$|^((\\d{7,8})|(\\d{4}|\\d{3})-(\\d{7,8})|(\\d{4}|\\d{3})-(\\d{7,8})-(\\d{4}|\\d{3}|\\d{2}|\\d{1})|(\\d{7,8})-(\\d{4}|\\d{3}|\\d{2}|\\d{1}))$");// 手机号码和固话
			break;
		case FAX:
			flag = validate(input, "^[0]{1}[0-9]{2,3}[0-9]{7,8}$");// 传真号码
			break;
		case EMAIL:
			flag = validate(input,
					"^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$");// "^([a-zA-Z0-9]*[-_.]?[a-zA-Z0-9]+)*@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{2})?$");//邮箱
			break;
		case QQ:
			flag = validate(input, "^[1-9][0-9]{4,15}$");// QQ号码
			break;
		case WEIXIN:
			flag = validate(input, "^[a-zA-Z]\\w{5,19}$");// 微信号码
			break;
		case WEBURL:
			flag = validate(input, "^([\\w-]+\\.)+[\\w-]+(/[\\w-./?%&=]*)?$");// 网站网址
			break;
		case ID_CARD:
			flag = validate(input, "^(([0-9]{17}(x|X))|([0-9]{15})|([0-9]{18}))$");
		default:
			break;
		}
		return flag;
	}

}
