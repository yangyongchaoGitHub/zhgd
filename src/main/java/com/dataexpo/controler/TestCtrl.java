package com.dataexpo.controler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dataexpo.domain.User;


@Controller
public class TestCtrl {
	@RequestMapping("/test")
	@ResponseBody
	public User test(@RequestParam(value="name", defaultValue="yyyyyyy") String name) {
		User user = new User();
		user.setName("first");
		
		System.out.println("bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb ");
		System.out.println(name);
		return user;
	}
}
