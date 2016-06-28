package com.example.volleyokhttplib.http.client;

import com.android.volley.Request;
import com.android.volley.Request.Method;
import com.android.volley.Request.Priority;
import com.android.volley.RetryPolicy;
import com.example.volleyokhttplib.http.common.VolleyRequestQueue;

import java.util.Iterator;
import java.util.Map;

public abstract class BaseHttpClient<T> {
	
	protected int mMethod;
	protected String mUrl;
	protected Map<String, String> mHeaders;
	protected Map<String, String> mParams;
	protected Object mTag;
	protected Priority mPriority;
	protected RetryPolicy mRetryPolicy;
	
	protected void initPropertys(BaseBuilder<T> builder) {
		this.mUrl = builder.url;
		this.mParams = builder.params;
		this.mTag = builder.tag;
		this.mHeaders = builder.headers;
		this.mPriority = builder.priority;
		this.mRetryPolicy = builder.retryPolicy;
		this.mMethod = builder.method;
	}
	
	protected void addRequestByTag(Request<T> request, Object tag) {
		if(null != tag){
			VolleyRequestQueue.getInstance().addRequest(request, tag);
		}else{
			VolleyRequestQueue.getInstance().addRequest(request);
		}
	}
	
	protected Request<T> createRequestByMethod(int method) {
		if(method == Method.POST){
			return createPostRequest();
		}
		return createGetRequest();
	}
	
	protected abstract Request<T> createPostRequest();
	protected abstract Request<T> createGetRequest();
	
	protected static String getUrl(String url, Map<String, String> params) {
		if(url == null){
			return null;
		}
        if (params != null) {
            Iterator<String> it = params.keySet().iterator();
            StringBuffer sb = null;
            while (it.hasNext()) {
                String key = it.next();
                String value = params.get(key);
                if (sb == null) {
                    sb = new StringBuffer();
                    if(url.indexOf("?") < 0){
                    	 sb.append("?");
                    }else{
                    	sb.append("&");
                    }
                } else {
                    sb.append("&");
                }
                sb.append(key);
                sb.append("=");
                sb.append(value);
            }
            url += sb.toString();
        }
        return url;
    }
	
}
