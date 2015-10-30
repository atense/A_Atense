/*
 *  TestActivity.java
 *
 *  Created by admin on 2015年6月23日.
 *  Copyright (c) 2015 Atense. All rights reserved.
 */
package com.atense.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.atense.R;

/**
 * 
 * @author Admin
 */
public class ExpandableListViewActivity extends Activity {

	private ExpandableListView list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.expandablelistview);

		BaseExpandableListAdapter adapter = new BaseExpandableListAdapter() {

			int[] logs = { R.drawable.home_class, R.drawable.home_food,
					R.drawable.home_me };
			String[] groupNames = { "类别", "食物", "我的" };
			String[][] items = { { "类别1", "类别2", "类别3" },
					{ "食物1", "食物2", "食物3" }, { "我的1", "我的2", "我的3" } };

			@Override
			public boolean isChildSelectable(int groupPosition,
					int childPosition) {
				return true;
			}

			@Override
			public boolean hasStableIds() {
				return true;
			}

			@Override
			public View getGroupView(int groupPosition, boolean isExpanded,
					View convertView, ViewGroup parent) {
				LinearLayout ll = new LinearLayout(
						ExpandableListViewActivity.this);
				ll.setOrientation(0);
				ImageView iv = new ImageView(ExpandableListViewActivity.this);
				iv.setImageResource(logs[groupPosition]);
				ll.addView(iv);
				TextView tv = getTextView();
				tv.setText(getGroup(groupPosition).toString());
				ll.addView(tv);
				return ll;
			}

			@Override
			public long getGroupId(int groupPosition) {
				return groupPosition;
			}

			@Override
			public int getGroupCount() {
				return groupNames.length;
			}

			@Override
			public Object getGroup(int groupPosition) {
				return groupNames[groupPosition];
			}

			@Override
			public int getChildrenCount(int groupPosition) {
				return items[groupPosition].length;
			}

			@Override
			public View getChildView(int groupPosition, int childPosition,
					boolean isLastChild, View convertView, ViewGroup parent) {
				TextView tv = getTextView();
				tv.setText(items[groupPosition][childPosition]);
				return tv;
			}

			private TextView getTextView() {
				AbsListView.LayoutParams p = new AbsListView.LayoutParams(
						LayoutParams.MATCH_PARENT, 64);
				TextView tv = new TextView(ExpandableListViewActivity.this);
				tv.setLayoutParams(p);
				tv.setTextSize(20);
				tv.setGravity(Gravity.CENTER_VERTICAL);
				return tv;
			}

			@Override
			public long getChildId(int groupPosition, int childPosition) {
				return childPosition;
			}

			@Override
			public Object getChild(int groupPosition, int childPosition) {
				return items[groupPosition][childPosition];
			}
		};

		list = (ExpandableListView) findViewById(R.id.list);
		list.setGroupIndicator(null);
		list.setAdapter(adapter);

	}

}
