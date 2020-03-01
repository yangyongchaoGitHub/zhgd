package com.dataexpo.trans.face;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FaceImageInfos {
	private String faceToken;
	private int personID;
	private int groupID;
	private int state;
	private String addTime;
	private int faceID;
	private String imageID;
	
	@JsonProperty("FaceToken")
	public String getFaceToken() {
		return faceToken;
	}
	public void setFaceToken(String faceToken) {
		this.faceToken = faceToken;
	}
	@JsonProperty("PersonID")
	public int getPersonID() {
		return personID;
	}
	public void setPersonID(int personID) {
		this.personID = personID;
	}
	@JsonProperty("GroupID")
	public int getGroupID() {
		return groupID;
	}
	public void setGroupID(int groupID) {
		this.groupID = groupID;
	}
	@JsonProperty("State")
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	@JsonProperty("AddTime")
	public String getAddTime() {
		return addTime;
	}
	
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	@JsonProperty("FaceID")
	public int getFaceID() {
		return faceID;
	}
	public void setFaceID(int faceID) {
		this.faceID = faceID;
	}
	@JsonProperty("ImageID")
	public String getImageID() {
		return imageID;
	}
	public void setImageID(String imageID) {
		this.imageID = imageID;
	}
	
	
}
