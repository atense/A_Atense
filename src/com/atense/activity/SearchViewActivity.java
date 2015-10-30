/*
 *  TestActivity.java
 *
 *  Created by admin on 2015年6月23日.
 *  Copyright (c) 2015 Atense. All rights reserved.
 */
package com.atense.activity;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.Toast;

import com.atense.R;

/**
 * 
 * @author Admin
 */
public class SearchViewActivity extends Activity {

	SearchView sv;
	ListView list;
	String[] arr = { "aaaaabb", "bbbbabb", "cccdc", "ddddcdd", "aaaaabb",
			"bbbbabb", "cccdc", "ddddcdd" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.searchview);

		sv = (SearchView) findViewById(R.id.searchview);
		list = (ListView) findViewById(R.id.list);
		ArrayAdapter<String> ad = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, arr);
		list.setTextFilterEnabled(true);
		list.setAdapter(ad);
		sv.setIconified(false);
		sv.setSubmitButtonEnabled(false);
		sv.setQueryHint("Search");
		sv.setOnQueryTextListener(new OnQueryTextListener() {

			@Override
			public boolean onQueryTextSubmit(String query) {
				Toast.makeText(SearchViewActivity.this, "你选择的是：" + query,
						Toast.LENGTH_LONG).show();
				return false;
			}

			@Override
			public boolean onQueryTextChange(String newText) {
				if (TextUtils.isEmpty(newText)) {
					list.clearTextFilter();
				} else {
					list.setFilterText(newText);
				}
				return true;
			}
		});
	}

}
