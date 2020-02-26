package com.dataexpo.domain;

import org.springframework.web.socket.WebSocketSession;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * websocket客户端实体类
 * @author pc
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WebSocketClient {
	//登陆时间
	private int loginTime;
	//设备编码 每个设备的编码都是独一无二的
	private String serialNo;
	private WebSocketSession session;
	
	public int getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(int loginTime) {
		this.loginTime = loginTime;
	}

	public String getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	public WebSocketSession getSession() {
		return session;
	}
	public void setSession(WebSocketSession session) {
		this.session = session;
	}
}
