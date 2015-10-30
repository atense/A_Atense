/*
 *  TestActivity.java
 *
 *  Created by admin on 2015年6月23日.
 *  Copyright (c) 2015 Atense. All rights reserved.
 */
package com.atense.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Chronometer.OnChronometerTickListener;

import com.atense.R;

/**
 * 
 * @author Admin
 */
public class ChronometerActivity extends Activity {

	private Chronometer chr;
	private Button start;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.chronometer);

		chr = (Chronometer) findViewById(R.id.chronometer);
		chr.setOnChronometerTickListener(new OnChronometerTickListener() {

			@Override
			public void onChronometerTick(Chronometer chronometer) {
				if (SystemClock.elapsedRealtime() - chronometer.getBase() > 20 * 1000) {
					chr.stop();
				}
			}
		});

		start = (Button) findViewById(R.id.start);
		start.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				chr.setBase(SystemClock.elapsedRealtime());
				chr.start();
				start.setEnabled(false);
			}
		});
	}

}
