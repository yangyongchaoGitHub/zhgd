package com.dataexpo.trans.user;

import com.dataexpo.trans.Condition;
import com.dataexpo.trans.Params;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserSearchParams implements Params<Condition> {
	private Condition condition;

	public UserSearchParams(Condition condition) {
		this.condition = condition;
	}
	
	@JsonProperty("Condition")
	public Condition getCondition() {
		return condition;
	}

	public void setCondition(Condition condition) {
		this.condition = condition;
	}
	
}
