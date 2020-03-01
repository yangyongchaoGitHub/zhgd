package com.dataexpo.controler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dataexpo.domain.WebSocketClient;
import com.dataexpo.serviceinterface.DeviceServiceInterface;
import com.dataexpo.serviceinterface.FaceSearchServiceInterface;
import com.dataexpo.serviceinterface.UserServiceInterface;
import com.dataexpo.trans.Card;
import com.dataexpo.trans.Condition;
import com.dataexpo.trans.Memo;
import com.dataexpo.trans.Person;

@Controller
public class WSRegistCtrl {
	
	@Autowired
	DeviceServiceInterface deviceService;
	@Autowired
	FaceSearchServiceInterface faceSearchService;
	@Autowired
	UserServiceInterface userService;
	
	@RequestMapping("/getUserPic")
	@ResponseBody
	public String getUserPic() {
		faceSearchService.getUserPic("5L97R020281", "0eee01a404a90a4562ae41c5b90a2b58");
		return "234";
	}
	
	@RequestMapping("/getUserFaceStatus")
	@ResponseBody
	public String userFaceStatus() {
		faceSearchService.getUserFaceStatus("5L97R020281", "1001");
		return "233";
	}
	
	@RequestMapping("/listDevice")
	@ResponseBody
	public List<WebSocketClient> listDevice() {
		List<WebSocketClient> list = deviceService.listDevice();
		return list;
	}
	
	@RequestMapping("/deviceCount")
	@ResponseBody
	public int getDeviceCount() {
		int count = 0;
		count = deviceService.deviceCount();
		return count;
	}
	
	@RequestMapping("/search")
	@ResponseBody
	public String search() {
		Condition condition = new Condition();
		condition.setCodeLike("1");
		condition.setLimit(10);
		condition.setType(1);
		userService.searchUser("5L97R020281", condition);
		return "2133";
	}
	
	/**
	 * 删除用户
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public String delete() {
		List<String> personList = new ArrayList<String>();
		personList.add("123");
		userService.deleteUser("", personList);
		return "";
	}
	
	/**
	 * 添加用户
	 * @param name
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public String addUser(@RequestParam(value="name", defaultValue="yyyyyyy") String name) {
		Person person = new Person();
		person.setType(1);
		person.setCode("123");
		person.setGroupName("默认权限组");
		person.setName("老大哥");
		person.setSex("male");
		person.setBirthday("2129-01-21");
//		person.setGuestInfo(new GuestInfo());
//		person.getGuestInfo().setCorp("中建一局");
//		person.getGuestInfo().setPhone("12349580684");
//		person.getGuestInfo().setCarLicense("特023921");
//		person.getGuestInfo().setPartner(3);
//		person.getGuestInfo().setHost("武老大");
//		AccessTime accessTime = new AccessTime();
//		accessTime.setFrom(1541756000);
//		accessTime.setTo(1541856000);
//		List<AccessTime> accessTimes = new ArrayList<AccessTime>();
//		accessTimes.add(accessTime);
//		person.getGuestInfo().setAccessTime(accessTimes);
		Card card = new Card();
		card.setID("e2341");
		card.setType(1);
		card.setMemo(new Memo("一栋22号"));
		List<String> validList = new ArrayList<String>();
		validList.add("2018-10-1");
		validList.add("2019-10-1");
		card.setValidity(validList);
		List<String> ValidityTime = new ArrayList<String>();
		ValidityTime.add("12:30:00");
		ValidityTime.add("23:59:59");
		card.setValidityTime(ValidityTime);
		List<Card> cards = new ArrayList<Card>();
		cards.add(card);
		person.setCards(cards);
		List<String> urlsList = new ArrayList<String>();
		urlsList.add("http://192.168.1.6:8080/999999.jpg");
		person.setURL(urlsList);
		List<Person> personList = new ArrayList<Person>();
		personList.add(person);
		
		userService.addUser("5L97R020281", personList);
		
		System.out.println("bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb ");
		System.out.println(name);
		
		/*
		 * wsServiceInterface.messageIn(null,
		 * "{\"id\":1,\"method\":\"personnelData.savePersons\",\"result\": true,\"params\":[{"
		 * + "\"CertificateType\":\"IC\"," + "\"Code\":\"10086\"," + "\"Result\":true,"
		 * + "\"ErrorCode\":0," + "\"ErrorCodePic\":[]," +
		 * "\"ErrorMessage\":\"URL is error\"" + "}]}");
		 */
		return "23";
	}
}
