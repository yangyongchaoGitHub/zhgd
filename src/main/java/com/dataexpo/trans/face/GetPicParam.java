package com.dataexpo.trans.face;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GetPicParam {
	private String faceToken;

	@JsonProperty("FaceToken")
	public String getFaceToken() {
		return faceToken;
	}

	public void setFaceToken(String faceToken) {
		this.faceToken = faceToken;
	}
	
}
