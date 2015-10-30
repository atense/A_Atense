/*
 *  TestActivity.java
 *
 *  Created by admin on 2015年6月23日.
 *  Copyright (c) 2015 Atense. All rights reserved.
 */
package com.atense.activity;

import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.atense.R;
import com.atense.service.BaseService;
import com.atense.service.MyIntentService;
import com.atense.service.MyService;

/**
 * 
 * @author Admin
 */
public class ServiceActivity extends Activity implements OnClickListener {

	Button start, stop, bind, unbind, sericeStatus, startService,
			startIntentService;
	Intent intent;
	ServiceConnection conn;
	BaseService.BaseBinder binder;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.service);

		start = (Button) findViewById(R.id.startservice);
		stop = (Button) findViewById(R.id.stopservice);
		bind = (Button) findViewById(R.id.bindservice);
		unbind = (Button) findViewById(R.id.unbindservice);
		sericeStatus = (Button) findViewById(R.id.getserverstatus);
		startIntentService = (Button) findViewById(R.id.startintentservice);
		startService = (Button) findViewById(R.id.startservice1);

		intent = new Intent();
		intent.setAction("com.atense.service.BASE_SERVICE");

		conn = new ServiceConnection() {

			@Override
			public void onServiceDisconnected(ComponentName name) {
			}

			@Override
			public void onServiceConnected(ComponentName name, IBinder service) {
				Log.e("ServiceConnection->onServiceConnected", "ComponentName="
						+ name.getPackageName() + "-" + name.getClassName()
						+ "-" + name.getShortClassName());
				binder = (BaseService.BaseBinder) service;
			}
		};

		start.setOnClickListener(this);
		stop.setOnClickListener(this);
		bind.setOnClickListener(this);
		unbind.setOnClickListener(this);
		sericeStatus.setOnClickListener(this);
		startIntentService.setOnClickListener(this);
		startService.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.startservice) {
			startService(intent);
		} else if (v.getId() == R.id.stopservice) {
			stopService(intent);
		} else if (v.getId() == R.id.bindservice) {
			bindService(intent, conn, Service.BIND_AUTO_CREATE);
		} else if (v.getId() == R.id.unbindservice) {
			unbindService(conn);
		} else if (v.getId() == R.id.getserverstatus) {
			Toast.makeText(ServiceActivity.this,
					"Service count=" + binder.getCount(), Toast.LENGTH_LONG)
					.show();
		} else if (v.getId() == R.id.startintentservice) {
			startService(new Intent(this, MyIntentService.class));
		} else if (v.getId() == R.id.startservice1) {
			startService(new Intent(this, MyService.class));
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

}
