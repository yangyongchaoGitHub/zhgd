package com.dataexpo.trans.user;

import com.dataexpo.trans.Params;
import com.dataexpo.trans.Person;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserAddParams implements Params<Person> {
	private Person person;
	
	public UserAddParams(Person person) {
		this.person = person;
	}
	
	@JsonProperty("Person")
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
}
