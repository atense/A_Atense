/*
 *  TestActivity.java
 *
 *  Created by admin on 2015年6月23日.
 *  Copyright (c) 2015 Atense. All rights reserved.
 */
package com.atense.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.QuickContactBadge;

import com.atense.R;

/**
 * 
 * @author Admin
 */
public class QuickContactBadgeActivity extends Activity {

	private QuickContactBadge badge;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.quickcontactbadge);

		badge = (QuickContactBadge) findViewById(R.id.badge);
		badge.assignContactFromPhone("18503892909", false);
	}

}
