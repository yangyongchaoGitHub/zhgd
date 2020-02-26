package com.dataexpo.trans.user;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 删除用户时使用
 * @author pc
 *
 */
public class UserDeleteParams {
	private String code;
	
	public UserDeleteParams(String code) {
		this.code = code;
	}

	@JsonProperty("Code")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	

}
