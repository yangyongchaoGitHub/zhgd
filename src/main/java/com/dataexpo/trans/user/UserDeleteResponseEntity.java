package com.dataexpo.trans.user;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserDeleteResponseEntity {
	private String code;
	private boolean result;
	private int errorCode;
	private String errorMessage;
	
	@JsonProperty("Code")
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	@JsonProperty("Result")
	public boolean getResult() {
		return result;
	}
	public void setResult(boolean result) {
		this.result = result;
	}
	@JsonProperty("ErrorCode")
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	@JsonProperty("ErrorMessage")
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	
}
