/*
 *  BookContent.java
 *
 *  Created by admin on 2015年9月17日.
 *  Copyright (c) 2015 Atense. All rights reserved.
 */
package com.atense.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author Admin
 */
public class BookContent {

	public static class Book {

		public int id;
		public String title;
		public String desc;

		public Book(int id, String title, String desc) {
			this.id = id;
			this.title = title;
			this.desc = desc;
		}

		@Override
		public String toString() {
			return title;
		}

	}

	public static List<Book> ITEMS = new ArrayList<Book>();
	public static Map<Integer, Book> ITEM_MAP = new HashMap<Integer, Book>();

	static {
		addItem(new Book(1, "疯狂Java讲义", "深入学习JAVA的教材"));
		addItem(new Book(2, "疯狂Xml讲义", "深入学习Xml的教材"));
		addItem(new Book(3, "疯狂Android讲义", "深入学习Android的教材"));
	}

	private static void addItem(Book book) {
		ITEMS.add(book);
		ITEM_MAP.put(book.id, book);
	}
}
