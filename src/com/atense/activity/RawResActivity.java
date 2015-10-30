/*
 *  TestActivity.java
 *
 *  Created by admin on 2015年6月23日.
 *  Copyright (c) 2015 Atense. All rights reserved.
 */
package com.atense.activity;

import java.io.IOException;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.atense.R;

/**
 * 
 * @author Admin
 */
public class RawResActivity extends Activity implements OnClickListener {

	private MediaPlayer mp1, mp2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.raw_res);

		mp1 = MediaPlayer.create(this, R.raw.dengniaiwo);

		try {
			AssetFileDescriptor is = getResources().getAssets().openFd(
					"buyaoshuohua.mp3");
			mp2 = new MediaPlayer();
			mp2.setDataSource(is.getFileDescriptor());
			is.close();
			mp2.prepare();
		} catch (IOException e) {
			e.printStackTrace();
		}

		findViewById(R.id.raw).setOnClickListener(this);
		findViewById(R.id.res).setOnClickListener(this);
		findViewById(R.id.raw_t).setOnClickListener(this);
		findViewById(R.id.res_t).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.raw) {
			mp1.start();
		} else if (v.getId() == R.id.res) {
			mp2.start();
		} else if (v.getId() == R.id.raw_t) {
			mp1.stop();
			mp1.release();
		} else if (v.getId() == R.id.res_t) {
			mp2.stop();
			mp2.release();
		}
	}
}
