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
public class MyReceiver2 extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		Bundle bundle = getResultExtras(true);
		String first = bundle.getString("first");
		Toast.makeText(context, "第一个broadcast存入的消息为" + first, Toast.LENGTH_LONG)
				.show();
	}

}
