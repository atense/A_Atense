/*
 *  TestActivity.java
 *
 *  Created by admin on 2015年6月23日.
 *  Copyright (c) 2015 Atense. All rights reserved.
 */
package com.atense.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.ViewGroup.LayoutParams;

import com.atense.widget.PlaneView;

/**
 * 
 * @author Admin
 */
public class PlaneViewActivity extends Activity {

	PlaneView pv;
	int speed = 10;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		pv = new PlaneView(this);
		pv.setBackgroundColor(Color.WHITE);
		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT);
		pv.setLayoutParams(params);
		setContentView(pv);
		DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);
		pv.x = metrics.widthPixels / 2;
		pv.y = metrics.heightPixels - 40;

		pv.setOnKeyListener(new OnKeyListener() {

			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				switch (event.getKeyCode()) {
				case KeyEvent.KEYCODE_S:
					pv.y += speed;
					break;
				case KeyEvent.KEYCODE_W:
					pv.y -= speed;
					break;
				case KeyEvent.KEYCODE_A:
					pv.x -= speed;
					break;
				case KeyEvent.KEYCODE_D:
					pv.y += speed;
					break;
				}
				pv.invalidate();
				return true;
			}
		});
	}
}
