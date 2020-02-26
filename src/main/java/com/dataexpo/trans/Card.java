package com.dataexpo.trans;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 卡信息
 * @author pc
 *
 */
public class Card {
	//卡号
    private String ID;
    //卡类型，1-普通卡，2-胁迫卡
    private int Type;
	/**
	 * "Validity": [ "2018-10-1", "2019-10-1" ],
	 *  有效期日期  开始日期， yyyy-MM-dd, 结束日期， yyyy-MM-dd
	 */
    private List<String> Validity;
	/**
	 * optional有效期的时间，默认0:0:0 - 23:59:59  
	 * "ValidityTime": [ "12:30:00", "23:59:59" ],
	 */
    private List<String> ValidityTime;
    //optional 其它信息
    private Memo Memo;
    
    @JsonProperty("ID")
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	@JsonProperty("Type")
	public int getType() {
		return Type;
	}
	public void setType(int type) {
		Type = type;
	}
	@JsonProperty("Validity")
	public List<String> getValidity() {
		return Validity;
	}
	
	public void setValidity(List<String> validity) {
		Validity = validity;
	}
	@JsonProperty("ValidityTime")
	public List<String> getValidityTime() {
		return ValidityTime;
	}
	public void setValidityTime(List<String> validityTime) {
		ValidityTime = validityTime;
	}
	@JsonProperty("Memo")
	public Memo getMemo() {
		return Memo;
	}
	public void setMemo(Memo memo) {
		Memo = memo;
	}
    
}
