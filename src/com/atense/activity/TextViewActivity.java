/*
 *  TestActivity.java
 *
 *  Created by admin on 2015年6月23日.
 *  Copyright (c) 2015 Atense. All rights reserved.
 */
package com.atense.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import com.atense.R;

/**
 * 
 * @author Admin
 */
public class TextViewActivity extends Activity {

	private AutoCompleteTextView auto;
	private MultiAutoCompleteTextView mauto;
	private String[] arr = new String[] { "疯狂Java讲义", "疯狂Ajax讲义", "疯狂Xml讲义",
			"疯狂Android讲义" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.textview);

		ArrayAdapter<String> aa = new ArrayAdapter<String>(this,
				android.R.layout.simple_dropdown_item_1line, arr);

		auto = (AutoCompleteTextView) findViewById(R.id.auto);
		auto.setAdapter(aa);
		mauto = (MultiAutoCompleteTextView) findViewById(R.id.mauto);
		mauto.setAdapter(aa);
		mauto.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());

		((TextView) findViewById(R.id.text))
				.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						Toast toast = new Toast(TextViewActivity.this);
						View view = LayoutInflater.from(TextViewActivity.this)
								.inflate(R.layout.custom_toast, null);
						toast.setView(view);
						toast.setGravity(Gravity.CENTER, 0, 0);
						toast.setDuration(Toast.LENGTH_LONG);
						toast.show();
					}
				});
	}

}
