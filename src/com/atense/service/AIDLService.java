/*
 *  BaseService.java
 *
 *  Created by admin on 2015年9月29日.
 *  Copyright (c) 2015 Atense. All rights reserved.
 */
package com.atense.service;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

/**
 * 调用startService()创建的service必须调用stopService()才能销毁<br/>
 * 调用bindService()创建的service必须调用unbindService()才能销毁<br/>
 * 
 * @author Admin
 */
public class AIDLService extends Service {

	private final String TAG = "AIDLService";
	private CatBinder binder;

	private String[] colors = new String[] { "Red", "yellow", "black" };
	private double[] weights = new double[] { 2.3, 3.1, 1.58 };

	private String color;
	private double weight;

	Timer timer = new Timer();

	public class CatBinder extends ICat.Stub {
		@Override
		public String getColor() throws RemoteException {
			return color;
		}

		@Override
		public double getWeight() throws RemoteException {
			return weight;
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
		binder = new CatBinder();
		Log.e(TAG, "Service is created");
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				int index = (int) (Math.random() * 3);
				color = colors[index];
				weight = weights[index];
			}
		}, 0, 600);
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.e(TAG, "Service is started");
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy() {
		Log.e(TAG, "Service is destroyed");
		timer.cancel();
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
