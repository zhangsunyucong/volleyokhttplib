package com.example.volleyokhttplib.http.client;

import java.util.Map;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.volleyokhttplib.http.common.OnResponseListener;

public class StringRequestHttpClient extends BaseHttpClient<String> {
	
	protected OnResponseListener<String> mOnResponseListener;
	
	private StringRequestHttpClient(BaseBuilder<String> builder) {
		
		initPropertys(builder);
		
		initLocalPropertys(builder);
		
		StringRequest stringRequest = (StringRequest) createRequestByMethod(mMethod);
		
		stringRequest.setRetryPolicy(mRetryPolicy);
		
		addRequestByTag(stringRequest, mTag);
		
	}
	
	private void initLocalPropertys(BaseBuilder<String> builder) {
		this.mOnResponseListener = builder.onResponseListener;
	}

	/*public static PostBuilder post(){
		return new PostBuilder().post();
	}
	
	public static GetBuilder get(){
		return new GetBuilder().get();
	}*/

	public static class GetBuilder extends BaseBuilder<String>{
		
		public GetBuilder(){
			method = Method.GET;
		}
		
		@Override
		public BaseHttpClient<String> execute() {
			if(checkUrl()){
				return new StringRequestHttpClient(this);
			}
			return null;
		}
		
	}
	
	public static class PostBuilder extends BaseBuilder<String>{
		
		public PostBuilder(){
			method = Method.POST;
		}

		@Override
		public BaseHttpClient<String> execute() {
			if(checkUrl()){
				return new StringRequestHttpClient(this);
			}
			return null;
		}
	}
	
	@Override
	protected Request<String> createPostRequest() {
		StringRequest stringRequest = new StringRequest(Method.POST, mUrl, 
				new Response.Listener<String>() {

					@Override
					public void onResponse(String response) {
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
					
					@Override
					protected Map<String, String> getParams()
							throws AuthFailureError {
						if(mParams != null){
							return mParams;
						}else{
							return super.getParams();
						}
						
					}
					
					@Override
					public Map<String, String> getHeaders()
							throws AuthFailureError {
						if(mHeaders != null){
							return mHeaders;
						}else{
							return super.getHeaders();
						}
						
					}
				};
		
				return stringRequest;
	}

	@Override
	protected Request<String> createGetRequest() {
		StringRequest stringRequest = new StringRequest(
				mParams == null ? mUrl : getUrl(mUrl, mParams), 
				new Response.Listener<String>() {

					@Override
					public void onResponse(String response) {
						if(null != mOnResponseListener){
							//mOnResponseListener.onSuccess(response);
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
					
					@Override
					public Map<String, String> getHeaders()
							throws AuthFailureError {
						if(mHeaders != null){
							return mHeaders;
						}else{
							return super.getHeaders();
						}
						
					}
				};
		
		return stringRequest;
	}

}
