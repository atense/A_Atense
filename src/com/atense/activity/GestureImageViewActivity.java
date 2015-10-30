/*
 *  TestActivity.java
 *
 *  Created by admin on 2015年6月23日.
 *  Copyright (c) 2015 Atense. All rights reserved.
 */
package com.atense.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.widget.ImageView;

import com.atense.R;

/**
 * 
 * @author Admin
 */
public class GestureImageViewActivity extends Activity implements
		OnGestureListener {

	private ImageView image;
	GestureDetector detector;
	Bitmap bitmap;
	int width, height;
	Matrix matrix;
	float curScale = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gesture_image_view);

		detector = new GestureDetector(this, this);
		image = (ImageView) findViewById(R.id.image);

		matrix = new Matrix();

		bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.s1);
		width = bitmap.getWidth();
		height = bitmap.getHeight();

		image.setImageBitmap(BitmapFactory.decodeResource(getResources(),
				R.drawable.s1));

	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		return detector.onTouchEvent(event);
	}

	@Override
	public boolean onDown(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onLongPress(MotionEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		velocityX = velocityX > 4000 ? 4000 : velocityX;
		velocityX = velocityX < -4000 ? -4000 : velocityX;
		Log.e("onFling", velocityX + "");

		curScale = curScale + curScale * velocityX / 4000.0f;
		curScale = curScale > 0.01 ? curScale : 0.01f;

		matrix.reset();

		Log.e("curScale", curScale + "");
		matrix.setScale(curScale, curScale, width / 2, height / 2);

		BitmapDrawable tmp = (BitmapDrawable) image.getDrawable();
		if (!tmp.getBitmap().isRecycled()) {
			tmp.getBitmap().recycle();
		}

		Bitmap b = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix,
				true);

		image.setImageBitmap(b);

		return true;
	}

}
