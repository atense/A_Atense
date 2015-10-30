/*
 *  BaseService.java
 *
 *  Created by admin on 2015年9月29日.
 *  Copyright (c) 2015 Atense. All rights reserved.
 */
package com.atense.service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

/**
 * @author Admin
 */
public class MyIntentService extends IntentService {

	public MyIntentService() {
		super("MyIntentService");
	}

	public MyIntentService(String name) {
		super(name);
	}

	private final String TAG = "BaseService";

	@Override
	protected void onHandleIntent(Intent intent) {
		Log.e(TAG, "Service onHandleIntent");
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
	}

}
