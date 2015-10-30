/*
 *  TestActivity.java
 *
 *  Created by admin on 2015年6月23日.
 *  Copyright (c) 2015 Atense. All rights reserved.
 */
package com.atense.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.atense.R;

/**
 * 
 * @author Admin
 */
public class StartActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.start);

		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);

		// Configuration cfg = getResources().getConfiguration();
		//
		// String text = String
		// .format("%s屏幕, %s, %s",
		// cfg.orientation == Configuration.ORIENTATION_LANDSCAPE ? "横向"
		// : "竖向",
		// cfg.navigation == Configuration.NAVIGATION_NONAV ? "没有方向控制"
		// : cfg.navigation == Configuration.NAVIGATION_WHEEL ? "滚动控制方向"
		// : cfg.navigation == Configuration.NAVIGATION_DPAD ? "方向键盘控制"
		// : "轨迹球控制方向",
		// cfg.touchscreen == Configuration.TOUCHSCREEN_NOTOUCH ? "无触屏"
		// : "支持触摸屏");
		//
		// Toast.makeText(this, text, Toast.LENGTH_LONG).show();

		// setRequestedOrientation(0);

		findViewById(R.id.test_custom).setOnClickListener(this);
		findViewById(R.id.test_tablelayout).setOnClickListener(this);
		findViewById(R.id.test_framelayout).setOnClickListener(this);
		findViewById(R.id.test_relativelayout).setOnClickListener(this);
		findViewById(R.id.test_gridlayout).setOnClickListener(this);
		findViewById(R.id.test_textview).setOnClickListener(this);
		findViewById(R.id.test_togglebutton).setOnClickListener(this);
		findViewById(R.id.test_clock).setOnClickListener(this);
		findViewById(R.id.test_chronometer).setOnClickListener(this);
		findViewById(R.id.test_imageview).setOnClickListener(this);
		findViewById(R.id.test_activity).setOnClickListener(this);
		findViewById(R.id.test_quickcontactbadge).setOnClickListener(this);
		findViewById(R.id.test_listview).setOnClickListener(this);
		findViewById(R.id.test_arrayadapter).setOnClickListener(this);
		findViewById(R.id.test_listactivity).setOnClickListener(this);
		findViewById(R.id.test_simpleadapter).setOnClickListener(this);
		findViewById(R.id.test_expandablelistview).setOnClickListener(this);
		findViewById(R.id.test_spinner).setOnClickListener(this);
		findViewById(R.id.test_adapterviewfilpper).setOnClickListener(this);
		findViewById(R.id.test_stackview).setOnClickListener(this);
		findViewById(R.id.test_progressbar).setOnClickListener(this);
		findViewById(R.id.test_viewswitcher).setOnClickListener(this);
		findViewById(R.id.test_calendarview).setOnClickListener(this);
		findViewById(R.id.test_searchview).setOnClickListener(this);
		findViewById(R.id.test_dialog).setOnClickListener(this);
		findViewById(R.id.test_menu).setOnClickListener(this);
		findViewById(R.id.test_actionbar).setOnClickListener(this);
		findViewById(R.id.test_plane).setOnClickListener(this);
		findViewById(R.id.test_calprime).setOnClickListener(this);
		findViewById(R.id.test_asynctask).setOnClickListener(this);
		findViewById(R.id.test_bookfragment).setOnClickListener(this);
		findViewById(R.id.test_book).setOnClickListener(this);
		findViewById(R.id.test_bitmap).setOnClickListener(this);
		findViewById(R.id.test_drawingboard).setOnClickListener(this);
		findViewById(R.id.test_alphaimageview).setOnClickListener(this);
		findViewById(R.id.test_rawres).setOnClickListener(this);
		findViewById(R.id.test_path).setOnClickListener(this);
		findViewById(R.id.test_surfaceview).setOnClickListener(this);
		findViewById(R.id.test_gesture).setOnClickListener(this);
		findViewById(R.id.test_viewflipper).setOnClickListener(this);
		findViewById(R.id.test_gesturelibs).setOnClickListener(this);
		findViewById(R.id.test_tts).setOnClickListener(this);
		findViewById(R.id.test_dict).setOnClickListener(this);
		findViewById(R.id.test_service).setOnClickListener(this);
		findViewById(R.id.test_telephonymanager).setOnClickListener(this);
		findViewById(R.id.test_smsmanager).setOnClickListener(this);
		findViewById(R.id.test_broadcast).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent();
		switch (v.getId()) {
		case R.id.test_broadcast:
			intent.setClass(this, BroadcastActivity.class);
			break;
		case R.id.test_smsmanager:
			intent.setClass(this, SMSActivity.class);
			break;
		case R.id.test_telephonymanager:
			intent.setClass(this, TelephonyManagerActivity.class);
			break;
		case R.id.test_service:
			intent.setClass(this, ServiceActivity.class);
			break;
		case R.id.test_dict:
			intent.setClass(this, DictActivity.class);
			break;
		case R.id.test_tts:
			intent.setClass(this, TTSActivity.class);
			break;
		case R.id.test_gesturelibs:
			intent.setClass(this, GestureLibsActivity.class);
			break;
		case R.id.test_viewflipper:
			intent.setClass(this, ViewFlipperActivity.class);
			break;
		case R.id.test_gesture:
			intent.setClass(this, GestureImageViewActivity.class);
			break;
		case R.id.test_surfaceview:
			intent.setClass(this, SurfaceViewActivity.class);
			break;
		case R.id.test_path:
			intent.setClass(this, PathActivity.class);
			break;
		case R.id.test_rawres:
			intent.setClass(this, RawResActivity.class);
			break;
		case R.id.test_alphaimageview:
			intent.setClass(this, AlphaImageViewActivity.class);
			break;
		case R.id.test_drawingboard:
			intent.setClass(this, DrawingBoardActivity.class);
			break;
		case R.id.test_bitmap:
			intent.setClass(this, BitmapActivity.class);
			break;
		case R.id.test_book:
			intent.setClass(this, BookListActivity.class);
			break;
		case R.id.test_bookfragment:
			intent.setClass(this, SelectBookActivity.class);
			break;
		case R.id.test_asynctask:
			intent.setClass(this, AsyncTaskActivity.class);
			break;
		case R.id.test_calprime:
			intent.setClass(this, CalPrimeActivity.class);
			break;
		case R.id.test_plane:
			intent.setClass(this, PlaneViewActivity.class);
			break;
		case R.id.test_actionbar:
			intent.setClass(this, ActionBarActivity.class);
			break;
		case R.id.test_menu:
			intent.setClass(this, MenuActivity.class);
			break;
		case R.id.test_dialog:
			intent.setClass(this, DialogActivity.class);
			break;
		case R.id.test_searchview:
			intent.setClass(this, SearchViewActivity.class);
			break;
		case R.id.test_calendarview:
			intent.setClass(this, CalendarViewActivity.class);
			break;
		case R.id.test_viewswitcher:
			intent.setClass(this, ViewSwitcherActivity.class);
			break;
		case R.id.test_progressbar:
			intent.setClass(this, ProgressBarActivity.class);
			break;
		case R.id.test_stackview:
			intent.setClass(this, StackViewActivity.class);
			break;
		case R.id.test_adapterviewfilpper:
			intent.setClass(this, AdapterViewFlipperActivity.class);
			break;
		case R.id.test_spinner:
			intent.setClass(this, SpinnerActivity.class);
			break;
		case R.id.test_expandablelistview:
			intent.setClass(this, ExpandableListViewActivity.class);
			break;
		case R.id.test_simpleadapter:
			intent.setClass(this, SimpleAdapterActivity.class);
			break;
		case R.id.test_listactivity:
			intent.setClass(this, ListActivityActivity.class);
			break;
		case R.id.test_arrayadapter:
			intent.setClass(this, ArrayAdapterActivity.class);
			break;
		case R.id.test_listview:
			intent.setClass(this, ListViewActivity.class);
			break;
		case R.id.test_quickcontactbadge:
			intent.setClass(this, QuickContactBadgeActivity.class);
			break;
		case R.id.test_activity:
			intent.setClass(this, Test1Activity.class);
			break;
		case R.id.test_imageview:
			intent.setClass(this, ImageViewActivity.class);
			break;
		case R.id.test_chronometer:
			intent.setClass(this, ChronometerActivity.class);
			break;
		case R.id.test_clock:
			intent.setClass(this, ClockActivity.class);
			break;
		case R.id.test_custom:
			intent.setClass(this, CustomActivity.class);
			break;
		case R.id.test_tablelayout:
			intent.setClass(this, TableLayoutActivity.class);
			break;
		case R.id.test_framelayout:
			intent.setClass(this, FrameLayoutActivity.class);
			break;
		case R.id.test_relativelayout:
			intent.setClass(this, RelativeLayoutActivity.class);
			break;
		case R.id.test_gridlayout:
			intent.setClass(this, GridLayoutActivity.class);
			break;
		case R.id.test_textview:
			intent.setClass(this, TextViewActivity.class);
			break;
		case R.id.test_togglebutton:
			intent.setClass(this, ToggleButtonActivity.class);
			break;
		}

		startActivity(intent);

	}
}
