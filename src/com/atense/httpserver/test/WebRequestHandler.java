package com.atense.httpserver.test;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.util.Iterator;

import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHttpEntityEnclosingRequest;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpRequestHandler;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.util.Log;

@SuppressLint("DefaultLocale")
public class WebRequestHandler implements HttpRequestHandler {
	private static int BUFFER_SIZE = 10240;

	private JSONObject parameter = new JSONObject();

	@Override
	public void handle(HttpRequest request, HttpResponse response,
			HttpContext context) throws HttpException, IOException {
		try {
			String url = request.getRequestLine().getUri();
			parseUrlParameters(url);

			int idx = url.indexOf(".do");
			if (idx != -1) {
				String preUrl = url.substring(0, idx);
				String className = preUrl
						.substring(preUrl.lastIndexOf("/") + 1);
				String methodName = String.valueOf(parameter.get("M"));

				if (request.getRequestLine().getMethod().equals("POST")) {
					BasicHttpEntityEnclosingRequest baseRequest = (BasicHttpEntityEnclosingRequest) request;
					long len = baseRequest.getEntity().getContentLength();
					String body = null;
					if (len > 0) {
						InputStream is = baseRequest.getEntity().getContent();
						byte[] read = new byte[BUFFER_SIZE];
						StringBuilder builder = new StringBuilder();
						int count = 0;
						while ((count = is.read(read, 0, BUFFER_SIZE)) > 0) {
							String str = new String(read, 0, count);
							builder.append(str);
						}

						body = builder.toString();

						if ("application/json".equals(baseRequest.getEntity()
								.getContentType().getValue())) {
							@SuppressWarnings("deprecation")
							JSONObject ps = new JSONObject(
									URLDecoder.decode(body));
							Iterator<String> it = ps.keys();
							while (it.hasNext()) {
								String key = it.next();
								Object value = ps.get(key);
								parameter.put(key, String.valueOf(value));
							}

						} else {
							parameter.put("body_stream", body);
						}
					}
				}

				String fullClassName = String.format("com.atense.httpserver.test.%s",
						className);
				Class<?> clazz = Class.forName(fullClassName);
				BaseHandler handler = (BaseHandler) clazz.newInstance();
				Method method = clazz.getMethod(methodName, new Class[] {});
				handler.setParameter(parameter);
				method.setAccessible(true);
				String msg = (String) method.invoke(handler, new Object[] {});
				StringEntity entity = new StringEntity(msg);
				entity.setContentType("application/json");
				response.setEntity(entity);
			} else {
				StringEntity entity = new StringEntity(
						"{\"success\":false, \"message\": \"URL Failed!\"}");
				entity.setContentType("application/json");
				response.setEntity(entity);
			}
		} catch (Exception ex) {
			StringEntity entity = new StringEntity(
					"{\"success\":false, \"message\": \"URL Failed!\"}");
			entity.setContentType("application/json");
			response.setEntity(entity);
		}

	}

	public void parseUrlParameters(String urlPath) {
		try {
			if (urlPath == null || urlPath.length() == 0
					|| !urlPath.contains("?")) {
				return;
			}

			// get the url parameter string from url path
			int paramIndex = urlPath.indexOf("?");
			String paramString = urlPath.substring(paramIndex + 1);
			paramString = java.net.URLDecoder.decode(paramString, "UTF-8");
			// convert url parameter string to array
			String[] paramArray = paramString.split("&");

			// visit the url parameter array, add each parameter(key/value) to
			// dictionary
			for (int i = 0; i < paramArray.length; i++) {
				String p = paramArray[i];
				String[] pArray = p.split("=");
				// '?' symbol identified as the external parameters
				String pKey = /* "?" + */pArray[0].toUpperCase();
				String pValue = "";
				if (pArray.length > 1) {
					pValue = pArray[1];
				}
				parameter.put(pKey, pValue);
			}

		} catch (Exception exception) {
			Log.i(this.getClass().getSimpleName(), String.format("Exception: %s",
									exception.getMessage()));
			return;
		}
	}

}
