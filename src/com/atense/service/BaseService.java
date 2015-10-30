/*
 *  BaseService.java
 *
 *  Created by admin on 2015年9月29日.
 *  Copyright (c) 2015 Atense. All rights reserved.
 */
package com.atense.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

/**
 * 调用startService()创建的service必须调用stopService()才能销毁<br/>
 * 调用bindService()创建的service必须调用unbindService()才能销毁<br/>
 * 
 * @author Admin
 */
public class BaseService extends Service {

	private final String TAG = "BaseService";
	private int count;
	private boolean quit;
	private BaseBinder binder = new BaseBinder();

	public class BaseBinder extends Binder {

		public int getCount() {
			return count;
		}

	}

	@Override
	public IBinder onBind(Intent intent) {
		Log.e(TAG, "Service is binded");
		return binder;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		Log.e(TAG, "Service is created");
		new Thread() {
			public void run() {
				while (!quit) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {

					}
					count++;
				}
			}
		}.start();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.e(TAG, "Service is started");
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy() {
		Log.e(TAG, "Service is destroyed");
		quit = true;
		super.onDestroy();
	}

	@Override
	public boolean onUnbind(Intent intent) {
		Log.e(TAG, "Service is unbinded");
		return true; // 为true时下次bindSerive()时会调用onRebind()方法
	}

	@Override
	public void onRebind(Intent intent) {
		Log.e(TAG, "Service is rebinded");
		super.onRebind(intent);
	}

}
