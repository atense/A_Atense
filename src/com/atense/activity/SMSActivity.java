/*
 *  TestActivity.java
 *
 *  Created by admin on 2015年6月23日.
 *  Copyright (c) 2015 Atense. All rights reserved.
 */
package com.atense.activity;

import android.app.Activity;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.telephony.SmsManager;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.atense.R;

/**
 * 
 * @author Admin
 */
public class SMSActivity extends Activity {

	Button send;
	EditText number, content;
	Vibrator vibrator;

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		vibrator.vibrate(50);
		return super.onTouchEvent(event);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sms);

		vibrator = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);

		send = (Button) findViewById(R.id.send);

		number = (EditText) findViewById(R.id.number);
		content = (EditText) findViewById(R.id.content);

		send.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				String numberStr = number.getText().toString();
				String contentStr = content.getText().toString();

				SmsManager.getDefault().sendTextMessage(
						numberStr,
						null,
						contentStr,
						PendingIntent.getActivity(SMSActivity.this, 0,
								new Intent(), 0), null);
			}
		});
	}

}
