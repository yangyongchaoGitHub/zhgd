package com.dataexpo.trans;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 进行websocket人员增加时的用户参数实体类
 * @author pc
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Person {
	//人员类型,1-内部员工,2-访客, 3-黑名单；必填
	private int type; 
	//证件号,与CertificateType组合构成全局唯一人员标识,不允许更新,避免前端和平台端不一致。必填
	private String code;
	//默认权限组
	private String groupName;
	//姓名必填
	private String name;
	//性别必填
	private String sex;
	//出生日期必填
	private String birthday;
	//访客信息,人员类型是访客时有效(非访客不填)
	private GuestInfo guestInfo;
	//通过HTTP协议下载图片的地址；只支持JPG格式;     "URL":["http://baidu.com/picture/11.jpg"]
	private List<String> URL;
	// Base64编码的图片数据,与URL字段互斥； 两者里面只能选一个
	private List<String> images;
	//卡信息
	private List<Card> cards;
	
	@JsonProperty("Type")
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	@JsonProperty("Code")
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	@JsonProperty("GroupName")
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	@JsonProperty("Name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@JsonProperty("Sex")
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	@JsonProperty("Birthday")
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	@JsonProperty("GuestInfo")
	public GuestInfo getGuestInfo() {
		return guestInfo;
	}
	public void setGuestInfo(GuestInfo guestInfo) {
		this.guestInfo = guestInfo;
	}
	@JsonProperty("URL")
	public List<String> getURL() {
		return URL;
	}
	public void setURL(List<String> uRL) {
		URL = uRL;
	}
	@JsonProperty("Images")
	public List<String> getImages() {
		return images;
	}
	public void setImages(List<String> images) {
		this.images = images;
	}
	
	@JsonProperty("Cards")
	public List<Card> getCards() {
		return cards;
	}
	public void setCards(List<Card> cards) {
		this.cards = cards;
	}
}
