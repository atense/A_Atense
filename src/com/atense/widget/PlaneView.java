/*
 *  DrawView.java
 *
 *  Created by admin on 2015年9月11日.
 *  Copyright (c) 2015 Atense. All rights reserved.
 */
package com.atense.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

import com.atense.R;

/**
 * 
 * @author Admin
 */
public class PlaneView extends View {

	public float x = 100, y = 100;
	private Paint p = new Paint();
	private Bitmap bitmap;

	/**
	 * @param context
	 */
	public PlaneView(Context context) {
		super(context);
		bitmap = BitmapFactory.decodeResource(getResources(),
				R.drawable.home_me);
		// setFocusable(true);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawBitmap(bitmap, x, y, p);
	}

}
