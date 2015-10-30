/*
 *  TestActivity.java
 *
 *  Created by admin on 2015年6月23日.
 *  Copyright (c) 2015 Atense. All rights reserved.
 */
package com.atense.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.atense.R;

/**
 * 
 * @author Admin
 */
public class BroadcastActivity extends Activity {

	Button send, sendOrder;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.broadcast);

		send = (Button) findViewById(R.id.send);
		sendOrder = (Button) findViewById(R.id.sendorder);

		send.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setAction("com.atense.broadcast.MY_RECEIVER");
				intent.putExtra("msg", "简单的消息");
				sendBroadcast(intent);
			}
		});
		sendOrder.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setAction("com.atense.broadcast.MY_RECEIVER");
				intent.putExtra("msg", "简单的消息");
				sendOrderedBroadcast(intent, null);
			}
		});
	}

}
