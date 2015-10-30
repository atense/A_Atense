/*
 *  TestActivity.java
 *
 *  Created by admin on 2015年6月23日.
 *  Copyright (c) 2015 Atense. All rights reserved.
 */
package com.atense.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewSwitcher;
import android.widget.ViewSwitcher.ViewFactory;

import com.atense.R;

/**
 * 
 * @author Admin
 */
public class ViewSwitcherActivity extends Activity implements OnClickListener {

	ViewSwitcher switcher;
	Button prev, next;
	final int SCREEN_NUM = 12;

	public static class DataItem {
		public String dataName;
		public Drawable drawable;
	}

	private List<DataItem> items = new ArrayList<DataItem>();
	private int screenNo = -1;
	private int screenCount;
	LayoutInflater inflater;

	private int[] imageIds = { R.drawable.home_class, R.drawable.home_food,
			R.drawable.home_me, R.drawable.home_navi, R.drawable.home_near };

	BaseAdapter adapter = new BaseAdapter() {

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View v = convertView;
			if (convertView == null) {
				v = inflater.inflate(R.layout.lableicon, null);
			}
			ImageView iv = (ImageView) v.findViewById(R.id.image);
			iv.setImageDrawable(getItem(position).drawable);
			TextView tv = (TextView) v.findViewById(R.id.text);
			tv.setText(getItem(position).dataName);

			return v;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public DataItem getItem(int position) {
			return items.get(screenNo * SCREEN_NUM + position);
		}

		@Override
		public int getCount() {
			if (screenNo == screenCount - 1 && items.size() % SCREEN_NUM != 0) {
				return items.size() % SCREEN_NUM;
			}

			return SCREEN_NUM;
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viewswitcher);

		for (int i = 0; i < 40; i++) {
			DataItem item = new DataItem();
			item.dataName = "" + i;
			item.drawable = getResources().getDrawable(R.drawable.ic_launcher);
			items.add(item);
		}

		inflater = LayoutInflater.from(this);

		screenCount = items.size() % SCREEN_NUM == 0 ? items.size()
				/ SCREEN_NUM : items.size() / SCREEN_NUM + 1;

		switcher = (ViewSwitcher) findViewById(R.id.switcher);
		switcher.setFactory(new ViewFactory() {

			@Override
			public View makeView() {
				return inflater.inflate(R.layout.slide_item, null);
			}
		});
		prev = (Button) findViewById(R.id.prev);
		next = (Button) findViewById(R.id.next);
		prev.setOnClickListener(this);
		next.setOnClickListener(this);
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.prev:
			if (screenNo > 0) {
				screenNo--;
				switcher.setInAnimation(this, R.anim.slide_in_left);
				switcher.setOutAnimation(this, R.anim.slide_out_right);
				((GridView) switcher.getNextView()).setAdapter(adapter);
				switcher.showPrevious();
			}
			break;
		case R.id.next:
			if (screenNo < screenCount - 1) {
				screenNo++;
				switcher.setInAnimation(this, R.anim.slide_in_right);
				switcher.setOutAnimation(this, R.anim.slide_out_left);
				((GridView) switcher.getNextView()).setAdapter(adapter);
				switcher.showNext();
			}
			break;
		}

	}

}
