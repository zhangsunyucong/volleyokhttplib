package com.example.volleyokhttplib.http.common;


/**
 * �û��ǲ�ѯ���ݵĺ�̨�������
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
