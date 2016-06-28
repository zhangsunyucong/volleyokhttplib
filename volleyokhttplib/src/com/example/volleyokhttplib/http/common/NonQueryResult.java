package com.example.volleyokhttplib.http.common;


/**
 * 用户非查询数据的后台操作结果
 * @author heyunjian
 *
 */
public class NonQueryResult {
	public Data data;
	public int code ;
	public String error;
	
	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public class Data {
		public String updatedAt;
		public String objectId;
		
	}

}
