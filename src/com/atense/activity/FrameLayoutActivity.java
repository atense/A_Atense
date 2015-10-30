/*
 *  TestActivity.java
 *
 *  Created by admin on 2015年6月23日.
 *  Copyright (c) 2015 Atense. All rights reserved.
 */
package com.atense.activity;

import java.util.Timer;
import java.util.TimerTask;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;
import com.atense.R;

/**
 * 
 * @author Admin
 */
public class FrameLayoutActivity extends Activity {
	
	private int currentColor = 1;
	private int[] colors = new int[]{
			R.color.color1,
			R.color.color2,
			R.color.color3,
			R.color.color4,
			R.color.color5,
			R.color.color6
	};
	
	private int[] names = new int[] {
			R.id.view1,
			R.id.view2,
			R.id.view3,
			R.id.view4,
			R.id.view5,
			R.id.view6
	};
	
	private TextView[] tvs = new TextView[names.length];
	private Timer timer;
	private TimerTask timerTask;
	
	@SuppressLint("HandlerLeak")
	private Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			if(msg.what == 0x123) {
				for(int idx = 0; idx < tvs.length; idx++) {
					tvs[idx].setBackgroundColor(getResources().getColor(colors[(idx + currentColor) % colors.length])); 
				}
				currentColor++;
			}
			super.handleMessage(msg);
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.framelayout);
		
		for(int idx = 0; idx < names.length; idx++) {
			tvs[idx] = (TextView) this.findViewById(names[idx]);
		}
	}
	
	@Override
	protected void onResume() {
		super.onResume();

		timer = new Timer();
		timerTask = new TimerTask(){
			@Override
			public void run() {
				handler.sendEmptyMessage(0x123);
			}
		};
		timer.schedule(timerTask, 0, 200);
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		if(timerTask != null) {
			timerTask = null;
		}
		if(timer != null) {
			timer.purge();
			timer = null;
		}
	}
	
	@Override
	protected void onStop() {
		super.onStop();
	}

}
