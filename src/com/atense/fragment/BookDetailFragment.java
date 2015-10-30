/*
 *  OneFragment.java
 *
 *  Created by admin on 2015年9月16日.
 *  Copyright (c) 2015 Atense. All rights reserved.
 */
package com.atense.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.atense.R;
import com.atense.model.BookContent;

/**
 * 
 * @author Admin
 */
public class BookDetailFragment extends Fragment {

	public final static String ITEM_ID = "item_id";
	BookContent.Book book;
	private final String TAG = "BookDetailFragment";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.e(TAG, "onCreate");
		if (getArguments().containsKey(ITEM_ID)) {
			book = BookContent.ITEM_MAP.get(getArguments().getInt(ITEM_ID));
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.e(TAG, "onCreateView");
		View view = inflater.inflate(R.layout.fragment_book_detail, null);
		TextView bookTitle = (TextView) view.findViewById(R.id.book_title);
		TextView bookDesc = (TextView) view.findViewById(R.id.book_desc);
		bookTitle.setText(book.title);
		bookDesc.setText(book.desc);
		return view;
	}

	@Override
	public void onAttach(Activity activity) {
		Log.e(TAG, "anAttach");
		super.onAttach(activity);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		Log.e(TAG, "onActivityCreated");
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public void onStart() {
		Log.e(TAG, "onStart");
		super.onStart();
	}

	@Override
	public void onResume() {
		Log.e(TAG, "onResume");
		super.onResume();
	}

	@Override
	public void onPause() {
		Log.e(TAG, "onPause");
		super.onPause();
	}

	@Override
	public void onStop() {
		Log.e(TAG, "onStop");
		super.onStop();
	}

	@Override
	public void onDestroy() {
		Log.e(TAG, "onDestroy");
		super.onDestroy();
	}

	@Override
	public void onDetach() {
		Log.e(TAG, "onDetach");
		super.onDetach();
	}
}
