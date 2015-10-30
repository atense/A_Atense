/*
 *  TestActivity.java
 *
 *  Created by admin on 2015年6月23日.
 *  Copyright (c) 2015 Atense. All rights reserved.
 */
package com.atense.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

import com.atense.R;

/**
 * 
 * @author Admin
 */
public class ProgressBarActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 设置窗口特征：启用显示进度的进度条
		requestWindowFeature(Window.FEATURE_PROGRESS);
		// 设置窗口特征：启用不显示进度的进度条
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		setContentView(R.layout.progressbar);

		setProgressBarVisibility(true);
		setProgressBarIndeterminateVisibility(true);

	}

}
