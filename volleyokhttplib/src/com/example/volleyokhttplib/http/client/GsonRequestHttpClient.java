package com.example.volleyokhttplib.http.client;

import com.android.volley.Request;
import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.volleyokhttplib.http.common.OnResponseListener;
import com.example.volleyokhttplib.http.request.GsonRequest;

public class GsonRequestHttpClient<T> extends BaseHttpClient<T> {

	private OnResponseListener<T> mOnResponseListener;
	private Class<T> mClazz;

	private GsonRequestHttpClient(BaseBuilder<T> builder) {

		initPropertys(builder);

		initLocalPropertys(builder);

		GsonRequest<T> gsonRequest = (GsonRequest<T>) createRequestByMethod(mMethod);

		gsonRequest.setRetryPolicy(mRetryPolicy);

		addRequestByTag(gsonRequest, mTag);

	}

	private void initLocalPropertys(BaseBuilder<T> builder) {
		this.mOnResponseListener = builder.onResponseListener;
		this.mClazz = builder.clazz;
	}

	public static class GetBuilder<T> extends BaseBuilder<T> {

		public GetBuilder(Class<T> beanClazz){
			this.clazz = beanClazz;
			method = Method.GET;
		}

		@Override
		public BaseHttpClient<T> execute() {
			if(checkUrl()){
				return new GsonRequestHttpClient<T>(this);
			}
			return null;
		}
		
	}
	
	public static class PostBuilder<T> extends BaseBuilder<T> {
		
		public PostBuilder(Class<T> classz){
			this.clazz = classz;
			method = Method.POST;
		}
		
		@Override
		public BaseHttpClient<T> execute() {
			if(checkUrl()){
				return new GsonRequestHttpClient<T>(this);
			}
			return null;
		}
	}

	@Override
	protected Request<T> createPostRequest() {
		
		GsonRequest<T> gsonRequest = new GsonRequest<T>(Method.POST, mUrl, mHeaders, mParams, mClazz,
				new Response.Listener<T>() {
					@Override
					public void onResponse(T response) {
						if(null != mOnResponseListener){
							mOnResponseListener.onSuccess(response);
						}
					}
				}, 
				new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						if(null != mOnResponseListener){
							mOnResponseListener.onError(error);
						}
					}
				})
				{
    				@Override
    				public Priority getPriority() {
    					return mPriority;
    				}
				};
		return (Request<T>) gsonRequest;
	}

	@Override
	protected Request<T> createGetRequest() {
		
		GsonRequest<T> gsonRequest = new GsonRequest<T>(getUrl(mUrl, mParams), mHeaders, mClazz,
				new Response.Listener<T>() {

					@Override
					public void onResponse(T response) {
						if(null != mOnResponseListener){
							mOnResponseListener.onSuccess(response);
						}
					}
				}, 
				new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						if(null != mOnResponseListener){
							mOnResponseListener.onError(error);
						}
					}
				})
				{
					@Override
					public Priority getPriority() {
						return mPriority;
					}
				};
		
		return (Request<T>) gsonRequest;
	}

}
