package com.example.volleyokhttplib.http.request;

import java.util.Iterator;
import java.util.Map;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.RetryPolicy;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;

/**
 * 自定义的GsonRequest
 * @author heyunjian
 *
 * @param <T>
 */
public class GsonRequest<T> extends Request<T> {

    private final Listener<T> mListener;
    private static Gson mGson = new Gson();
    private Class<T> mClass;
    private Map<String, String> mParams;
    private  Map<String, String> mHeaders;
    private int TIMEOUT_MS = 3000;

    public GsonRequest(int method, String url, Map<String, String> headers, Map<String, String> params, Class<T> clazz,
    		Listener<T> listener, ErrorListener errorListener){
    	 super(method, (method == Method.GET) ? (getUrl(url, params)) : url, errorListener);
        mClass = clazz;
        mListener = listener;
        if(method == Method.POST){
        	this.mParams = params;
        }
        this.mHeaders = headers;
        initRetryPolicy();
    }
    
    public GsonRequest(String url, Map<String, String> headers, Class<T> clazz, Listener<T> listener,
            ErrorListener errorListener) {
    	this(Method.GET, url, headers, null, clazz, listener, errorListener);
    } 
    
    private void initRetryPolicy() {
   	 	RetryPolicy retryPolicy = new DefaultRetryPolicy(TIMEOUT_MS, 
        		DefaultRetryPolicy.DEFAULT_MAX_RETRIES, 
        		DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        setRetryPolicy(retryPolicy);
	}

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
    	if(mHeaders != null){
    		return mHeaders;
    	}else{
    		return super.getHeaders();
    	}
    }
    
    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
    	return mParams;
    }
    
    @Override
	protected void deliverResponse(T response) {
		mListener.onResponse(response);
	}

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
    	try {
    		if(mClass == null){
    			throw new IllegalArgumentException("Gson 的 beanClass 不能为null！");
    		}
            T result;  
            String jsonStr = new String(response.data, "UTF-8");
            
            result = mGson.fromJson(jsonStr, mClass); 
            return Response.success(result,
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (Exception e) {
            e.printStackTrace();
            return Response.error(new ParseError(e));
        }
    }

    private static String getUrl(String url, Map<String, String> params) {
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