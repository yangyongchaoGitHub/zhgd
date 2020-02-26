package com.dataexpo.trans;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 访客信息,人员类型是访客时有效(非访客不填)
 * @author pc
 *
 */
public class GuestInfo {
	//单位
	private String Corp;
	//电话
	private String Phone;
	//车牌号码
	private String CarLicense;
	//随行人数
	private int Partner;
	//被访人
	private String Host;
	//准入时间
	private List<AccessTime> AccessTime;
	
	@JsonProperty("Corp")
	public String getCorp() {
		return Corp;
	}
	public void setCorp(String corp) {
		Corp = corp;
	}
	@JsonProperty("Phone")
	public String getPhone() {
		return Phone;
	}
	public void setPhone(String phone) {
		Phone = phone;
	}
	@JsonProperty("CarLicense")
	public String getCarLicense() {
		return CarLicense;
	}
	public void setCarLicense(String carLicense) {
		CarLicense = carLicense;
	}
	@JsonProperty("Partner")
	public int getPartner() {
		return Partner;
	}
	public void setPartner(int partner) {
		Partner = partner;
	}
	@JsonProperty("Host")
	public String getHost() {
		return Host;
	}
	public void setHost(String host) {
		Host = host;
	}
	@JsonProperty("AccessTime")
	public List<AccessTime> getAccessTime() {
		return AccessTime;
	}
	public void setAccessTime(List<AccessTime> accessTime) {
		AccessTime = accessTime;
	}
	
}
