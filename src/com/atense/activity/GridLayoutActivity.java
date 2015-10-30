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
import android.widget.Button;
import android.widget.GridLayout;

import com.atense.R;

/**
 * 
 * @author Admin
 */
public class GridLayoutActivity extends Activity {
	
	private String[] chars = new String[]{
			"1", "2", "3", "/",
			"4", "5", "6", "*",
			"7", "8", "9", "-",
			".", "0", "=", "+"
	};
	
	private GridLayout gridLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gridlayout);
		gridLayout = (GridLayout) findViewById(R.id.layout);
		
		for(int i = 0; i < chars.length; i++) {
			Button btn = new Button(this);
			btn.setText(chars[i]);
			btn.setTextSize(40);
			GridLayout.Spec rowSpec = GridLayout.spec(i / 4 + 2);
			GridLayout.Spec columnSpec = GridLayout.spec(i % 4);
			GridLayout.LayoutParams p = new GridLayout.LayoutParams(rowSpec, columnSpec);
			p.setGravity(Gravity.FILL);
			gridLayout.addView(btn, p);
		}
	}

}
