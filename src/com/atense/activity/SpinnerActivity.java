/*
 *  TestActivity.java
 *
 *  Created by admin on 2015年6月23日.
 *  Copyright (c) 2015 Atense. All rights reserved.
 */
package com.atense.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.atense.R;

/**
 * 
 * @author Admin
 */
public class SpinnerActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.spinner);

		String[] arr1 = new String[] { "孙悟空", "猪八戒", "牛魔王" };
		ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,
				R.layout.array_item, arr1);
		((Spinner) findViewById(R.id.spinner)).setAdapter(adapter1);
	}

}
