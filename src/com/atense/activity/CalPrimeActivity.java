/*
 *  TestActivity.java
 *
 *  Created by admin on 2015年6月23日.
 *  Copyright (c) 2015 Atense. All rights reserved.
 */
package com.atense.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.graphics.drawable.ClipDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.atense.R;

/**
 * 
 * @author Admin
 */
public class CalPrimeActivity extends Activity {

	EditText edit;
	Button btn;
	ImageView image;
	ClipDrawable clip;
	CalThread calThread;

	class CalThread extends Thread {
		public Handler handler;

		@Override
		public void run() {
			Looper.prepare();
			handler = new Handler() {
				@Override
				public void handleMessage(Message msg) {
					if (msg.what == 0x123) {
						int upper = msg.getData().getInt("Upper");
						List<Integer> nums = new ArrayList<Integer>();
						outer: for (int i = 2; i < upper; i++) {
							for (int j = 2; j < Math.sqrt(i); j++) {
								if (i != 2 && i % j == 0) {
									continue outer;
								}
							}
							nums.add(i);
						}
						Toast.makeText(CalPrimeActivity.this, nums.toString(),
								Toast.LENGTH_LONG).show();
					}
				}
			};
			Looper.loop();
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.calprime);

		btn = (Button) findViewById(R.id.button);
		edit = (EditText) findViewById(R.id.edit);
		btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				cal();
			}
		});

		image = (ImageView) findViewById(R.id.image);
		clip = (ClipDrawable) image.getDrawable();

		calThread = new CalThread();
		calThread.start();
	}

	protected void onResume() {
		super.onResume();

		ObjectAnimator objAnim = (ObjectAnimator) AnimatorInflater
				.loadAnimator(this, R.animator.color_anim);
		objAnim.setTarget(image);
		objAnim.start();

		timer = new Timer();
		task = new TimerTask() {
			@Override
			public void run() {
				handler.sendEmptyMessage(0x123);
				if (clip.getLevel() > 10000) {
					timer.cancel();
				}
			}
		};
		timer.schedule(task, 0, 300);
	}

	Timer timer;
	TimerTask task;
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			if (msg.what == 0x123) {
				clip.setLevel(clip.getLevel() + 200);
			}
		};
	};

	public void cal() {
		Message msg = new Message();
		msg.what = 0x123;
		Bundle data = new Bundle();
		data.putInt("Upper", Integer.parseInt(edit.getText().toString()));
		msg.setData(data);
		calThread.handler.sendMessage(msg);
	}
}
