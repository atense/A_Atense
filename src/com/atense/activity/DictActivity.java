/*
 *  TestActivity.java
 *
 *  Created by admin on 2015年6月23日.
 *  Copyright (c) 2015 Atense. All rights reserved.
 */
package com.atense.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

import com.atense.R;
import com.atense.db.DictDatabaseHelper;

/**
 * 
 * @author Admin
 */
public class DictActivity extends Activity {

	DictDatabaseHelper helper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dict);

		helper = new DictDatabaseHelper(this, "dict_db.db3", null, 1);

		findViewById(R.id.insert).setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				insertData(helper.getReadableDatabase(),
						((EditText) findViewById(R.id.word)).getText()
								.toString(),
						((EditText) findViewById(R.id.detail)).getText()
								.toString());
				Toast.makeText(DictActivity.this, "添加生词成功", 8000).show();
			}
		});
		findViewById(R.id.search).setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				String key = ((EditText) findViewById(R.id.key)).getText()
						.toString();
				Cursor cursor = helper
						.getReadableDatabase()
						.rawQuery(
								"select * from dict where word like ? or detail like ?",
								new String[] { "%" + key + "%", "%" + key + "%" });
				Bundle data = new Bundle();
				data.putSerializable("data", convertCursorToList(cursor));
				cursor.close();
				Intent intent = new Intent(DictActivity.this,
						ResultActivity.class);
				intent.putExtras(data);
				startActivity(intent);
			}
		});
	}

	protected ArrayList<Map<String, Object>> convertCursorToList(Cursor cursor) {
		ArrayList<Map<String, Object>> result = new ArrayList<Map<String, Object>>();

		while (cursor.moveToNext()) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put(com.atense.model.Words.Word.WORD, cursor.getString(1));
			map.put(com.atense.model.Words.Word.DETAIL, cursor.getString(2));
			result.add(map);
		}

		return result;
	}

	private void insertData(SQLiteDatabase db, String word, String detail) {
		ContentValues values = new ContentValues();
		values.put(com.atense.model.Words.Word.WORD, word);
		values.put(com.atense.model.Words.Word.DETAIL, detail);
		db.insert("dict", null, values);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (helper != null) {
			helper.close();
		}
	}

}
