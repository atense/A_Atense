/*
 *  MusicService.java
 *
 *  Created by admin on 2015年9月30日.
 *  Copyright (c) 2015 Atense. All rights reserved.
 */
package com.atense.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.IBinder;

import com.atense.activity.MusicBoxActivity;

/**
 * 
 * @author Admin
 */
public class MusicService extends Service {

	MyReceiver serviceReceiver;
	MediaPlayer mPlayer;
	AssetManager am;
	String[] musics = new String[] { "dengniaiwo.mp3", "buyaoshuohua.mp3",
			"haojiubujian.mp3" };

	// 定义音乐的播放状态：0x11-没有播放，0x12-正在播放，0x13-暂停
	int status = 0x11;
	int current = 0;

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		am = getAssets();
		serviceReceiver = new MyReceiver();

		IntentFilter filter = new IntentFilter(MusicBoxActivity.CTL_ACTION);

		registerReceiver(serviceReceiver, filter);

		mPlayer = new MediaPlayer();
		mPlayer.setOnCompletionListener(new OnCompletionListener() {
			public void onCompletion(MediaPlayer mp) {
				current++;
				if (current >= 3) {
					current = 0;
				}

				Intent intent = new Intent(MusicBoxActivity.UPDATE_ACTION);
				intent.putExtra("current", current);
				sendBroadcast(intent);
				prepareAndPlay(musics[current]);
			}
		});
		super.onCreate();
	}

	private void prepareAndPlay(String fileName) {
		try {
			AssetFileDescriptor afd = am.openFd(fileName);
			mPlayer.reset();
			mPlayer.setDataSource(afd.getFileDescriptor());
			mPlayer.prepare();
			mPlayer.start();
		} catch (Exception e) {

		}
	}

	public class MyReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			int control = intent.getIntExtra("control", -1);

			switch (control) {
			case 1:
				if (status == 0x11) {
					prepareAndPlay(musics[current]);
					status = 0x12;
				} else if (status == 0x12) {
					mPlayer.pause();
					status = 0x13;
				} else if (status == 0x13) {
					mPlayer.start();
					status = 0x11;
				}
				break;
			case 2:
				if (status == 0x12 || status == 0x13) {
					mPlayer.stop();
					status = 0x11;
				}
				break;
			}

			Intent sendIntent = new Intent(MusicBoxActivity.UPDATE_ACTION);
			sendIntent.putExtra("update", status);
			sendIntent.putExtra("current", current);
			sendBroadcast(sendIntent);
		}
	}

}
