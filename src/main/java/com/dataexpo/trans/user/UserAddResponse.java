package com.dataexpo.trans.user;

import java.util.List;

import com.dataexpo.trans.BaseResponse;

public class UserAddResponse extends BaseResponse {
	List<UserAddResponseEntity> params;

	@Override
	public List<UserAddResponseEntity> getParams() {
		return params;
	}
	
	
	public void setParams(List<UserAddResponseEntity> params) {
		this.params = params;
	}

}
