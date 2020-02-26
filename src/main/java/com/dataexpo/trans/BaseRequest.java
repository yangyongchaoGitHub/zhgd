package com.dataexpo.trans;

public class BaseRequest extends BaseProtocal {
	//获取人脸信息时使用
	private long object;

	public long getObject() {
		return object;
	}

	public void setObject(long object) {
		this.object = object;
	}
	
}
