/*
 *  TestActivity.java
 *
 *  Created by admin on 2015年6月23日.
 *  Copyright (c) 2015 Atense. All rights reserved.
 */
package com.atense.activity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.atense.R;

/**
 * 
 * @author Admin
 */
public class AsyncTaskActivity extends Activity {

	TextView text;
	Button btn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.asynctask);

		btn = (Button) findViewById(R.id.button);
		text = (TextView) findViewById(R.id.text);
		btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				try {
					download();
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

	}

	public void download() throws MalformedURLException {
		DownloadTask task = new DownloadTask(this);
		task.execute(new URL(
				"http://blog.csdn.net/huguangshanse00/article/details/38780895"));
	}

	class DownloadTask extends AsyncTask<URL, Integer, String> {
		Context context;
		ProgressDialog pdialog;
		int hasRead = 0;

		public DownloadTask(Context context) {
			this.context = context;
		}

		@Override
		protected String doInBackground(URL... params) {
			Log.e("AsyncTask", "doInBackground");
			StringBuffer sb = new StringBuffer();
			try {
				URLConnection conn = params[0].openConnection();
				BufferedReader br = new BufferedReader(new InputStreamReader(
						conn.getInputStream(), "utf-8"));
				String line = null;
				while ((line = br.readLine()) != null) {
					sb.append(line + "\n");
					hasRead++;
					publishProgress(hasRead);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			return sb.toString();
		}

		@Override
		protected void onPreExecute() {
			Log.e("AsyncTask", "onPreExecute");
			pdialog = new ProgressDialog(context);
			pdialog.setTitle("任务正在执行中");
			pdialog.setMessage("任务正在执行中， 请等待...");
			pdialog.setCancelable(false);
			pdialog.setMax(200);
			pdialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			pdialog.setIndeterminate(false);
			pdialog.show();
		}

		@Override
		protected void onPostExecute(String result) {
			Log.e("AsyncTask", "onPostExecute");
			text.setText(result);
			pdialog.dismiss();
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			Log.e("AsyncTask", "onProgressUpdate");
			text.setText("已经读取了【" + values[0] + "】行！");
			pdialog.setProgress(values[0]);
		}

	}
}
