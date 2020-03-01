package com.dataexpo.trans;

public class BaseRequest extends BaseProtocal {
	//获取人脸信息时使用
	private Long object;

	public Long getObject() {
		return object;
	}

	public void setObject(Long object) {
		this.object = object;
	}
	
}
