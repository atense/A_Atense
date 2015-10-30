/*
 *  TestActivity.java
 *
 *  Created by admin on 2015年6月23日.
 *  Copyright (c) 2015 Atense. All rights reserved.
 */
package com.atense.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.atense.R;

/**
 * 
 * @author Admin
 */
public class Test3Activity extends Activity {

	private final String TAG = "Test3Activity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.e(TAG, "onCreate");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.custom);
	}

	@Override
	protected void onStart() {
		Log.e(TAG, "onStart");
		super.onStart();
	}

	@Override
	protected void onResume() {
		Log.e(TAG, "onResume");
		super.onResume();
	}

	@Override
	protected void onPause() {
		Log.e(TAG, "onPause");
		super.onPause();
	}

	protected void onStop() {
		Log.e(TAG, "onStop");
		super.onStop();
	};

	@Override
	protected void onRestart() {
		Log.e(TAG, "onRestart");
		super.onRestart();
	}

	@Override
	protected void onDestroy() {
		Log.e(TAG, "onDestroy");
		super.onDestroy();
	}
}
