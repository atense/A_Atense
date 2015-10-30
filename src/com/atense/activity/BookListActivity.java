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

import com.atense.R;
import com.atense.fragment.BookDetailFragment;
import com.atense.fragment.BookListFragment;

/**
 * 
 * @author Admin
 */
public class BookListActivity extends Activity implements
		com.atense.fragment.BookListFragment.Callbacks {

	boolean mTwoPane = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_book_list);
		if (findViewById(R.id.book_detail) != null) {
			mTwoPane = true;
			((BookListFragment) getFragmentManager().findFragmentById(
					R.id.book_list)).setActivateOnItemClick(true);
		}
	}

	@Override
	public void onItemSelected(Integer id) {
		if (mTwoPane) {
			Bundle args = new Bundle();
			args.putInt(BookDetailFragment.ITEM_ID, id);
			BookDetailFragment fragment = new BookDetailFragment();
			FragmentTransaction ft = getFragmentManager().beginTransaction();
			fragment.setArguments(args);
			ft.replace(R.id.book_detail, fragment);
			ft.commit();
		} else {
			Intent intent = new Intent(this, BookDetailActivity.class);
			intent.putExtra(BookDetailFragment.ITEM_ID, id);
			startActivity(intent);
		}
	}
}