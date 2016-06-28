package com.example.volleyokhttplib;

import java.io.Serializable;
import java.util.List;

public class BaseData<T> implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private String sessionID;
	
	private List<T> data;

	private int code = -1;
	
	private String error;
	
	public String getSessionID() {
		return sessionID;
	}

	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

}
