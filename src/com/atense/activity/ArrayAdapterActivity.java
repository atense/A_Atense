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
import android.widget.ListView;

import com.atense.R;

/**
 * 
 * @author Admin
 */
public class ArrayAdapterActivity extends Activity {

	ListView list1, list2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.arrayadapter);
		
		list1 = (ListView) findViewById(R.id.list1);
		list2 = (ListView) findViewById(R.id.list2);
		
		String[] arr1 = new String[]{ "孙悟空", "猪八戒", "牛魔王"};
		ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, R.layout.array_item, arr1);
		list1.setAdapter(adapter1);
		
		String[] arr2 = new String[]{ "Java", "Hibernate", "Android"};
		ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, R.layout.checked_item, arr2);
		list2.setAdapter(adapter2);
	}

}
