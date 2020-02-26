package com.dataexpo.listener;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;

import com.dataexpo.trans.BaseResponse;

@Component
public class WebsocketPacketHandleEvent extends ApplicationEvent {
	private String method;
	private String payload;
	
	public WebsocketPacketHandleEvent(ApplicationContext source) {
		super(source);
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getPayload() {
		return payload;
	}

	public void setPayload(String payload) {
		this.payload = payload;
	}

	
}
