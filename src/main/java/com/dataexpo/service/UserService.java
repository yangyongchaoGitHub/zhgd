package com.dataexpo.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.dataexpo.domain.WebSocketClient;
import com.dataexpo.listener.WebsocketPacketHandleEvent;
import com.dataexpo.serviceinterface.UserServiceInterface;
import com.dataexpo.serviceinterface.WSServiceInterface;
import com.dataexpo.trans.BaseRequest;
import com.dataexpo.trans.BaseResponse;
import com.dataexpo.trans.Condition;
import com.dataexpo.trans.Definds;
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
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
@Service("UserService")
public class UserService implements UserServiceInterface, 
									ApplicationListener<WebsocketPacketHandleEvent>, 
									BaseWebsocketPacketHandle{
	@Autowired
	WSServiceInterface wsService;
	
	ObjectMapper mapper = new ObjectMapper();
	
	/**
	 * 处理设备返回的添加用户返回信息
	 * @param session 返回设备的websocket session
	 * @param payload 返回的数据
	 * @return 
	 */
	public void responseWithUserAdd(UserAddResponse response) {
		//先从发送列表找出对应的发送消息 并删除
		
		// 判断返回值

		for (int i = 0; i < response.getParams().size(); i++) {
			UserAddResponseEntity entity = response.getParams().get(i);
			if (entity.isResult()) {
				System.out.println("用户：" + entity.getCode() + " 添加成功");
			} else {
				System.out.println("添加失败 " + entity.getErrorMessage() + " code: " + entity.getCode());
			}
		}

		/*
		 * for (int i = 0; i < resBody.getParams().size(); i++) { UserDeleteResBody
		 * deleteResBody = (UserDeleteResBody) resBody.getParams().get(i).getB();
		 * 
		 * }
		 */
	}
	
	/**
	 * 设备返回用户删除接口的调用
	 * @param session 设备的websocket session
	 * @param response 调用返回
	 */
	public void responseWithUserDelete(UserDeleteResponse response) {
		for (int i = 0; i < response.getParams().size(); i++) {
			UserDeleteResponseEntity entity = response.getParams().get(i);
			if (entity.getResult()) {
				System.out.println("用户：" + entity.getCode() + " 删除成功");
			} else {
				System.out.println("删除失败 " + entity.getErrorMessage() + " code: " + entity.getCode());
			}
		}
	}
	
	/**
	 *  设备返回用户查询接口调用
	 * @param session 设备的websocket session
	 * @param response 返回的设备数据
	 */
	public void responseWithUserSearch(UserSearchResponse response) {
		for (int i = 0; i < response.getParams().getPersons().size(); i++) {
			Person person = response.getParams().getPersons().get(i);
			System.out.println("查询到的用户：" + person.getName());
		}
	}

	/**
	 * 添加用户到设备给的接口
	 * @param deviceId 设备编号
	 * @param persons 用户列表
	 * return 发送成功返回0 失败返回1 （并不能确定已经成功录入设备，仅仅代表发送成功）
	 */
	public int addUser(String deviceId, List<Person> persons) {
		int res = 0;
		BaseRequest request = new BaseRequest();
		List<Params> params = new ArrayList<Params>();
		
		request.setId(wsService.getId());
		request.setMethod(Definds.USER_ADD);
		
		for (int i = 0; i < persons.size(); i++) {
			params.add(new UserAddParams(persons.get(i)));
		}
		
		request.setParams(params);
		
		res = wsService.send(deviceId, request);
		
		return res;
	}

	/**
	 * 从指定用户删除设备的接口
	 * @param deviceId 设备编号
	 * @param userCodeList 删除的用户的列表
	 * return 
	 */
	public int deleteUser(String deviceId, List<String> userCode) {
		int res = 0;
		BaseRequest request = new BaseRequest();
		List<UserDeleteParams> deleteParams = new ArrayList<UserDeleteParams>();
		
		request.setId(wsService.getId());
		
		request.setMethod(Definds.USER_REMOVE);
		
		for (int i = 0; i < userCode.size(); i++) {
			deleteParams.add(new UserDeleteParams(userCode.get(i)));
		}
		request.setParams(deleteParams);
		
		res = wsService.send(deviceId, request);
		return res;
	}

	/**
	 * 调用模糊查询进行用户数据获取的接口
	 * @param deviceId 查询的设备的序列号
	 * @condition 模糊查询的条件
	 */
	public int searchUser(String deviceId, Condition condition) {
		int res = 0;
		BaseRequest request = new BaseRequest();
		
		request.setId(wsService.getId());
		
		request.setMethod(Definds.USER_SEARCH);
		
		request.setParams(new UserSearchParams(condition));

		res = wsService.send(deviceId, request);
		return res;
	}

	/**
	 * 当有设备返回数据包时 触发监听时间
	 */
	public void onApplicationEvent(WebsocketPacketHandleEvent event) {
		System.out.println("UserService onApplicationEvent");
		String method = event.getMethod();
		String payload =  event.getPayload();
		
		try {
			if (Definds.USER_ADD.equals(method)) {
				System.out.println("-----" + Definds.USER_ADD);
				UserAddResponse addResponse = mapper.readValue(payload, UserAddResponse.class);
				if (checkPacket(addResponse) == 1) {
					return;
				}
				responseWithUserAdd(addResponse);
				
			} else if (Definds.USER_REMOVE.equals(method)) {
				System.out.println("-----" + Definds.USER_REMOVE);
				UserDeleteResponse deleteResponse = mapper.readValue(payload, UserDeleteResponse.class);
				if (checkPacket(deleteResponse) == 1) {
					return;
				}
				responseWithUserDelete(deleteResponse);

			} else if (Definds.USER_SEARCH.equals(method)) {
				UserSearchResponse searchResponse = mapper.readValue(payload, UserSearchResponse.class);
				System.out.println("-----" + Definds.USER_SEARCH);
				if (checkPacket(searchResponse) == 1) {
					return;
				}
				responseWithUserSearch(searchResponse);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int checkPacket(BaseResponse response) {
		if (!response.getResult() || response.getError() != null) {
			System.err.println("happen error on some transation " + response.getError().getCode());
			return 1;
		}
		return 0;
	}
	
}
