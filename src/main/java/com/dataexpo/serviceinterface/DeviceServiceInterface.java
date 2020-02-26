package com.dataexpo.serviceinterface;

import java.util.List;

import com.dataexpo.domain.WebSocketClient;

public interface DeviceServiceInterface {
	public int deviceCount();
	public List<WebSocketClient> listDevice();
}
