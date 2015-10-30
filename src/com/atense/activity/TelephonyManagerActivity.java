/*
 *  TestActivity.java
 *
 *  Created by admin on 2015年6月23日.
 *  Copyright (c) 2015 Atense. All rights reserved.
 */
package com.atense.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.widget.TextView;

/**
 * 
 * @author Admin
 */
public class TelephonyManagerActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		TextView tv = new TextView(this);
		setContentView(tv);
		TelephonyManager tManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		StringBuilder text = new StringBuilder();
		text.append("\n 设备编号=" + tManager.getDeviceId());
		text.append("\n 系统平台版本=" + tManager.getDeviceSoftwareVersion() != null ? tManager
				.getDeviceSoftwareVersion() : "未知");
		text.append("\n 网络运营商代号=" + tManager.getNetworkOperator());
		text.append("\n 网络运营商名称=" + tManager.getNetworkOperatorName());
		text.append("\n 手机网络类型=" + tManager.getPhoneType());
		text.append("\n 设备所在位置=" + tManager.getCellLocation() != null ? tManager
				.getCellLocation() : "未知位置");
		text.append("\n SIM卡的国别=" + tManager.getSimCountryIso());
		text.append("\n SIM卡的序列号=" + tManager.getSimSerialNumber());
		text.append("\n SIM卡的状态=" + tManager.getSimState());
		tv.setText(text);
	}

}
