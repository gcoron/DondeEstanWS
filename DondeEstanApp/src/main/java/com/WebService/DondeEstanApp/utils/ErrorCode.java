package com.WebService.DondeEstanApp.utils;

import org.json.JSONObject;

public class ErrorCode {

	private int code;
	private String status;
	
	public ErrorCode() {
	}
	
	public ErrorCode(int code, String status) {
		this.code = code;
		this.status = status;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		JSONObject response = new JSONObject();
		response.put("code", this.code);
		response.put("status", this.status);
		return response.toString();
	}
	
	
}
