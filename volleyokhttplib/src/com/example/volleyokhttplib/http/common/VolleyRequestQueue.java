package com.example.volleyokhttplib.http.common;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.example.volleyokhttplib.MyApplication;


/**
 * 请求队列管理
 * @author heyunjian
 *
 */
public class VolleyRequestQueue {
	
	 private static VolleyRequestQueue mVolleyRequestQueue;
	 private static RequestQueue mRequestQueue;
	 
	 private VolleyRequestQueue(){}
	 
	 public static VolleyRequestQueue getInstance(){
		 if (mVolleyRequestQueue == null) {
			 synchronized (VolleyRequestQueue.class) {
				 if (mVolleyRequestQueue == null) {
					 mVolleyRequestQueue = new VolleyRequestQueue();
					 if(MyApplication.getApplication() != null 
							 && MyApplication.getApplication().getRequestQueue() != null){
						 mRequestQueue = MyApplication.getApplication().getRequestQueue();
					 }
				 }
			}
		}
		return mVolleyRequestQueue;
	 }
	 
	 /**
     * 娣诲姞Request璇锋眰鍒伴槦鍒椾腑锛屽洜涓烘垜浠娇鐢∣kHttp浣滀负Volley鐨勪紶杈撳眰锛屾墍浠ュ鍔犱竴涓狧ttpStack鍙傛暟
     * @param request
     */
    public void addRequest(Request<?> request) {
        if (request != null) {
            if (getRequestQueue() != null) {
            	getRequestQueue().add(request);
            }
        }
    }
    
    /**
     * 娣诲姞Request璇锋眰鍒伴槦鍒椾腑锛屽洜涓烘垜浠娇鐢∣kHttp浣滀负Volley鐨勪紶杈撳眰锛屾墍浠ュ鍔犱竴涓狧ttpStack鍙傛暟
     * @param request
     */
    public void addRequest(Request<?> request, Object tag) {
        if (request != null && tag != null) {
        	 request.setTag(tag);
        	 if (getRequestQueue() != null) {
             	getRequestQueue().add(request);
             }
        } else if(tag == null){
        	Log.e("HttpUtils", "tag涓虹┖鍝�");
        }
    }
    
    /**
     * 鏍规嵁鏍囩鍙栨秷鎸囧畾Request
     * @param tag
     */
    public void cancelRequestByTag(Object tag) {
        if (tag != null) {
            if (getRequestQueue() != null) {
                getRequestQueue().cancelAll(tag);
            }
        }
    }
    
    /**
     * 鍙栨秷鎵�鏈塕equest
     */
    public void cancelAllRequest() {
        RequestQueue localRequestQueue = getRequestQueue();
        if (localRequestQueue != null) {
            localRequestQueue.cancelAll(new RequestQueue.RequestFilter() {

                @Override
                public boolean apply(Request<?> request) {
                    return true;
                }
            });
            localRequestQueue.stop();
        }
    }
    
    public void clear(){
    	 cancelAllRequest();
	     mRequestQueue = null;
    }
    
    public RequestQueue getRequestQueue() {
	      return mRequestQueue;
	 }

	 public void setRequestQueue(RequestQueue paramRequestQueue) {
		 mRequestQueue = paramRequestQueue;
	 }
	
}
