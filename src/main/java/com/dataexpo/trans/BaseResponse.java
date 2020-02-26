package com.dataexpo.trans;

public class BaseResponse extends BaseProtocal {
	//当返回的数据有这个字段时会被修改，没有时进入下一个判断模块去判断
	private boolean result = true;
	private Errors error;
	private String session;

	public boolean getResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public Errors getError() {
		return error;
	}

	public void setError(Errors error) {
		this.error = error;
	}

	public String getSession() {
		return session;
	}

	public void setSession(String session) {
		this.session = session;
	}
	
	
}
