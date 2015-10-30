/*
 *  TestActivity.java
 *
 *  Created by admin on 2015年6月23日.
 *  Copyright (c) 2015 Atense. All rights reserved.
 */
package com.atense.activity;

import android.app.ActionBar;
import android.app.ActionBar.OnNavigationListener;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.atense.R;
import com.atense.fragment.OneFragment;

/**
 * 
 * @author Admin
 */
public class ActionBarActivity extends FragmentActivity implements TabListener,
		OnNavigationListener {

	final int FONT_10 = 0x111;
	final int FONT_12 = 0x112;
	final int FONT_14 = 0x113;
	final int FONT_16 = 0x114;
	final int FONT_18 = 0x115;

	final int PLAIN_ITEM_1 = 0x11a;
	final int PLAIN_ITEM = 0x11b;

	final int FONT_RED = 0x116;
	final int FONT_BLUE = 0x117;
	final int FONT_GREEN = 0x118;

	final int MENU1 = 0x121;
	final int MENU2 = 0x122;
	final int MENU3 = 0x123;

	TextView tv;
	ActionBar actionBar;
	ViewPager pager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.actionbar);
		setContentView(R.layout.actionbar1);

		// 当Theme 为Holo/
		actionBar = getActionBar();
		// actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		// // actionBar
		// // .addTab(actionBar.newTab().setText("第一页").setTabListener(this));
		// // actionBar
		// // .addTab(actionBar.newTab().setText("第二页").setTabListener(this));
		// // actionBar
		// // .addTab(actionBar.newTab().setText("第三页").setTabListener(this));
		//
		// pager = (ViewPager) findViewById(R.id.layout);
		// FragmentPagerAdapter adapter = new FragmentPagerAdapter(
		// getSupportFragmentManager()) {
		//
		// @Override
		// public int getCount() {
		// return 3;
		// }
		//
		// @Override
		// public android.support.v4.app.Fragment getItem(int arg0) {
		// android.support.v4.app.Fragment fragment = new OneFragment();
		// Bundle args = new Bundle();
		// args.putInt(OneFragment.ARG_SELECT_NUMBER, arg0 + 1);
		// fragment.setArguments(args);
		// return fragment;
		// }
		//
		// @Override
		// public CharSequence getPageTitle(int position) {
		// if (position == 0) {
		// return "第一页";
		// } else if (position == 1) {
		// return "第二页";
		// } else if (position == 2) {
		// return "第三页";
		// }
		//
		// return null;
		// }
		// };
		//
		// for (int i = 0; i < adapter.getCount(); i++) {
		// actionBar.addTab(actionBar.newTab()
		// .setText(adapter.getPageTitle(i)).setTabListener(this));
		// }
		// pager.setAdapter(adapter);
		// pager.setOnPageChangeListener(new OnPageChangeListener() {
		//
		// @Override
		// public void onPageSelected(int arg0) {
		// actionBar.setSelectedNavigationItem(arg0);
		// }
		//
		// @Override
		// public void onPageScrolled(int arg0, float arg1, int arg2) {
		// }
		//
		// @Override
		// public void onPageScrollStateChanged(int arg0) {
		//
		// }
		// });

		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
		// actionBar.setDisplayShowTitleEnabled(false);
		ArrayAdapter<String> arrAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, android.R.id.text1,
				new String[] { "第一页", "第二页", "第三页" });
		actionBar.setListNavigationCallbacks(arrAdapter, this);

	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// // android.app.Fragment fragment = new OneFragment();
		// // Bundle args = new Bundle();
		// // args.putInt(OneFragment.ARG_SELECT_NUMBER, tab.getPosition() + 1);
		// // fragment.setArguments(args);
		// // FragmentTransaction ftt = getFragmentManager().beginTransaction();
		// // ftt.replace(R.id.layout, fragment);
		// // ftt.commit();
		// pager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onNavigationItemSelected(int itemPosition, long itemId) {
		Fragment fragment = new OneFragment();
		Bundle args = new Bundle();
		args.putInt(OneFragment.ARG_SELECT_NUMBER, itemPosition + 1);
		fragment.setArguments(args);
		FragmentTransaction ftt = getFragmentManager().beginTransaction();
		ftt.replace(R.id.layout, fragment);
		ftt.commit();
		return true;
	}

}
