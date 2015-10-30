/*
 *  BaseService.java
 *
 *  Created by admin on 2015年9月29日.
 *  Copyright (c) 2015 Atense. All rights reserved.
 */
package com.atense.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

/**
 * @author Admin
 */
public class MyService extends Service {

	private final String TAG = "BaseService";

	@Override
	public IBinder onBind(Intent intent) {
		Log.e(TAG, "Service is binded");
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		Log.e(TAG, "Service is created");
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.e(TAG, "Service is started");
		long endTime = System.currentTimeMillis() + 20 * 1000;
		while (System.currentTimeMillis() < endTime) {
			synchronized (this) {
				try {
					wait(endTime - System.currentTimeMillis());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		Log.e(TAG, "Service 耗时任务执行完成");
		return START_STICKY;
	}

	@Override
	public void onDestroy() {
		Log.e(TAG, "Service is destroyed");
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
