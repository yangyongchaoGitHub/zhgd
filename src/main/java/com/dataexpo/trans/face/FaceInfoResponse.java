package com.dataexpo.trans.face;

import com.dataexpo.trans.BaseResponse;

public class FaceInfoResponse extends BaseResponse {
	private FaceInfoResponseEntity params;

	@Override
	public FaceInfoResponseEntity getParams() {
		return params;
	}

	public void setParams(FaceInfoResponseEntity params) {
		this.params = params;
	}
}
