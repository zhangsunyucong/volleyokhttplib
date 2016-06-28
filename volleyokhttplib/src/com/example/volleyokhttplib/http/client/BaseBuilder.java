package com.example.volleyokhttplib.http.client;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request.Method;
import com.android.volley.Request.Priority;
import com.android.volley.RetryPolicy;
import com.example.volleyokhttplib.http.common.HttpConstants;
import com.example.volleyokhttplib.http.common.OnResponseListener;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public abstract class BaseBuilder<T> {
	
	protected int method = Method.GET;
	protected String url;
	protected Map<String, String> headers;
	protected Map<String, String> params;
	protected Class<T> clazz;
	protected Object tag;
	protected OnResponseListener<T> onResponseListener;
	protected int timeOutMS = HttpConstants.DEFAULT_TIME_OUT_MS;
	protected Priority priority = Priority.NORMAL;
	protected RetryPolicy retryPolicy = new DefaultRetryPolicy(timeOutMS,
			HttpConstants.DEFAULT_MAX_RETRIES, HttpConstants.DEFAULT_BACKOFF_MULT);

	public BaseBuilder<T> url(String url){
		this.url = url;
		return this;
	}

	public BaseBuilder<T> param(String key, Object value){
		if(params == null){
			params = new HashMap<String, String>();
		}
		params.put(key, String.valueOf(value));
		return this;
	}

	public BaseBuilder<T> timeOut(int timeOut){
		timeOutMS = timeOut;
		return this;
	}

	public BaseBuilder<T> addParams(Map<String, Object> params){
		if(this.params == null){
			this.params = new HashMap<String, String>();
		}
		Iterator<Entry<String, Object>> entries = params.entrySet().iterator();
		while (entries.hasNext()) {
		    Entry<String, Object> entry = entries.next();
		    this.params.put(entry.getKey(), String.valueOf(entry.getValue()));
		}
		return this;
	}

	public BaseBuilder<T> tag(Object tag){
		this.tag = tag;
		return this;
	}

	public BaseBuilder<T> headers(Map<String, String> headers){
		this.headers = headers;
		return this;
	}

	public BaseBuilder<T> header(String key, String value){
		if(headers == null){
			headers = new HashMap<String, String>();
		}
		headers.put(key, value);
		return this;
	}

	public BaseBuilder<T> priority(Priority priority){
		this.priority = priority;
		return this;
	}

	public BaseBuilder<T> retryPolicy(RetryPolicy retryPolicy){
		this.retryPolicy = retryPolicy;
		return this;
	}

	public BaseBuilder<T> setOnResponseListener(OnResponseListener<T> listener){
		this.onResponseListener = listener;
		return this;
	}
	
	public abstract BaseHttpClient<T> execute();
	
	protected boolean checkUrl(){
		return (url != null && !url.equals(""));
	}

}
