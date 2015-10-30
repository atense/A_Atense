/*
 *  DictDatabasehelper.java
 *
 *  Created by admin on 2015年9月29日.
 *  Copyright (c) 2015 Atense. All rights reserved.
 */
package com.atense.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 
 * @author Admin
 */
public class DictDatabaseHelper extends SQLiteOpenHelper {

	private final String CREATE_TABLE_SQL = "create table dict(_id integer primary key autoincrement, word, detail)";

	public DictDatabaseHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_TABLE_SQL);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

}
