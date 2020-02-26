package com.dataexpo.service;

import com.dataexpo.trans.BaseResponse;

public interface BaseWebsocketPacketHandle {
	int checkPacket(BaseResponse response);
}
