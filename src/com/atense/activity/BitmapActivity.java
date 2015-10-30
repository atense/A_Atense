/*
 *  TestActivity.java
 *
 *  Created by admin on 2015年6月23日.
 *  Copyright (c) 2015 Atense. All rights reserved.
 */
package com.atense.activity;

import java.io.IOException;
import java.io.InputStream;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.res.AssetManager;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

import com.atense.R;

/**
 * 
 * @author Admin
 */
public class BitmapActivity extends Activity implements OnClickListener {

	ImageView image;
	Button prev, next;

	AssetManager assets;
	String[] icons;

	int curIndex = 0;
	boolean isNext = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bitmap);

		assets = getAssets();

		image = (ImageView) findViewById(R.id.image);
		prev = (Button) findViewById(R.id.prev);
		next = (Button) findViewById(R.id.next);
		prev.setOnClickListener(this);
		next.setOnClickListener(this);

		try {
			icons = assets.list("");
		} catch (IOException e) {
			icons = null;
			e.printStackTrace();
		}
		setImage();
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.prev) {
			isNext = false;
			curIndex--;
			if (curIndex <= 0) {
				curIndex = icons.length - 1;
			}
		} else if (v.getId() == R.id.next) {
			isNext = true;
			curIndex++;
			if (curIndex >= icons.length) {
				curIndex = 0;
			}
		}
		setImage();
	}

	@SuppressLint("DefaultLocale")
	private void setImage() {
		String fileName = icons[curIndex];
		while (!fileName.toLowerCase().endsWith(".jpg")
				&& !fileName.toLowerCase().endsWith(".png")
				&& !fileName.toLowerCase().endsWith(".gif")) {
			if (isNext) {
				curIndex++;
			} else {
				curIndex--;
			}
			if (curIndex >= icons.length) {
				curIndex = 0;
			}
			fileName = icons[curIndex];
		}

		InputStream assetIS = null;
		try {
			assetIS = assets.open(fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}

		BitmapDrawable bd = (BitmapDrawable) image.getDrawable();
		if (bd != null && bd.getBitmap() != null
				&& bd.getBitmap().isRecycled() == false) {
			bd.getBitmap().recycle();
		}
		image.setImageBitmap(BitmapFactory.decodeStream(assetIS));
	}
}
