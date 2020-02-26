package com.dataexpo.trans.user;

import java.util.List;

import com.dataexpo.trans.Person;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserSearchResult {
	List<Person> Persons;

	@JsonProperty("Persons")
	public List<Person> getPersons() {
		return Persons;
	}

	public void setPersons(List<Person> persons) {
		Persons = persons;
	}
	
}
