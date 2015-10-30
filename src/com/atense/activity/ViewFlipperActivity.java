/*
 *  TestActivity.java
 *
 *  Created by admin on 2015年6月23日.
 *  Copyright (c) 2015 Atense. All rights reserved.
 */
package com.atense.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.ViewFlipper;

import com.atense.R;

/**
 * 
 * @author Admin
 */
public class ViewFlipperActivity extends Activity implements OnGestureListener {

	GestureDetector detector;

	ViewFlipper flipper;

	final int FLIP_DISTANCE = 50;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_flipper);

		detector = new GestureDetector(this, this);

		flipper = (ViewFlipper) findViewById(R.id.flipper);
		flipper.addView(addView(R.drawable.home_class));
		flipper.addView(addView(R.drawable.home_food));
		flipper.addView(addView(R.drawable.home_me));
		flipper.addView(addView(R.drawable.home_navi));
		flipper.addView(addView(R.drawable.home_near));

	}

	private ImageView addView(int resId) {
		ImageView iv = new ImageView(ViewFlipperActivity.this);
		iv.setImageResource(resId);
		iv.setScaleType(ScaleType.FIT_XY);
		iv.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT));
		return iv;
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
		// <---
		if (e1.getX() - e2.getX() > FLIP_DISTANCE) {
			flipper.setInAnimation(this, R.anim.slide_in_right);
			flipper.setOutAnimation(this, R.anim.slide_out_left);
			flipper.showNext();
			return true;
		} else if (e2.getX() - e1.getX() > FLIP_DISTANCE) {
			flipper.setInAnimation(this, R.anim.slide_in_left);
			flipper.setOutAnimation(this, R.anim.slide_out_right);
			flipper.showPrevious();
			return true;
		}
		return false;
	}

}
