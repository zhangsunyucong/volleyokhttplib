# volleyokhttplib
一个builder模式封装好的volley+okhttp，封装了GsonRequestHttpClien、JsonArrayRequestHttpClient、JsonObjectRequestHttpClient和StringRequestHttpClient的post和get请求

如何使用volleyokhttplib？

首先，在自定义的application中初始化：

private void initVolley() {
		mRequestQueue = Volley
                .newRequestQueue(this,
               		 new OkHttpStack(new OkHttpClient()));
}

下面是GsonRequestHttpClient的get和post请求，其他的JsonArrayRequestHttpClient、JsonObjectRequestHttpClient和StringRequestHttpClient用法类似。
关于定义实体的格式约定，请查看demo。
GsonRequestHttpClient发送post请求：
    String url = ApiUtils.formatUrl(this, R.string.api_get_weather_info);
		new GsonRequestHttpClient.PostBuilder<WeatherInfoData>(WeatherInfoData.class)
			.url(url)
			.param("city", "深圳")
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
			
			
GsonRequestHttpClient发送get请求：
String url = ApiUtils.formatUrl(this, R.string.api_get_weather_info);
		new GsonRequestHttpClient.GetBuilder<WeatherInfoData>(WeatherInfoData.class)
			.url(url)
			.param("city", "深圳")
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
			
			
			如果项目使用混淆，可能需要实体类防止被混淆。
			
