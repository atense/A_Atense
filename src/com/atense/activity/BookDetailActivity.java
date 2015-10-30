/*
 *  TestActivity.java
 *
 *  Created by admin on 2015年6月23日.
 *  Copyright (c) 2015 Atense. All rights reserved.
 */
package com.atense.activity;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.atense.R;
import com.atense.fragment.BookDetailFragment;

/**
 * 
 * @author Admin
 */
public class BookDetailActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_book_detail);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		if (savedInstanceState == null) {
			Bundle args = new Bundle();
			args.putInt(BookDetailFragment.ITEM_ID,
					getIntent().getIntExtra(BookDetailFragment.ITEM_ID, 1));
			BookDetailFragment fragment = new BookDetailFragment();
			FragmentTransaction ft = getFragmentManager().beginTransaction();
			fragment.setArguments(args);
			ft.replace(R.id.layout, fragment);
			ft.addToBackStack(null);
			ft.commit();
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == android.R.id.home) {
			Intent intent = new Intent(this, BookListActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
		}
		return true;
	}

}
