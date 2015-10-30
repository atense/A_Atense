/*
 *  OneFragment.java
 *
 *  Created by admin on 2015年9月16日.
 *  Copyright (c) 2015 Atense. All rights reserved.
 */
package com.atense.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * 
 * @author Admin
 */
public class OneFragment extends Fragment {

	public final static String ARG_SELECT_NUMBER = "select number";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		TextView tv = new TextView(getActivity());
		tv.setGravity(Gravity.CENTER);
		Bundle args = getArguments();
		tv.setText(args.getInt(ARG_SELECT_NUMBER) + "");
		tv.setTextSize(30);
		return tv;
	}
}
