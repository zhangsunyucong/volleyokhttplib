package com.example.volleyokhttplib;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.volleyokhttplib.http.common.OkHttpStack;
import com.squareup.okhttp.OkHttpClient;

import android.app.Application;

public class MyApplication extends Application{
	
	private RequestQueue mRequestQueue;
	private static MyApplication mApplication;
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		mApplication = this;
		initVolley();
	}
	
	private void initVolley() {
		mRequestQueue = Volley
                .newRequestQueue(this,
               		 new OkHttpStack(new OkHttpClient()));
	}
	
	public static MyApplication getApplication() {
		return mApplication;
	}
	
	@Override
	public void onTerminate() {
		// TODO Auto-generated method stub
		super.onTerminate();
	}
	
	public RequestQueue getRequestQueue(){
		return mRequestQueue;
	}

}
