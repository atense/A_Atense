/*
 *  RequestListenerThread.java
 *
 *  Created by Kyle on 2015/11/5.
 *  Copyright (c) 2015 ATENSE. All rights reserved.
 */
package com.atense.httpserver;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.http.impl.DefaultConnectionReuseStrategy;
import org.apache.http.impl.DefaultHttpResponseFactory;
import org.apache.http.impl.DefaultHttpServerConnection;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.BasicHttpProcessor;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpRequestHandler;
import org.apache.http.protocol.HttpRequestHandlerRegistry;
import org.apache.http.protocol.HttpService;
import org.apache.http.protocol.ResponseConnControl;
import org.apache.http.protocol.ResponseContent;
import org.apache.http.protocol.ResponseDate;
import org.apache.http.protocol.ResponseServer;

import android.util.Log;

/**
 * 
 * @author <a href="#" target="_black">Kyle</a> 2015-11-05
 */
public class RequestListenerThread extends Thread {
	private ServerSocket serverSocket;
	private final HttpParams params;
	private final HttpService httpService;
	private final HttpContext context = new BasicHttpContext(null);

	/**
	 * 
	 * @param port 端口
	 * @param docRoot 根目录
	 * @param registers 注册拦截信息
	 * @throws IOException
	 */
	public RequestListenerThread(int port, String docRoot,
			Map<String, HttpRequestHandler> registers) throws IOException {
		this.context.setAttribute("WebRoot", docRoot);
		this.serverSocket = new ServerSocket(port);
		this.params = new BasicHttpParams();
		this.params
				.setIntParameter(CoreConnectionPNames.SO_TIMEOUT, 360 * 1000)
				.setIntParameter(CoreConnectionPNames.SOCKET_BUFFER_SIZE,
						8 * 1024)
				.setBooleanParameter(
						CoreConnectionPNames.STALE_CONNECTION_CHECK, false)
				.setBooleanParameter(CoreConnectionPNames.TCP_NODELAY, true)
				.setParameter(CoreProtocolPNames.ORIGIN_SERVER,
						"HttpComponents/1.1");
		// Set up the HTTP protocol processor
		BasicHttpProcessor httpproc = new BasicHttpProcessor();
		httpproc.addInterceptor(new ResponseDate());
		httpproc.addInterceptor(new ResponseServer());
		httpproc.addInterceptor(new ResponseContent());
		httpproc.addInterceptor(new ResponseConnControl());
		// Set up request handlers
		HttpRequestHandlerRegistry reqistry = new HttpRequestHandlerRegistry();
		Iterator<String> patterns = registers.keySet().iterator();
		while (patterns.hasNext()) {
			String pattern = patterns.next();
			// reqistry.register("*.do", new HttpServletHandler());
			reqistry.register(pattern, registers.get(pattern));
		}
		// Set up the HTTP service
		this.httpService = new HttpService(httpproc,
				new DefaultConnectionReuseStrategy(),
				new DefaultHttpResponseFactory());
		this.httpService.setParams(this.params);
		this.httpService.setHandlerResolver(reqistry);
	}

	public void run() {
		ExecutorService pool = Executors.newFixedThreadPool(10);

		while (!Thread.interrupted()) {
			try {
				// Set up HTTP connection
				Socket socket = this.serverSocket.accept();
				DefaultHttpServerConnection conn = new DefaultHttpServerConnection();
				conn.bind(socket, this.params);
				// conn.set
				// Start worker thread
				Thread t = new WorkerThread(this.httpService, conn,
						this.context);
				t.setDaemon(true);
				pool.execute(t);
			} catch (InterruptedIOException ex) {
				break;
			} catch (IOException e) {
				Log.e("RequestListenerThread.run",
						"I/O error initialising connection thread: "
								+ e.getMessage());
				break;
			}
		}
		//
		try {
			this.serverSocket.close();
		} catch (IOException e) {

		}
		this.serverSocket = null;
	}
}
