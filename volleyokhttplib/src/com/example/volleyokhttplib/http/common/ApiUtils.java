package com.example.volleyokhttplib.http.common;

import com.example.volleyokhttplib.R;

import android.content.Context;

/**
 * 拼接url
 * @author heyunjian
 *
 */
public class ApiUtils {
	
	//url = scheme：//host：port/path
	
	public static String formatUrl(Context context, int path){
		
		StringBuffer sb = new StringBuffer();
		sb.append(context.getString(R.string.api_base_url));
		sb.append(context.getString(path));
		return sb.toString();
	}
		
	public static String formatUrl(Context context, String path){
		StringBuffer sb = new StringBuffer();
		sb.append(context.getString(R.string.api_base_url));
		sb.append(path);
		return sb.toString();
	}	
}
