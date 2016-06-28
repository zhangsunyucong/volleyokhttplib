package com.example.volleyokhttplib.http.common;

import com.android.volley.VolleyError;

public interface OnResponseListener<T> {
	public void onSuccess(T response);
	public void onError(VolleyError error);
}
