/*
 *  TestActivity.java
 *
 *  Created by admin on 2015年6月23日.
 *  Copyright (c) 2015 Atense. All rights reserved.
 */
package com.atense.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.StackView;

import com.atense.R;

/**
 * 
 * @author Admin
 */
public class StackViewActivity extends Activity implements OnClickListener {

	StackView stack;
	Button prev, next;

	private int[] imageIds = { R.drawable.home_class, R.drawable.home_food,
			R.drawable.home_me, R.drawable.home_navi, R.drawable.home_near };

	BaseAdapter adapter = new BaseAdapter() {

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ImageView iv = new ImageView(StackViewActivity.this);
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
		setContentView(R.layout.stackview);

		stack = (StackView) findViewById(R.id.stack);
		stack.setAdapter(adapter);
		prev = (Button) findViewById(R.id.prev);
		next = (Button) findViewById(R.id.next);
		prev.setOnClickListener(this);
		next.setOnClickListener(this);

	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.prev:
			stack.showPrevious();
			break;
		case R.id.next:
			stack.showNext();
			break;
		}

	}

}
