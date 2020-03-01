package com.dataexpo.trans.face;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FaceInfoResponseEntity {
	List<FaceImageInfos> faceImageInfos;

	@JsonProperty("FaceImageInfos")
	public List<FaceImageInfos> getFaceImageInfos() {
		return faceImageInfos;
	}

	public void setFaceImageInfos(List<FaceImageInfos> faceImageInfos) {
		this.faceImageInfos = faceImageInfos;
	}
}
