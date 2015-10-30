/*
 *  TestActivity.java
 *
 *  Created by admin on 2015年6月23日.
 *  Copyright (c) 2015 Atense. All rights reserved.
 */
package com.atense.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

import com.atense.R;
import com.atense.service.MusicService;

/**
 * 
 * @author Admin
 */
public class MusicBoxActivity extends Activity implements OnClickListener {

	ImageButton play, stop;
	TextView title, author;

	ActivityReceiver activityReceiver;
	// 定义音乐的播放状态：0x11-没有播放，0x12-正在播放，0x13-暂停
	int status = 0x11;
	public static final String CTL_ACTION = "com.atense.action.CTL_ACTION";
	public static final String UPDATE_ACTION = "com.atense.action.UPDATE_ACTION";

	String[] titles = new String[] { "等你爱我", "不要说话", "好久不见" };
	String[] authors = new String[] { "陈奕迅", "陈奕迅", "陈奕迅" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.music_box);

		play = (ImageButton) findViewById(R.id.play);
		stop = (ImageButton) findViewById(R.id.stop);

		title = (TextView) findViewById(R.id.title);
		author = (TextView) findViewById(R.id.author);

		play.setOnClickListener(this);
		stop.setOnClickListener(this);

		activityReceiver = new ActivityReceiver();
		IntentFilter filter = new IntentFilter();
		filter.addAction(UPDATE_ACTION);

		registerReceiver(activityReceiver, filter);

		Intent intent = new Intent(this, MusicService.class);
		startService(intent);
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent(CTL_ACTION);
		switch (v.getId()) {
		case R.id.play:
			intent.putExtra("control", 1);
			break;
		case R.id.stop:
			intent.putExtra("control", 2);
			break;
		}
		sendBroadcast(intent);
	}

	public class ActivityReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			int update = intent.getIntExtra("update", -1);
			int current = intent.getIntExtra("current", -1);

			if (current >= 0) {
				title.setText(titles[current]);
				author.setText(authors[current]);
			}

			switch (update) {
			case 0x11:
				play.setImageResource(R.drawable.play);
				status = 0x11;
				break;
			case 0x12:
				play.setImageResource(R.drawable.pause);
				status = 0x12;
				break;
			case 0x13:
				play.setImageResource(R.drawable.play);
				status = 0x13;
				break;
			}
		}
	}

}
