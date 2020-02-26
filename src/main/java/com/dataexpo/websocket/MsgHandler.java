package com.dataexpo.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;
import org.springframework.web.socket.handler.BinaryWebSocketHandler;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.dataexpo.serviceinterface.WSServiceInterface;

public class MsgHandler extends AbstractWebSocketHandler {

	@Autowired
	WSServiceInterface wsServiceInterface;
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("handleTextMessage" + message.getPayload().toString());
		wsServiceInterface.messageIn(session, message.getPayload());
		super.handleTextMessage(session, message);
	}
	
	@Override
	protected void handleBinaryMessage(WebSocketSession session, BinaryMessage message) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("handleBinaryMessage");
		super.handleBinaryMessage(session, message);
	}
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("afterConnectionEstablished");
		System.out.println(session.getAttributes().get("SerialNo"));
		wsServiceInterface.addDevice(session);
		super.afterConnectionEstablished(session);
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("afterConnectionClosed " + session.getAttributes().get("SerialNo"));
		wsServiceInterface.removeDevice(session);
		super.afterConnectionClosed(session, status);
	}
	
	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("handleTransportError");
		super.handleTransportError(session, exception);
	}
}
