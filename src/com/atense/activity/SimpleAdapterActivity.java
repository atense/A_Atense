/*
 *  TestActivity.java
 *
 *  Created by admin on 2015年6月23日.
 *  Copyright (c) 2015 Atense. All rights reserved.
 */
package com.atense.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.atense.R;

/**
 * 
 * @author Admin
 */
public class SimpleAdapterActivity extends Activity {

	private ListView list;
	String[] names = {"虎头", "弄玉", "李清照", "李白"};
	String[] descs = {"可爱的小孩", "一个擅长音乐的女孩", "一个擅长文学的女性", "浪漫是人"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.simpleadapter);
		
		list = (ListView) findViewById(R.id.list);
		
		List<Map<String, Object>> listitems = new ArrayList<Map<String, Object>>();
		for(int i=0; i<names.length; i++) {
			Map<String, Object> listItem = new HashMap<String, Object>();
			listItem.put("name", names[i]);
			listItem.put("desc", descs[i]);
			listitems.add(listItem);
		}
		
		SimpleAdapter adapter = new SimpleAdapter(this, listitems, R.layout.simple_item, new String[]{"name", "desc"}, new int[]{R.id.name, R.id.desc});
		list.setAdapter(adapter);
		list.setOnItemClickListener(new OnItemClickListener() {
			
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Log.e("Item", names[arg2]+"被单击了");
			}
		});
		list.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				Log.e("Item", names[arg2]+"被选中了");
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}
			
		});
	}

}
