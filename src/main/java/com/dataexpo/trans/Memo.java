package com.dataexpo.trans;

/**
 * optional 其它信息
 * @author pc
 *
 */
public class Memo {
	//门禁位置
	private String Entrance;

	public Memo(String entrance) {
		Entrance = entrance;
	}
	
	public String getEntrance() {
		return Entrance;
	}

	public void setEntrance(String entrance) {
		Entrance = entrance;
	}
	
}
