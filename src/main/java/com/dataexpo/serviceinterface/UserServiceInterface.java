package com.dataexpo.serviceinterface;

import java.util.List;

import com.dataexpo.trans.Condition;
import com.dataexpo.trans.Person;

public interface UserServiceInterface {
	public int addUser(String deviceId, List<Person> persons);
	public int deleteUser(String deviceId, List<String> userCode);
	public int searchUser(String deviceId, Condition condition);
}
