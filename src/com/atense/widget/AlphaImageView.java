/*
 *  DrawView.java
 *
 *  Created by admin on 2015年9月11日.
 *  Copyright (c) 2015 Atense. All rights reserved.
 */
package com.atense.widget;

import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

import com.atense.R;

/**
 * 
 * @author Admin
 */
public class AlphaImageView extends ImageView {

	private int curAlpha = 0;
	private int alphaDelta = 0;
	private final int SPEED = 300;
	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			if (msg.what == 0x123) {
				Log.e("AlphaImageView", (float) curAlpha / 255 + "");
				AlphaImageView.this.setAlpha((float) curAlpha / 255);
			}
		}
	};

	/**
	 * @param context
	 */
	public AlphaImageView(Context context) {
		super(context);
	}

	public AlphaImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		TypedArray typedArray = context.obtainStyledAttributes(attrs,
				R.styleable.AlphaImageView);
		int duration = typedArray
				.getInt(R.styleable.AlphaImageView_duration, 0);

		alphaDelta = 255 * SPEED / duration;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		this.setAlpha((float) curAlpha / 255);
		super.onDraw(canvas);
		final Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				curAlpha += alphaDelta;
				if (curAlpha > 255) {
					timer.cancel();
				} else {
					handler.sendEmptyMessage(0x123);
				}
			}
		}, 0, SPEED);
	}

}
