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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.PopupWindow;

import com.atense.R;

/**
 * 
 * @author Admin
 */
public class Test1Activity extends Activity implements OnClickListener {

	private final String TAG = "Test1Activity";
	PopupWindow popup;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.e(TAG, "onCreate");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test1);
		popup = new PopupWindow(LayoutInflater.from(this).inflate(
				R.layout.simple_item, null), 200, 200);

		findViewById(R.id.test1).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent(this, Test2Activity.class);
		startActivity(intent);
		// popup.showAtLocation(findViewById(R.id.test1), Gravity.CENTER, 20,
		// 20);
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
