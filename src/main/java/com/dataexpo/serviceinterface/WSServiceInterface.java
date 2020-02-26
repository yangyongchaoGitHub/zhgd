package com.dataexpo.serviceinterface;

import java.util.List;

import org.springframework.web.socket.WebSocketSession;

import com.dataexpo.domain.WebSocketClient;
import com.dataexpo.trans.BaseRequest;
import com.dataexpo.trans.Condition;
import com.dataexpo.trans.Person;

public interface WSServiceInterface {
	public int addDevice(WebSocketSession session);
	public int removeDevice(WebSocketSession session);
	public WebSocketClient getClient(String deviceId);
	public int deviceCount();
	public List<WebSocketClient> listDevice();
	
	public int getId();
	
	public String messageIn(WebSocketSession session, String payload);
	
	public int send(String deviceId, BaseRequest request);
}
