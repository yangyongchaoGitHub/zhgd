package com.dataexpo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.dataexpo.listener.WebsocketPacketHandleEvent;
import com.dataexpo.serviceinterface.FaceSearchServiceInterface;
import com.dataexpo.serviceinterface.WSServiceInterface;
import com.dataexpo.trans.BaseRequest;
import com.dataexpo.trans.BaseResponse;
import com.dataexpo.trans.Definds;
import com.dataexpo.trans.face.FaceInfoRequestParam;
import com.dataexpo.trans.face.FacePacket;
import com.dataexpo.trans.face.FaceSearchCreateResponseEntity;
import com.dataexpo.trans.face.WaitSendRequest;
import com.dataexpo.trans.user.UserAddResponse;
import com.dataexpo.trans.user.UserDeleteResponse;
import com.dataexpo.trans.user.UserSearchResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
@Service("FaceSearchService")
public class FaceSearchService implements FaceSearchServiceInterface, ApplicationListener<WebsocketPacketHandleEvent> {
	//存储人脸查询组信息
	List<FacePacket> packets = new ArrayList<FacePacket>();
	List<WaitSendRequest> waitList = new ArrayList<WaitSendRequest>();
	
	ObjectMapper mapper = new ObjectMapper();
	
	@Autowired
	WSServiceInterface wsService;
	
	public int getUserFaceStatus(String deviceId, String id) {
		
		int res = 0;
		BaseRequest request = new BaseRequest();
		
		request.setId(wsService.getId());
		
		request.setMethod(Definds.FACE_GET_BY_ID);
		FaceInfoRequestParam param = new FaceInfoRequestParam();
		param.setCertificateType("IC");
		param.setId(id);
		request.setParams(param);
		
		if (packets.size() == 0) {
			createFaceSearchPacket(deviceId);
			WaitSendRequest waitSendRequest = new WaitSendRequest();
			waitSendRequest.setDeviceId(deviceId);
			waitSendRequest.setRequest(request);
			waitList.add(waitSendRequest);
			System.out.println("还未创建查询组件");
		} else {
			request.setObject(packets.get(0).getPacketId());
			res = wsService.send(deviceId, request);
			System.out.println("已有查询组件  直接发送！");
		}
		return res;
	}
	
	public int createFaceSearchPacket(String deviceId) {
		int res = 0;
		BaseRequest request = new BaseRequest();
		
		request.setId(wsService.getId());
		
		request.setMethod(Definds.FACE_CREATE_GROUP_INTERFACE);
		
		res = wsService.send(deviceId, request);
		
		return res;
	}
	
	private void sendWait() {
		for (int i = 0; i < waitList.size(); i++) {
			BaseRequest request = waitList.get(i).getRequest();
			request.setObject(packets.get(0).getPacketId());
			wsService.send(waitList.get(i).getDeviceId(), request);
		}
	}

	/**
	 * 当有设备返回数据包时 触发监听时间
	 */
	public void onApplicationEvent(WebsocketPacketHandleEvent event) {
		System.out.println("FaceSearchService onApplicationEvent");
		String method = event.getMethod();
		String payload =  event.getPayload();
		try {
			if (Definds.FACE_CREATE_GROUP_INTERFACE.equals(method)) {
				System.out.println("-----" + Definds.FACE_CREATE_GROUP_INTERFACE);
				/*
				 * UserAddResponse addResponse = mapper.readValue(payload,
				 * UserAddResponse.class); responseWithUserAdd(addResponse);
				 */
				FaceSearchCreateResponseEntity entity = mapper.readValue(payload, FaceSearchCreateResponseEntity.class);
				FacePacket packet = new FacePacket();
				packet.setPacketId(entity.getResult());
				packet.setCreate(new Date().getTime());
				packets.add(packet);
				sendWait();
				
			} else if (Definds.FACE_GET_BY_ID.equals(method)) {
				System.out.println("-----" + Definds.FACE_GET_BY_ID);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
