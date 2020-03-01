package com.dataexpo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.dataexpo.domain.WebSocketClient;
import com.dataexpo.listener.WebsocketPacketHandleEvent;
import com.dataexpo.serviceinterface.FaceSearchServiceInterface;
import com.dataexpo.serviceinterface.WSServiceInterface;
import com.dataexpo.trans.BaseRequest;
import com.dataexpo.trans.BaseResponse;
import com.dataexpo.trans.Definds;
import com.dataexpo.trans.face.FaceImageInfos;
import com.dataexpo.trans.face.FaceInfoRequestParam;
import com.dataexpo.trans.face.FaceInfoResponse;
import com.dataexpo.trans.face.FaceInfoResponseEntity;
import com.dataexpo.trans.face.FacePacket;
import com.dataexpo.trans.face.FaceSearchCreateResponseEntity;
import com.dataexpo.trans.face.GetPicParam;
import com.dataexpo.trans.face.WaitSendRequest;
import com.dataexpo.trans.user.UserAddResponse;
import com.dataexpo.trans.user.UserDeleteResponse;
import com.dataexpo.trans.user.UserSearchResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
@Service("FaceSearchService")
public class FaceSearchService implements FaceSearchServiceInterface, ApplicationListener<WebsocketPacketHandleEvent> {
	//存储人脸查询组信息 key：设备序列号  
	HashMap<String, FacePacket> packetsMap = new HashMap<String, FacePacket>();
	List<WaitSendRequest> waitList = new ArrayList<WaitSendRequest>();
	
	ObjectMapper mapper = new ObjectMapper();
	
	@Autowired
	WSServiceInterface wsService;

	public int getUserPic(String deviceId, String userCode) {
		int res = 0;
		BaseRequest request = new BaseRequest();
		GetPicParam param = new GetPicParam();
		
		param.setFaceToken("0eee01a404a90a4562ae41c5b90a2b58");
		
		request.setId(wsService.getId());
		request.setMethod(Definds.FACE_GET_PIC_BY_TOKEN);
		request.setParams(param);
		
		if (packetsMap.size() == 0) {
			createFaceSearchPacket(deviceId);
			WaitSendRequest waitSendRequest = new WaitSendRequest();
			waitSendRequest.setDeviceId(deviceId);
			waitSendRequest.setRequest(request);
			waitList.add(waitSendRequest);
			System.out.println("还未创建查询组件");
		} else {
			request.setObject(packetsMap.get(deviceId).getPacketId());
			res = wsService.send(deviceId, request);
			System.out.println("已有查询组件  直接发送！");
		}
		return res;
	}
	
	public int getUserFaceStatus(String deviceId, String id) {
		int res = 0;
		BaseRequest request = new BaseRequest();
		
		request.setId(wsService.getId());
		
		request.setMethod(Definds.fACE_GET_INFO_BY_ID);
		FaceInfoRequestParam param = new FaceInfoRequestParam();
		param.setCertificateType("IC");
		param.setId(id);
		request.setParams(param);
		
		if (packetsMap.size() == 0) {
			createFaceSearchPacket(deviceId);
			WaitSendRequest waitSendRequest = new WaitSendRequest();
			waitSendRequest.setDeviceId(deviceId);
			waitSendRequest.setRequest(request);
			waitList.add(waitSendRequest);
			System.out.println("还未创建查询组件");
		} else {
			request.setObject(packetsMap.get(deviceId).getPacketId());
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
	
	public int destroyFaceSearchPacket(String deviceId) {
		int res = 0;
		BaseRequest request = new BaseRequest();
		FacePacket packet = packetsMap.get(deviceId);
		
		request.setId(wsService.getId());
		packet.setDestroyId(request.getId());
		packet.setStatus(FacePacket.STATUS_INDESTROY);
		
		request.setMethod(Definds.FACE_DESTROY_GROUP_INTERFACE);
		request.setObject(packet.getPacketId());
		
		res = wsService.send(deviceId, request);
		
		return res;
	}
	
	private void sendWait() {
		for (int i = 0; i < waitList.size(); i++) {
			BaseRequest request = waitList.get(i).getRequest();
			FacePacket packet = packetsMap.get(waitList.get(i).getDeviceId());
			if (packet != null) {
				request.setObject(packet.getPacketId());
				wsService.send(waitList.get(i).getDeviceId(), request);
			}
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
				
				packetsMap.put((String) event.getSession().getAttributes().get("SerialNo"), packet);
			
				sendWait();
				
			} else if (Definds.fACE_GET_INFO_BY_ID.equals(method)) {
				System.out.println("-----" + Definds.fACE_GET_INFO_BY_ID);
				FaceInfoResponse response = mapper.readValue(payload, FaceInfoResponse.class);
				FaceImageInfos infos = response.getParams().getFaceImageInfos().get(0);
				
				System.out.println("FaceImageInfos facetoken" + infos.getFaceToken() + 
						" personid " + infos.getPersonID());
				
			} else if (Definds.FACE_GET_PIC_BY_TOKEN.equals(method)) {
				System.out.println("-----" + Definds.FACE_GET_PIC_BY_TOKEN);
				System.out.println(payload);
				
			} else if (Definds.FACE_DESTROY_GROUP_INTERFACE.equals(method)) {
				System.out.println("-----" + Definds.FACE_DESTROY_GROUP_INTERFACE);
				BaseResponse response = mapper.readValue(payload, BaseResponse.class);
				
				System.out.println("result: " + response.getResult());
				Iterator<Entry<String, FacePacket>> iterator = packetsMap.entrySet().iterator();
				while (iterator.hasNext()) {
					Entry<String, FacePacket> entry = iterator.next();
					//找到对应的packet
					if (entry.getValue().getDestroyId() == response.getId()) {
						iterator.remove();
						break;
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
