/*
 *  TestActivity.java
 *
 *  Created by admin on 2015年6月23日.
 *  Copyright (c) 2015 Atense. All rights reserved.
 */
package com.atense.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnMenuItemClickListener;
import android.widget.TextView;
import android.widget.Toast;

import com.atense.R;

/**
 * 
 * @author Admin
 */
public class MenuActivity extends Activity implements OnClickListener {

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
	PopupMenu popup;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu);

		// 当Theme 为Holo/
		ActionBar actionBar = getActionBar();
		actionBar.setIcon(R.drawable.home_me);
		// 是否将应用图标变成可点击的，在图标左侧添加一个向左的箭头
		actionBar.setDisplayHomeAsUpEnabled(true);
		// 是否显示应用程序的图标
		actionBar.setDisplayShowHomeEnabled(false);
		// 是否显示应用程序的标题
		// actionBar.setDisplayShowTitleEnabled(false);
		// 是否将应用图标变成可点击的
		actionBar.setHomeButtonEnabled(true);

		// actionBar.setBackgroundDrawable(getResources().getDrawable(
		// R.drawable.s1));

		tv = (TextView) findViewById(R.id.text);
		registerForContextMenu(tv);
		TextView tv1 = (TextView) findViewById(R.id.text1);
		tv1.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.text1) {
			popup = new PopupMenu(this, tv);
			getMenuInflater().inflate(R.menu.content, popup.getMenu());
			popup.setOnMenuItemClickListener(new OnMenuItemClickListener() {

				@Override
				public boolean onMenuItemClick(MenuItem item) {
					switch (item.getItemId()) {
					case MENU1:
					case R.id.color_red:
						item.setChecked(true);
						tv.setBackgroundColor(Color.RED);
						break;
					case MENU2:
					case R.id.color_blue:
						item.setChecked(true);
						tv.setBackgroundColor(Color.BLUE);
						break;
					case MENU3:
					case R.id.color_green:
						item.setChecked(true);
						tv.setBackgroundColor(Color.GREEN);
						break;
					}
					return false;
				}
			});
			popup.show();
		}
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// menu.add(0, MENU1, 0, "红色");
		// menu.add(0, MENU2, 0, "蓝色");
		// menu.add(0, MENU3, 0, "绿色");
		// menu.setGroupCheckable(0, true, true);
		MenuInflater inflater = new MenuInflater(this);
		inflater.inflate(R.menu.content, menu);
		menu.setHeaderIcon(R.drawable.home_me);
		menu.setHeaderTitle("选择背景色");
		super.onCreateContextMenu(menu, v, menuInfo);
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case MENU1:
		case R.id.color_red:
			item.setChecked(true);
			tv.setBackgroundColor(Color.RED);
			break;
		case MENU2:
		case R.id.color_blue:
			item.setChecked(true);
			tv.setBackgroundColor(Color.BLUE);
			break;
		case MENU3:
		case R.id.color_green:
			item.setChecked(true);
			tv.setBackgroundColor(Color.GREEN);
			break;
		}
		return true;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// SubMenu fontMenu = menu.addSubMenu("字体大小");
		// fontMenu.setIcon(R.drawable.ic_launcher);
		// fontMenu.setHeaderIcon(R.drawable.home_me);
		// fontMenu.setHeaderTitle("Header Title");
		// fontMenu.add(0, FONT_10, 0, "10号字体");
		// fontMenu.add(0, FONT_12, 0, "12号字体");
		// fontMenu.add(0, FONT_14, 0, "14号字体");
		// fontMenu.add(0, FONT_16, 0, "16号字体");
		// fontMenu.add(0, FONT_18, 0, "18号字体");
		// menu.add(0, PLAIN_ITEM, 0, "普通菜单");
		// SubMenu colorMenu = menu.addSubMenu("颜色");
		// colorMenu.setIcon(R.drawable.home_food);
		// colorMenu.setHeaderIcon(R.drawable.home_me);
		// colorMenu.setHeaderTitle("Header Title");
		// colorMenu.add(0, FONT_RED, 0, "红色");
		// colorMenu.add(0, FONT_BLUE, 0, "蓝色");
		// colorMenu.add(0, FONT_GREEN, 0, "绿色");
		//
		// MenuItem item = colorMenu.add("Test Activity");
		// item.setIntent(new Intent(this, ListViewActivity.class));
		// menu.add(0, PLAIN_ITEM_1, 0, "普通菜单1");

		MenuInflater inflater = new MenuInflater(this);
		inflater.inflate(R.menu.menu, menu);

		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case FONT_10:
		case R.id.font_10:
			tv.setTextSize(10 * 2);
			break;
		case FONT_12:
		case R.id.font_12:
			tv.setTextSize(12 * 2);
			break;
		case FONT_14:
		case R.id.font_14:
			tv.setTextSize(14 * 2);
			break;
		case FONT_16:
		case R.id.font_16:
			tv.setTextSize(16 * 2);
			break;
		case FONT_18:
		case R.id.font_18:
			tv.setTextSize(18 * 2);
			break;
		case FONT_RED:
		case R.id.color_red:
			tv.setTextColor(Color.RED);
			break;
		case FONT_BLUE:
		case R.id.color_blue:
			tv.setTextColor(Color.BLUE);
			break;
		case FONT_GREEN:
		case R.id.color_green:
			tv.setTextColor(Color.GREEN);
			break;
		case PLAIN_ITEM:
		case R.id.plain_itme:
			Toast.makeText(MenuActivity.this, "你单击了普通菜单", Toast.LENGTH_LONG)
					.show();
			break;
		case PLAIN_ITEM_1:
			Toast.makeText(MenuActivity.this, "你单击了普通菜单1", Toast.LENGTH_LONG)
					.show();
			break;
		}
		return true;
	}

}
