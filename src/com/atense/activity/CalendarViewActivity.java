/*
 *  TestActivity.java
 *
 *  Created by admin on 2015年6月23日.
 *  Copyright (c) 2015 Atense. All rights reserved.
 */
package com.atense.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.CalendarView.OnDateChangeListener;
import android.widget.Toast;

import com.atense.R;

/**
 * 
 * @author Admin
 */
public class CalendarViewActivity extends Activity {

	CalendarView calendar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.calendarview);

		calendar = (CalendarView) findViewById(R.id.calendar);
		calendar.setOnDateChangeListener(new OnDateChangeListener() {

			@Override
			public void onSelectedDayChange(CalendarView view, int year,
					int month, int dayOfMonth) {
				Toast.makeText(CalendarViewActivity.this,
						"生日为" + year + "年" + month + "月" + dayOfMonth + "日",
						Toast.LENGTH_LONG).show();
			}
		});

	}

}
