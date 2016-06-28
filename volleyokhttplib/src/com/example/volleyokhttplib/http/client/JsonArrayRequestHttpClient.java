package com.example.volleyokhttplib.http.client;

import java.util.Map;

import org.json.JSONArray;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.volleyokhttplib.http.common.OnResponseListener;

public class JsonArrayRequestHttpClient extends BaseHttpClient<JSONArray>{
	
	private OnResponseListener<JSONArray> mOnResponseListener;
	
	private JsonArrayRequestHttpClient(BaseBuilder<JSONArray> builder) {
		
		initPropertys(builder);
		
		initLoacalPropertys(builder);
		
		JsonArrayRequest jsonArrayRequest = (JsonArrayRequest) createRequestByMethod(mMethod);
		
		jsonArrayRequest.setRetryPolicy(mRetryPolicy);
		
		addRequestByTag(jsonArrayRequest, mTag);
		
	}
	
	public static class GetBuilder extends BaseBuilder<JSONArray>{
		
		public GetBuilder(){
			method = Method.GET;
		}
		
		@Override
		public BaseHttpClient<JSONArray> execute() {
			if(checkUrl()){
				return new JsonArrayRequestHttpClient(this);
			}
			return null;
		}
		
	}

	private void initLoacalPropertys(BaseBuilder<JSONArray> builder) {
		this.mOnResponseListener = builder.onResponseListener;
	}

	@Override
	protected Request<JSONArray> createPostRequest() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Request<JSONArray> createGetRequest() {
		JsonArrayRequest jsonArrayRequestRequest = new JsonArrayRequest(getUrl(mUrl, mParams), 
				new Response.Listener<JSONArray>() {

					@Override
					public void onResponse(JSONArray response) {
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
		return jsonArrayRequestRequest;
	}
}
