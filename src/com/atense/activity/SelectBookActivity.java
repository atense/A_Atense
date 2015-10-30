/*
 *  TestActivity.java
 *
 *  Created by admin on 2015年6月23日.
 *  Copyright (c) 2015 Atense. All rights reserved.
 */
package com.atense.activity;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;

import com.atense.R;
import com.atense.fragment.BookDetailFragment;

/**
 * 
 * @author Admin
 */
public class SelectBookActivity extends Activity implements
		com.atense.fragment.BookListFragment.Callbacks {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_book_twopane);
	}

	@Override
	public void onItemSelected(Integer id) {
		Bundle args = new Bundle();
		args.putInt(BookDetailFragment.ITEM_ID, id);
		BookDetailFragment fragment = new BookDetailFragment();
		FragmentTransaction ft = getFragmentManager().beginTransaction();
		fragment.setArguments(args);
		ft.replace(R.id.book_detail, fragment);
		ft.addToBackStack(null);
		ft.commit();
	}
}
