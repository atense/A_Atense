/*
 *  TestActivity.java
 *
 *  Created by admin on 2015年6月23日.
 *  Copyright (c) 2015 Atense. All rights reserved.
 */
package com.atense.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.GestureOverlayView.OnGesturePerformedListener;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.atense.R;

/**
 * 
 * @author Admin
 */
public class GestureLibsActivity extends Activity {

	GestureOverlayView gesture;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gesture_libs);

		gesture = (GestureOverlayView) findViewById(R.id.gesture);
		gesture.addOnGesturePerformedListener(new OnGesturePerformedListener() {
			@Override
			public void onGesturePerformed(GestureOverlayView overlay,
					final Gesture gesture) {
				View root = getLayoutInflater().inflate(
						R.layout.gesture_libs_dialog, null);
				ImageView iv = (ImageView) root.findViewById(R.id.image);
				final EditText et = (EditText) root.findViewById(R.id.edit);
				iv.setImageBitmap(gesture.toBitmap(128, 128, 10, 0xffff0000));
				new AlertDialog.Builder(GestureLibsActivity.this).setView(root)
						.setPositiveButton("OK", new OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								String gestureName = et.getText().toString();
								GestureLibrary gl = GestureLibraries
										.fromFile("/mnt/sdcard/mygesture");
								gl.addGesture(gestureName, gesture);
								gl.save();
							}
						}).setNegativeButton("Cancel", null).show();
			}
		});

	}
}
