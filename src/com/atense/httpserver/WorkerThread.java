/*
 *  WorkerThread.java
 *
 *  Created by Kyle on 2015/11/5.
 *  Copyright (c) 2015 ATENSE. All rights reserved.
 */
package com.atense.httpserver;

import java.io.IOException;

import org.apache.http.ConnectionClosedException;
import org.apache.http.HttpException;
import org.apache.http.HttpServerConnection;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpService;

/**
 * 
 * @author <a href="#" target="_black">Kyle</a> 2015-11-05
 */
public class WorkerThread extends Thread {
	private final HttpService httpservice;
	private final HttpServerConnection conn;
	private final HttpContext context;

	public WorkerThread(final HttpService httpservice,
			final HttpServerConnection conn, HttpContext context) {
		super();
		this.httpservice = httpservice;
		this.conn = conn;
		this.context = context;
	}

	public void run() {
		try {
			while (!Thread.interrupted() && this.conn.isOpen()) {
				if (this.httpservice != null && this.context != null) {
					this.httpservice.handleRequest(this.conn, this.context);
				}
			}
		} catch (ConnectionClosedException ex) {
			System.err.println("Client closed connection");
		} catch (IOException ex) {
			System.err.println("I/O error: " + ex.getMessage());
		} catch (HttpException ex) {
			System.err.println("Unrecoverable HTTP protocol violation: "
					+ ex.getMessage());
		} finally {
			try {
				this.conn.shutdown();
			} catch (IOException ignore) {
			}
		}
	}
}
