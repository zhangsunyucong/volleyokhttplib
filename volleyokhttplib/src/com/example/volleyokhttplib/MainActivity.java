package com.example.volleyokhttplib;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.volleyokhttplib.commmon.ControlUtil;
import com.example.volleyokhttplib.http.client.GsonRequestHttpClient;
import com.example.volleyokhttplib.http.common.ApiUtils;
import com.example.volleyokhttplib.http.common.OnResponseListener;

public class MainActivity extends Activity implements View.OnClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		initPostRequest();
		initGetRequest();
		
	}

	private void initPostRequest() {
		Button postBtn = (Button) findViewById(R.id.id_btn_post);
		if(null != postBtn){
			postBtn.setOnClickListener(this);
		}
		
	}

	
	private void initGetRequest() {
		Button getBtn = (Button) findViewById(R.id.id_btn_get);
		if(null != getBtn){
			getBtn.setOnClickListener(this);
		}
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.id_btn_post:
			sendPostRequest();
			break;
		case R.id.id_btn_get:
			sendGetRequest();
			break;

		default:
			break;
		}
	}

	private void sendGetRequest() {
		
		String url = ApiUtils.formatUrl(this, R.string.api_get_weather_info);
		new GsonRequestHttpClient.GetBuilder<WeatherInfoData>(WeatherInfoData.class)
			.url(url)
			.param("city", "…Ó€⁄")
			.setOnResponseListener(new OnResponseListener<WeatherInfoData>() {
				
				@Override
				public void onSuccess(WeatherInfoData response) {
					if(response != null){
						List<WeatherInfoRecord> data = response.getData();
						if(data != null && data.size() > 0){
							ControlUtil.showToastText(MainActivity.this, data.get(0).weather);
						}else{
							ControlUtil.showToastText(MainActivity.this, "error");
						}
					}
				}
				
				@Override
				public void onError(VolleyError error) {
					ControlUtil.showToastText(MainActivity.this, ""+error.networkResponse.statusCode);
				}
			}).execute();
		
	}

	private void sendPostRequest() {
		
		String url = ApiUtils.formatUrl(this, R.string.api_get_weather_info);
		new GsonRequestHttpClient.PostBuilder<WeatherInfoData>(WeatherInfoData.class)
			.url(url)
			.param("city", "…Ó€⁄")
			.setOnResponseListener(new OnResponseListener<WeatherInfoData>() {

				@Override
				public void onSuccess(WeatherInfoData response) {
					if(response != null){
						List<WeatherInfoRecord> data = response.getData();
						if(data != null && data.size() > 0){
							Toast.makeText(MainActivity.this, data.get(0).weather, Toast.LENGTH_SHORT).show();
						}else{
							Toast.makeText(MainActivity.this, "error", Toast.LENGTH_SHORT).show();
						}
					}
				}

				@Override
				public void onError(VolleyError error) {
					Toast.makeText(MainActivity.this, ""+error.networkResponse.statusCode, Toast.LENGTH_SHORT).show();
				}
			}).execute();
		
	}

}
