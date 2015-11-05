package com.atense.httpserver.test;

import java.util.HashMap;

import org.apache.http.protocol.HttpRequestHandler;

import com.atense.httpserver.HttpServerManager;

public class HttpServerTest {
	
	public static void main(String[] args) {
		HashMap<String, HttpRequestHandler> registers = new HashMap<String, HttpRequestHandler>();
		registers.put("*.do", new WebRequestHandler());
		registers.put("*", new HttpFileHandler());
		HttpServerManager.start("rootPath", registers);
	}

}
