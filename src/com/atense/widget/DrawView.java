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
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.atense.R;

/**
 * 
 * @author Admin
 */
public class DrawView extends View {

	private float x = 40, y = 50;
	private Paint p = new Paint();
	private Bitmap bitmap;

	/**
	 * @param context
	 */
	public DrawView(Context context) {
		super(context);
		bitmap = BitmapFactory.decodeResource(getResources(),
				R.drawable.home_me);
	}

	public DrawView(Context context, AttributeSet set) {
		super(context, set);
		bitmap = BitmapFactory.decodeResource(getResources(),
				R.drawable.home_me);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// p.setColor(Color.RED);
		// canvas.drawCircle(x, y, 15, p);
		canvas.drawBitmap(bitmap, x, y, p);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		x = event.getX();
		y = event.getY();
		invalidate();
		return true;
	}

}
