package com.dataexpo.trans.face;

import com.dataexpo.trans.BaseRequest;

public class WaitSendRequest {
	private BaseRequest request;
	private String deviceId;
	
	public BaseRequest getRequest() {
		return request;
	}
	public void setRequest(BaseRequest request) {
		this.request = request;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	
}
