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
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterViewFlipper;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

import com.atense.R;

/**
 * 
 * @author Admin
 */
public class AdapterViewFlipperActivity extends Activity implements
		OnClickListener, OnGestureListener {

	GestureDetector detector;

	AdapterViewFlipper flipper;
	Button prev, next, autoPlay;

	final int FLIP_DISTANCE = 50;

	private int[] imageIds = { R.drawable.home_class, R.drawable.home_food,
			R.drawable.home_me, R.drawable.home_navi, R.drawable.home_near };

	BaseAdapter adapter = new BaseAdapter() {

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ImageView iv = new ImageView(AdapterViewFlipperActivity.this);
			iv.setImageResource(imageIds[position]);
			iv.setScaleType(ScaleType.FIT_XY);
			iv.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
					LayoutParams.MATCH_PARENT));
			return iv;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public Object getItem(int position) {
			return imageIds[position];
		}

		@Override
		public int getCount() {
			return imageIds.length;
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.adapterviewfilpper);

		detector = new GestureDetector(this, this);

		flipper = (AdapterViewFlipper) findViewById(R.id.flipper);
		flipper.setAdapter(adapter);
		prev = (Button) findViewById(R.id.prev);
		next = (Button) findViewById(R.id.next);
		autoPlay = (Button) findViewById(R.id.autoplay);
		prev.setOnClickListener(this);
		next.setOnClickListener(this);
		autoPlay.setOnClickListener(this);

	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		return detector.onTouchEvent(event);
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.prev:
			flipper.showPrevious();
			flipper.stopFlipping();
			break;
		case R.id.next:
			flipper.showNext();
			flipper.stopFlipping();
			break;
		case R.id.autoplay:
			flipper.startFlipping();
			break;
		}

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
