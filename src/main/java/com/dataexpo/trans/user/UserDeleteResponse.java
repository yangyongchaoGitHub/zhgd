package com.dataexpo.trans.user;

import java.util.List;

import com.dataexpo.trans.BaseResponse;

public class UserDeleteResponse extends BaseResponse {
	private List<UserDeleteResponseEntity> params;

	public List<UserDeleteResponseEntity> getParams() {
		return params;
	}

	public void setParams(List<UserDeleteResponseEntity> params) {
		this.params = params;
	}
	
	
}
