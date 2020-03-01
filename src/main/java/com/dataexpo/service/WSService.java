package com.dataexpo.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.apache.ibatis.javassist.expr.NewArray;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.dataexpo.domain.WebSocketClient;
import com.dataexpo.listener.WebsocketPacketHandleEvent;
import com.dataexpo.serviceinterface.FaceSearchServiceInterface;
import com.dataexpo.serviceinterface.WSServiceInterface;
import com.dataexpo.trans.Params;
import com.dataexpo.trans.Person;
import com.dataexpo.trans.user.UserAddParams;
import com.dataexpo.trans.user.UserAddResponse;
import com.dataexpo.trans.user.UserAddResponseEntity;
import com.dataexpo.trans.user.UserDeleteParams;
import com.dataexpo.trans.user.UserDeleteResponse;
import com.dataexpo.trans.user.UserDeleteResponseEntity;
import com.dataexpo.trans.user.UserSearchParams;
import com.dataexpo.trans.user.UserSearchResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.tools.classfile.Opcode.Set;
import com.sun.xml.internal.xsom.impl.scd.Iterators.Map;
import com.dataexpo.trans.BaseProtocal;
import com.dataexpo.trans.BaseRequest;
import com.dataexpo.trans.BaseResponse;
import com.dataexpo.trans.Condition;
import com.dataexpo.trans.Definds;

@Component
@Service("WSService")
public class WSService implements WSServiceInterface, ApplicationContextAware {
	private static int id = 0;
	//发送到指定目的地，并不支持在这个应用的使用场景
	/*
	 * @Autowired private SimpMessagingTemplate simpMessagingTemplate;
	 */
	//用于事件发布
	private ApplicationContext applicationContext;
	
	ObjectMapper mapper = new ObjectMapper();
	
	//当前登陆的设备列表
	private HashMap<String, WebSocketClient> devices = new HashMap();
	private HashMap<String, BaseRequest> sends = new HashMap<String, BaseRequest>();

	/**
	 * 设备上线后 将设备信息放入在线设备列表
	 * @param 设备连接保持的websocket session
	 */
	public synchronized int addDevice(WebSocketSession session) {
		String no = (String) session.getAttributes().get("SerialNo");
		WebSocketClient client = devices.get(no);
		if (client == null) {
			client = new WebSocketClient();
		}
		
		client.setLoginTime((int) new Date().getTime());
		client.setSerialNo(no);
		client.setSession(session);
		
		devices.put(no, client);
		System.out.println("curr connect device number: " + devices.size());
		return 0;
	}
	
	/**
	 * 从在线列表删除指定设备
	 * @param session 设备连接保持的websocket session
	 */
	public synchronized int removeDevice(WebSocketSession session) {
		devices.remove(session.getAttributes().get("SerialNo"));
		return 0;
	}

	/**
	 * 获取指定序列号的设备信息
	 * @param deviceId 设备的序列号
	 * 
	 */
	public synchronized WebSocketClient getClient(String deviceId) {
		return devices.get(deviceId);
	}

	/**
	 * 获取发送序列号
	 */
	public int getId() {
		if (id == Integer.MAX_VALUE) {
			id = 0;
		}
		return id++;
	}

	/**
	 * @param session 传来消息的session
	 * @param payload 消息内容
	 */
	public String messageIn(WebSocketSession session, String payload) {
		System.out.println("curr wait response: " + sends.size() + " -- messageIn " + payload);
		String method = "";
		int id;
		try {
			if (payload != null && payload.length() > 0) {
				JsonNode node = mapper.readTree(payload);
				
				JsonNode idNode = node.get("id");
				if (idNode != null) {
					id = idNode.asInt();
					sends.remove(String.valueOf(id));
				}
				
				JsonNode methodNode = node.get("method");
				if (methodNode == null) {
					return null;
				}
				method = methodNode.asText();
			}
			
			WebsocketPacketHandleEvent event = new WebsocketPacketHandleEvent(applicationContext);
			event.setMethod(method);
			event.setPayload(payload);
			event.setSession(session);
			applicationContext.publishEvent(event);
			
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 获取当前设备在线数量
	 * @return 在线设备数量
	 */
	public int deviceCount() {
		return devices.size();
	}

	/**
	 *  查询当前在线的设备
	 *  @return 设备列表
	 */
	public List<WebSocketClient> listDevice() {
		System.out.println("client size " + deviceCount() + "--");
		List<WebSocketClient> clients = new ArrayList<WebSocketClient>();
		Iterator<Entry<String, WebSocketClient>> iterator = devices.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<String, WebSocketClient> client = iterator.next();
			System.out.println(client.getValue().getSerialNo());
			WebSocketClient client2 = new WebSocketClient();
			client2.setLoginTime(client.getValue().getLoginTime());
			client2.setSerialNo(client.getValue().getSerialNo());
			clients.add(client2);
		}
		return clients;
	}

	public int send(String deviceId, BaseRequest request) {
		int res = 0;
		WebSocketClient client = getClient(deviceId);
		
		if (client == null) {
			return 1;
		}
		
		try {
			System.out.println(mapper.writeValueAsString(request));
			client.getSession().sendMessage(new TextMessage(mapper.writeValueAsBytes(request)));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			res = 1;
		} catch (IOException e) {
			e.printStackTrace();
			res = 1;
		}
		
		sends.put(String.valueOf(request.getId()), request);
		return res;
	}

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
}
