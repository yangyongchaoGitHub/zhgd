package com.dataexpo.serviceinterface;

public interface FaceSearchServiceInterface {
	public int getUserFaceStatus(String deviceId, String id);
	public int createFaceSearchPacket(String deviceId);
	public int getUserPic(String deviceId, String userCode);
}
