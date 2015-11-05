package com.atense.httpserver.test;

import org.json.JSONObject;

public class BaseHandler {
	protected String contentType = "application/json; charset=utf-8";
	protected JSONObject parameter;

	public String getContentType() {
		return contentType;
	}

	public void setParameter(JSONObject parameter) {
		this.parameter = parameter;
	}

}
