package com.example.volleyokhttplib.http.client;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

import org.json.JSONObject;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.volleyokhttplib.http.common.OnResponseListener;

public class JsonObjectRequestHttpClient extends BaseHttpClient<JSONObject>{
	
	private OnResponseListener<JSONObject> mOnResponseListener;
	
	private JsonObjectRequestHttpClient(BaseBuilder<JSONObject> builder) {
		
		initPropertys(builder);
		
		initLoacalPropertys(builder);
		
		JsonObjectRequest jsonObjectRequest = (JsonObjectRequest) createRequestByMethod(mMethod);
		
		jsonObjectRequest.setRetryPolicy(mRetryPolicy);
		
		addRequestByTag(jsonObjectRequest, mTag);
		
	}

	private void initLoacalPropertys(BaseBuilder<JSONObject> builder) {
		this.mOnResponseListener = builder.onResponseListener;
	}
	
	public static class GetBuilder extends BaseBuilder<JSONObject>{
		
		public GetBuilder(){
			method = Method.GET;
		}
		
		@Override
		public BaseHttpClient<JSONObject> execute() {
			if(checkUrl()){
				return new JsonObjectRequestHttpClient(this);
			}
			return null;
		}
		
	}
	
	public static class PostBuilder extends BaseBuilder<JSONObject>{
		
		public PostBuilder(){
			method = Method.POST;
		}

		@Override
		public BaseHttpClient<JSONObject> execute() {
			if(checkUrl()){
				return new JsonObjectRequestHttpClient(this);
			}
			return null;
		}
	}

	@Override
	protected Request<JSONObject> createPostRequest() {
		JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Method.POST, mUrl, null,
				new Response.Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject response) {
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
					public String getBodyContentType() {
						return "application/x-www-form-urlencoded; charset=" + getParamsEncoding();
					}
			
					@Override
					public byte[] getBody() {
						if (mParams != null && mParams.size() > 0) {
					        return encodeParameters(mParams, getParamsEncoding());
					     }
					     return null;
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
					
					@Override
					public Priority getPriority() {
						return mPriority;
					}
					
					private byte[] encodeParameters(Map<String, String> params, String paramsEncoding) {
				        StringBuilder encodedParams = new StringBuilder();
				        try {
				            for (Map.Entry<String, String> entry : params.entrySet()) {
				                encodedParams.append(URLEncoder.encode(entry.getKey(), paramsEncoding));
				                encodedParams.append('=');
				                encodedParams.append(URLEncoder.encode(entry.getValue(), paramsEncoding));
				                encodedParams.append('&');
				            }
				            return encodedParams.toString().getBytes(paramsEncoding);
				        } catch (UnsupportedEncodingException uee) {
				            throw new RuntimeException("Encoding not supported: " + paramsEncoding, uee);
				        }
				    }
				};
		
		return jsonObjectRequest;
	}

	@Override
	protected Request<JSONObject> createGetRequest() {
		JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Method.GET, getUrl(mUrl, mParams), null,
				new Response.Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject response) {
						if(null != mOnResponseListener){
							mOnResponseListener.onSuccess(response);
						}
					}
				}, 
				new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) { //error.networkResponse.statusCode
						if(null != mOnResponseListener){
							mOnResponseListener.onError(error);
						}
					}
				})
				{
			
					@Override
					public Map<String, String> getHeaders()
							throws AuthFailureError {
						if(mHeaders != null){
							return mHeaders;
						}else{
							return super.getHeaders();
						}
					}
					
					@Override
					public Priority getPriority() {
						// TODO Auto-generated method stub
						return mPriority;
					}
				};
				return jsonObjectRequest;
	}
	
}
