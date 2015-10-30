/*
 *  TestActivity.java
 *
 *  Created by admin on 2015年6月23日.
 *  Copyright (c) 2015 Atense. All rights reserved.
 */
package com.atense.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.ToggleButton;

import com.atense.R;

/**
 * 
 * @author Admin
 */
public class ToggleButtonActivity extends Activity {
	
	ToggleButton toggle;
	Switch switcher;
	LinearLayout layout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.togglebutton);
		toggle = (ToggleButton) findViewById(R.id.toggle);
		switcher = (Switch) findViewById(R.id.switcher);
		layout = (LinearLayout) findViewById(R.id.layout);
		OnCheckedChangeListener listener = new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(isChecked) {
					layout.setOrientation(1);
				} else {
					layout.setOrientation(0);
				}
			}
		};
		toggle.setOnCheckedChangeListener(listener);
		switcher.setOnCheckedChangeListener(listener);
	}

}
