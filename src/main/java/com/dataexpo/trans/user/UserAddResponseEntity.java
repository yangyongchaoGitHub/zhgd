package com.dataexpo.trans.user;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserAddResponseEntity {
	private String CertificateType;

    private String Code;

    //当前人员是否添加成功,失败有错误码和错误信息
    private boolean Result;

    //错误码
    private int ErrorCode;

    private List<String> ErrorCodePic;

    //错误信息
    private String ErrorMessage;

    @JsonProperty("CertificateType")
	public String getCertificateType() {
		return CertificateType;
	}

	public void setCertificateType(String certificateType) {
		CertificateType = certificateType;
	}

	@JsonProperty("Code")
	public String getCode() {
		return Code;
	}

	public void setCode(String code) {
		Code = code;
	}

	@JsonProperty("Result")
	public boolean isResult() {
		return Result;
	}

	public void setResult(boolean result) {
		Result = result;
	}

	@JsonProperty("ErrorCode")
	public int getErrorCode() {
		return ErrorCode;
	}

	public void setErrorCode(int errorCode) {
		ErrorCode = errorCode;
	}

	@JsonProperty("ErrorCodePic")
	public List<String> getErrorCodePic() {
		return ErrorCodePic;
	}

	public void setErrorCodePic(List<String> errorCodePic) {
		ErrorCodePic = errorCodePic;
	}

	@JsonProperty("ErrorMessage")
	public String getErrorMessage() {
		return ErrorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		ErrorMessage = errorMessage;
	}
    
    
}
