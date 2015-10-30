/*
 *  DrawingBoard.java
 *
 *  Created by admin on 2015年9月17日.
 *  Copyright (c) 2015 Atense. All rights reserved.
 */
package com.atense.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * 
 * @author Admin
 */
public class DrawingBoard extends View {

	Bitmap cahceBitmap = null;
	Canvas cacheCanvas = null;
	Paint paint = null;
	Path path = null;
	float preX;
	float preY;
	final int VIEW_WIDTH = 320;
	final int VIEW_HEIGHT = 480;

	public DrawingBoard(Context context) {
		super(context);
		init();
	}

	public DrawingBoard(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	private void init() {
		cahceBitmap = Bitmap.createBitmap(VIEW_WIDTH, VIEW_HEIGHT,
				Config.ARGB_8888);
		cacheCanvas = new Canvas(cahceBitmap);
		path = new Path();
		paint = new Paint(Paint.DITHER_FLAG);
		paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeWidth(1);

		paint.setAntiAlias(true);
		paint.setDither(true);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		Paint p = new Paint();
		canvas.drawBitmap(cahceBitmap, 0, 0, p);
		canvas.drawPath(path, paint);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		float x = event.getX();
		float y = event.getY();
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			path.moveTo(x, y);
			preX = x;
			preY = y;
			break;
		case MotionEvent.ACTION_MOVE:
			path.quadTo(preX, preY, x, y);
			preX = x;
			preY = y;
			break;
		case MotionEvent.ACTION_UP:
			cacheCanvas.drawPath(path, paint);
			path.reset();
			break;
		}
		invalidate();
		return true;
	}

}
