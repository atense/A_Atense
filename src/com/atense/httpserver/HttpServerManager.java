/*
 *  HttpServerManager.java
 *
 *  Created by Kyle on 2015/11/05.
 *  Copyright (c) 2015 ATENSE. All rights reserved.
 */
package com.atense.httpserver;

import java.io.IOException;
import java.util.Map;

import org.apache.http.protocol.HttpRequestHandler;

import com.atense.util.RandomUtils;

/**
 * 
 * @author <a href="#" target="_black">Kyle</a> 2015-11-05
 */
public class HttpServerManager {
	private static Thread listenerThread = null;
	private static int port = 8080;

	/**
	 * 
	 * @param rootPath
	 *            Web Content Root path<br>
	 *            eg: http://127.0.0.1:port/web/main.html<br>
	 *            rootPath is web folder parent folder absolute path
	 * @param registers
	 */
	public static void start(String rootPath,
			Map<String, HttpRequestHandler> registers) {
		while (true) {
			try {
				listenerThread = new RequestListenerThread(port, rootPath,
						registers);
				listenerThread.setDaemon(false);
				listenerThread.start();
				break;
			} catch (IOException e) {
				port += RandomUtils.getRandom(10); //getRandom();
			}
		}
	}

	public static int getPort() {
		return port;
	}

	
	@SuppressWarnings("unused")
	private static int getRandom() {
		Double randomDouble = Math.random() * 10;
		int random = randomDouble.intValue();
		return random <= 0 ? 1 : random;
	}
}
