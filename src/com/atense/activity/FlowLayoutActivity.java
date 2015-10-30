/*
 *  TestActivity.java
 *
 *  Created by admin on 2015年6月23日.
 *  Copyright (c) 2015 Atense. All rights reserved.
 */
package com.atense.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

import com.atense.R;
import com.atense.widget.FlowLayout;

/**
 * 
 * @author Admin
 */
public class FlowLayoutActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.flowlayout);
		FlowLayout fl = (FlowLayout) findViewById(R.id.fl);
		Button tv1 = new Button(this);
		tv1.setText("ddddddddd");
		fl.addView(tv1);
		Button tv2 = new Button(this);
		tv2.setText("ddddddddd");
		fl.addView(tv2);
		Button tv4 = new Button(this);
		tv4.setText("dddddddddddddddddddddddd");
		fl.addView(tv4);
		Button tv3 = new Button(this);
		tv3.setText("ddddddddd");
		fl.addView(tv3);
	}
}
