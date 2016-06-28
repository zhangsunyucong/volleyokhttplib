package com.example.volleyokhttplib.http.common;


import com.android.volley.DefaultRetryPolicy;

public class HttpConstants {
	public static final int DEFAULT_TIME_OUT_MS = 10000;
	public static final int DEFAULT_MAX_RETRIES = DefaultRetryPolicy.DEFAULT_MAX_RETRIES;
	public static final float DEFAULT_BACKOFF_MULT =  DefaultRetryPolicy.DEFAULT_BACKOFF_MULT;
	
	public static final String DEFAULT_SESSION_TOKEN_KEY =  "sessionToken";
	public static final String DEFAULT_USER_PHONE_NUMBER = "userPhoneNumber";
	public static final String DEFAULT_MOBILE_PHONE_NUMBER = "mobilePhoneNumber";
	
	public static final String REQUEST_ID = "requestId";
	public static final String REQUEST_KEY = "requestKey";
}
