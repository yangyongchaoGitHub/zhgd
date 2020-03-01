package com.dataexpo.trans.face;

public class FacePacket {
	public static int STATUS_USING = 1;
	public static int STATUS_INDESTROY = 2;
	public static int STATUS_DESTROY = 3;
	//创建时间
	private long create;
	//查询组的id
	private long packetId;
	//查询组当前状态
	private int status;
	//查询组删除时对应的发送id
	private int destroyId;
	
	public long getCreate() {
		return create;
	}
	public void setCreate(long create) {
		this.create = create;
	}
	public long getPacketId() {
		return packetId;
	}
	public void setPacketId(long packetId) {
		this.packetId = packetId;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getDestroyId() {
		return destroyId;
	}
	public void setDestroyId(int destroyId) {
		this.destroyId = destroyId;
	}
	
}
