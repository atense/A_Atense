/*
 *  MyReceiver.java
 *
 *  Created by admin on 2015年9月30日.
 *  Copyright (c) 2015 Atense. All rights reserved.
 */
package com.atense.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

/**
 * 
 * @author Admin
 */
public class MyReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		StringBuilder sb = new StringBuilder();
		sb.append("收到的Intent的Action为" + intent.getAction());
		sb.append("\n");
		sb.append("消息内容是" + intent.getStringExtra("msg"));
		Toast.makeText(context, sb.toString(), Toast.LENGTH_LONG).show();

		Bundle bundle = new Bundle();
		bundle.putString("first", "第一个BroadcastReceiver存入的消息");
		setResultExtras(bundle);
		// 取消broadcast继续传播
		// abortBroadcast();
	}

}
