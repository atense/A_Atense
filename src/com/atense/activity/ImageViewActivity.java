/*
 *  TestActivity.java
 *
 *  Created by admin on 2015年6月23日.
 *  Copyright (c) 2015 Atense. All rights reserved.
 */
package com.atense.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ImageView;

import com.atense.R;

/**
 * 
 * @author Admin
 */
public class ImageViewActivity extends Activity implements OnClickListener,
		OnTouchListener {

	private int[] images = { R.drawable.s1, R.drawable.s1 };
	private int currentPosition = 0, alpha = 255, screenWidth = 0;

	private Button plus, minus, next;
	private ImageView image1, image2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.imageview);

		plus = (Button) findViewById(R.id.plus);
		minus = (Button) findViewById(R.id.minus);
		next = (Button) findViewById(R.id.next);
		image1 = (ImageView) findViewById(R.id.image1);
		image2 = (ImageView) findViewById(R.id.image2);

		plus.setOnClickListener(this);
		minus.setOnClickListener(this);
		next.setOnClickListener(this);

		image1.setOnTouchListener(this);
		screenWidth = this.getWindow().getWindowManager().getDefaultDisplay()
				.getWidth();
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		int top = image1.getTop();
		BitmapDrawable bd = (BitmapDrawable) image1.getDrawable();
		Bitmap b = bd.getBitmap();
		// 缩放比例
		double scale = (double) b.getWidth() / screenWidth;

		int x = (int) (event.getX() * scale);
		int y = (int) ((event.getY() - top) * scale);

		if (x + 120 > b.getWidth()) {
			x = b.getWidth() - 120;
		}
		if (y + 120 > b.getHeight()) {
			y = b.getHeight() - 120;
		}

		image2.setImageBitmap(Bitmap.createBitmap(b, x, y, 120, 120));

		return false;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.plus:
			alpha = alpha + 20;
			break;
		case R.id.minus:
			alpha = alpha - 20;
			break;
		case R.id.next:
			image1.setBackgroundResource(images[++currentPosition
					% images.length]);
			return;
		}
		if (alpha > 255) {
			alpha = 255;
		}
		if (alpha < 0) {
			alpha = 0;
		}
		image1.setAlpha(alpha);
	}

}
