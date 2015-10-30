/*
 *  TestActivity.java
 *
 *  Created by admin on 2015年6月23日.
 *  Copyright (c) 2015 Atense. All rights reserved.
 */
package com.atense.activity;

import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.atense.R;

/**
 * 
 * @author Admin
 */
public class ResultActivity extends Activity {

	ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dict_result);

		listView = (ListView) findViewById(R.id.listview);

		Bundle bundle = getIntent().getExtras();
		List<Map<String, Object>> data = (List<Map<String, Object>>) bundle
				.getSerializable("data");

		SimpleAdapter adapter = new SimpleAdapter(this, data, R.layout.line,
				new String[] { "word", "detail" }, new int[] { R.id.word,
						R.id.detail });
		listView.setAdapter(adapter);
	}
}
