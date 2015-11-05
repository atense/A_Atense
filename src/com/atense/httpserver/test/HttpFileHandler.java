//
//  HttpFileHandler.java
//  Sprayer
//
//  Created by admin on 1/30/15.
//  Copyright (c) 2015   MRF. All rights reserved.
//
package com.atense.httpserver.test;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Locale;

import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.MethodNotSupportedException;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpRequestHandler;

import android.util.Log;

public class HttpFileHandler implements HttpRequestHandler {

	public HttpFileHandler() {
		super();
	}

	private static Dictionary<String, String> MIME_TYPES = new Hashtable<String, String>();
	static {
		MIME_TYPES.put("htm", "text/html");
		MIME_TYPES.put("html", "text/html");
		MIME_TYPES.put("css", "text/css");
		MIME_TYPES.put("js", "application/x-javascript");
		MIME_TYPES.put("pdf", "application/pdf");
		MIME_TYPES.put("txt", "text/plain");
		MIME_TYPES.put("xml", "text/xml");
		MIME_TYPES.put("svg", "image/svg+xml");
		MIME_TYPES.put("jpg", "image/jpeg");
		MIME_TYPES.put("png", "image/png");
		MIME_TYPES.put("bmp", "image/bmp");
		MIME_TYPES.put("gif", "image/gif");
		MIME_TYPES.put("mp3", "audio/mpeg");
		MIME_TYPES.put("wav", "audio/x-wav");
		MIME_TYPES.put("ogv", "'video/ogg");
		MIME_TYPES.put("mp4", "'video/mp4");
		MIME_TYPES.put("WebM", "'video/WebM");
		MIME_TYPES.put("avi", "video/x-msvideo");
	}

	public String getContentType(String uri) {
		int endPos = uri.indexOf('?');
		if (endPos < 0) {
			endPos = uri.length();
		}
		int startPos = uri.lastIndexOf('.', endPos - 1);
		if (startPos < 0)
			return "text/html";
		startPos++;
		String fileExt = uri.substring(startPos, endPos);
		String mimeType = MIME_TYPES.get(fileExt);
		if (mimeType == null || mimeType.length() == 0) {
			return "application/octet-stream";
		} else {
			return mimeType;
		}

	}

	@SuppressWarnings("deprecation")
	public void handle(final HttpRequest request, final HttpResponse response,
			final HttpContext context) throws HttpException, IOException {
		String method = request.getRequestLine().getMethod()
				.toUpperCase(Locale.ENGLISH);
		if (!method.equals("GET") && !method.equals("POST")) {
			throw new MethodNotSupportedException(method
					+ " method not supported");
		}
		String target = request.getRequestLine().getUri();
		int pos = target.indexOf('?');
		if (pos > 0) {
			target = target.substring(0, pos);
		}
		if (target.indexOf("/localtile/") >= 0) {
			Log.i("HttpFileHandler", target);
			// String taskDir = MRFUtil.sharedInstance()
			// .getCachedParameter("CURRENT_TASK_DIR").toString();
			// String szMapViewName = String.format("%s/TileLayer/", taskDir);
			// target = target.replaceAll("/localtile/", szMapViewName);
			target = target.substring(target.indexOf("/localtile/"));
			Log.i("HttpFileHandler localtile:", target);
		} else if (target.indexOf("/localcache/") >= 0) {
			Log.i("HttpFileHandler", target);
			// String taskDir = MRFUtil.sharedInstance()
			// .getCachedParameter("CURRENT_TASK_PDF_DIR").toString()+File.separator;
			// target = target.replaceAll("/localcache/download/", taskDir);
			target = target.substring(target.indexOf("/localcache/"));
			Log.i("HttpFileHandler pdf:", target);
		}
		target = URLDecoder.decode(target);
		response.setHeader("Connection", "Keep-Alive");
		File file = new File((String) context.getAttribute("WebRoot"), target);
		if (!file.exists()) {
			File file1 = new File(target);
			if (file1.exists()) {
				try {
					response.setStatusCode(HttpStatus.SC_OK);
					FileEntity body = new FileEntity(file1,
							this.getContentType(target));
					response.setEntity(body);
				} catch (Exception e) {
					String msg = e.getClass().getName() + ":" + e.getMessage();
					response.setEntity(new StringEntity(msg));
					e.printStackTrace();
				}
			} else {
				response.setStatusCode(HttpStatus.SC_NOT_FOUND);
				response.setEntity(new StringEntity("File Not Found."));
				Log.i("HttpFileHandler File Not Found:", target);
			}
		} else if (!file.canRead() || file.isDirectory()) {
			response.setStatusCode(HttpStatus.SC_FORBIDDEN);
			response.setEntity(new StringEntity("Access Denied."));
		} else {
			try {
				response.setStatusCode(HttpStatus.SC_OK);
				FileEntity body = new FileEntity(file,
						this.getContentType(target));
				response.setEntity(body);
			} catch (Exception e) {
				String msg = e.getClass().getName() + ":" + e.getMessage();
				response.setEntity(new StringEntity(msg));
				e.printStackTrace();
			}
		}
	}
}
