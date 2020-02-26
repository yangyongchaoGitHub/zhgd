package com.dataexpo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.dataexpo.domain.WebSocketClient;
import com.dataexpo.serviceinterface.DeviceServiceInterface;
import com.dataexpo.serviceinterface.WSServiceInterface;

@Component
@Service("DeviceService")
public class DeviceService implements DeviceServiceInterface {
	@Autowired
	WSServiceInterface wsService;
	
	public int deviceCount() {
		return wsService.deviceCount();
	}

	public List<WebSocketClient> listDevice() {
		return wsService.listDevice();
	}

}
