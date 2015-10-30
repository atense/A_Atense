/*
 *  TestActivity.java
 *
 *  Created by admin on 2015年6月23日.
 *  Copyright (c) 2015 Atense. All rights reserved.
 */
package com.atense.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.atense.R;

/**
 * 
 * @author Admin
 */
public class DialogActivity extends Activity implements OnClickListener {

	final int MAX_PROGRESS = 100;

	int[] data = new int[50];
	int progressStatus = 0;
	int hasData = 0;
	ProgressDialog pd1, pd2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog);

		findViewById(R.id.d1).setOnClickListener(this);
		findViewById(R.id.d2).setOnClickListener(this);
		findViewById(R.id.d3).setOnClickListener(this);
	}

	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			if (msg.what == 0x123) {
				pd2.setProgress(progressStatus);
			}
		}
	};

	public void showSpinner() {
		ProgressDialog.show(this, "执行中的任务", "任务执行中，请等待", false, true);
	}

	public void showProgress() {
		pd2 = new ProgressDialog(this);
		// pd2.setTitle(title);
		pd2.setMessage("任务执行中，请等待");
		// 设置进度条风格
		pd2.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		// 进度条是否显示进度（进度内滚动的效果）
		pd2.setIndeterminate(true);
		// 设置对话框是否可以删除（后退或者点击非对话框区域）
		pd2.setCancelable(true);
		// pd2.setOnCancelListener(cancelListener);
		pd2.show();
	}

	private void showDialog() {
		final AlertDialog.Builder b = new Builder(this);
		b.setTitle("Dialog");
		b.setIcon(R.drawable.home_class);
		b.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}

		});
		b.show();
	}

	private void showToast() {
		Toast.makeText(this, "Toast", Toast.LENGTH_LONG).show();
	}

	@Override
	protected void onResume() {
		Log.e("dd", "onResume");
		super.onResume();
	}

	@Override
	protected void onPause() {
		Log.e("dd", "onPause");
		super.onPause();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.d1:
			showSpinner();
			break;
		case R.id.d2:
			showProgress();
			break;
		case R.id.d3:
			// showDialog();
			showToast();
			break;
		}

	}
}
