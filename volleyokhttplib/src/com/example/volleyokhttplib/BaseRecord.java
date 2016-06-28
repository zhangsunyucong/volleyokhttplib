package com.example.volleyokhttplib;

import java.io.Serializable;


public abstract class BaseRecord implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	public static String OBJECTID = "objectId";
	public static String UPDATEDAT = "updatedAt";
	public static String CREATEDAT = "createdAt";
	
	public String objectId;
	public long updatedAt;
	public long createdAt;
	
	public long getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(long updatedAt) {
		this.updatedAt = updatedAt;
	}
	public long getCreatedAt() {
		return createdAt;
	}
	public String getObjectId() {
		return objectId;
	}
	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}
	public void setCreatedAt(long createdAt) {
		this.createdAt = createdAt;
	}
	
	public BaseRecord(BaseRecord record){
		objectId = record.objectId;
		updatedAt = record.updatedAt;
		createdAt = record.createdAt;
	}
	
	public BaseRecord(){
		
	}
}
