/*
 *  Words.java
 *
 *  Created by admin on 2015年9月29日.
 *  Copyright (c) 2015 Atense. All rights reserved.
 */
package com.atense.model;

import android.net.Uri;

/**
 * 
 * @author Admin
 */
public final class Words {

	public static final String AUTHORITY = "com.atense.providers.dictprovider";

	public static final class Word {
		public static final String _ID = "_id";
		public static final String WORD = "word";
		public static final String DETAIL = "detail";
		public static final Uri DICT_CONTENT_URI = Uri.parse("content://"
				+ AUTHORITY + "/words");
		public static final Uri WORD_CONTENT_URI = Uri.parse("content://"
				+ AUTHORITY + "/word");
	}

}
