/*
 *  TestActivity.java
 *
 *  Created by admin on 2015年6月23日.
 *  Copyright (c) 2015 Atense. All rights reserved.
 */
package com.atense.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;

import com.atense.R;

/**
 * 
 * @author Admin
 */
public class SurfaceViewActivity extends Activity {
	// private SurfaceHolder holder;
	// private Paint paint;
	// final int HEIGHT=320;
	// final int WIDTH=320;
	// final int X_OFFSET = 5;
	// private int cx = X_OFFSET;
	// //实际的Y轴的位置
	// int centerY = HEIGHT /2;
	// Timer timer = new Timer();
	// TimerTask task = null;
	//
	// @Override
	// protected void onCreate(Bundle savedInstanceState) {
	// // TODO Auto-generated method stub
	// super.onCreate(savedInstanceState);
	//
	// setContentView(R.layout.show_wave);
	//
	// final SurfaceView surface = (SurfaceView)findViewById(R.id.show);
	// //初始化SurfaceHolder对象
	// holder = surface.getHolder();
	// paint = new Paint();
	// paint.setColor(Color.GREEN);
	// paint.setStrokeWidth(3);
	//
	// Button sin =(Button)findViewById(R.id.sin);
	// Button cos =(Button)findViewById(R.id.cos);
	// OnClickListener listener = (new OnClickListener() {
	//
	// @Override
	// public void onClick(final View source) {
	// // TODO Auto-generated method stub
	// drawBack(holder);
	// cx = X_OFFSET;
	// if(task != null){
	// task.cancel();
	// }
	//
	// task = new TimerTask() {
	//
	// @Override
	// public void run() {
	// int cy = source.getId() == R.id.sin ? centerY -(int)(100 * Math.sin((cx
	// -5) *2 * Math.PI/150)):
	// centerY -(int)(100 * Math.cos((cx-5)*2*Math.PI/150));
	// Canvas canvas = holder.lockCanvas(new Rect(cx,cy-2,cx+2,cy+2));
	// canvas.drawPoint(cx, cy, paint);
	// cx++;
	//
	// if(cx >WIDTH){
	//
	// task.cancel();
	// task = null;
	//
	// }
	// holder.unlockCanvasAndPost(canvas);
	// }
	// };
	// timer.schedule(task, 0,30);
	//
	// }
	// });
	//
	// sin.setOnClickListener(listener);
	// cos.setOnClickListener(listener);
	// holder.addCallback(new Callback() {
	// public void surfaceChanged(SurfaceHolder holder,int format,int width,int
	// height){
	// drawBack(holder);
	// }
	//
	// @Override
	// public void surfaceCreated(SurfaceHolder holder) {
	// // TODO Auto-generated method stub
	// }
	//
	// @Override
	// public void surfaceDestroyed(SurfaceHolder holder) {
	// // TODO Auto-generated method stub
	// timer.cancel();
	// }
	//
	// });
	//
	//
	// }
	//
	//
	// private void drawBack(SurfaceHolder holder){
	// Canvas canvas = holder.lockCanvas();
	// //绘制白色背景
	// canvas.drawColor(Color.WHITE);
	// Paint p = new Paint();
	// p.setColor(Color.BLACK);
	// p.setStrokeWidth(2);
	//
	// //绘制坐标轴
	// canvas.drawLine(X_OFFSET, centerY, WIDTH, centerY, p);
	// canvas.drawLine(X_OFFSET, 40, X_OFFSET, HEIGHT, p);
	// holder.unlockCanvasAndPost(canvas);
	// holder.lockCanvas(new Rect(0,0,0,0));
	// holder.unlockCanvasAndPost(canvas);
	//
	// }
	SurfaceView surface;
	SurfaceHolder holder;
	Paint paint;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.surface_view);

		surface = (SurfaceView) findViewById(R.id.surfaceview);
		holder = surface.getHolder();
		paint = new Paint();
		holder.addCallback(new Callback() {

			@Override
			public void surfaceDestroyed(SurfaceHolder holder) {
			}

			@Override
			public void surfaceCreated(SurfaceHolder holder) {
				Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
						R.drawable.s1);
				Canvas canvas = holder.lockCanvas();
				canvas.drawColor(Color.WHITE);
				canvas.drawBitmap(bitmap, 0, 0, null);
				holder.unlockCanvasAndPost(canvas);
				holder.lockCanvas(new Rect(0, 0, 0, 0));
				holder.unlockCanvasAndPost(canvas);
			}

			@Override
			public void surfaceChanged(SurfaceHolder holder, int format,
					int width, int height) {
			}
		});

		surface.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					int cx = (int) event.getX();
					int cy = (int) event.getY();

					Canvas canvas = holder.lockCanvas(new Rect(cx - 50,
							cy - 50, cx + 50, cy + 50));
					canvas.save();
					canvas.rotate(30, cx, cy);
					paint.setColor(Color.RED);
					canvas.drawRect(cx - 30, cy - 30, cx, cy, paint);
					canvas.restore();
					paint.setColor(Color.GREEN);
					canvas.drawRect(cx, cy, cx + 30, cy + 30, paint);
					holder.unlockCanvasAndPost(canvas);
					holder.lockCanvas(new Rect(0, 0, 0, 0));
					holder.unlockCanvasAndPost(canvas);
				}
				return false;
			}
		});
	}

}
