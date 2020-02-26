package com.dataexpo.trans.face;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FaceInfoRequestParam {
	private String certificateType;
	private String id;
	
	@JsonProperty("CertificateType")
	public String getCertificateType() {
		return certificateType;
	}
	public void setCertificateType(String certificateType) {
		this.certificateType = certificateType;
	}
	@JsonProperty("ID")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
}
