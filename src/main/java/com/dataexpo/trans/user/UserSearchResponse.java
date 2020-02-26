package com.dataexpo.trans.user;

import java.util.List;

import com.dataexpo.trans.BaseResponse;

public class UserSearchResponse extends BaseResponse {
	private UserSearchResult person;

	@Override
	public UserSearchResult getParams() {
		return person;
	}

	public void setParams(UserSearchResult person) {
		this.person = person;
	}
}
