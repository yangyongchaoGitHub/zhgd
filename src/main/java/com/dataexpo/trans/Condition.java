package com.dataexpo.trans;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 进行用户查询时使用的查询参数
 * @author pc
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Condition {
	//人员类型
	private int type;
	//编号模糊条件
	private String codeLike;
	//姓名模糊条件
	private String nameLike;
	//分页起始号
	private int offset;
	//每页最大条数
	private int limit;
	
	@JsonProperty("Type")
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	@JsonProperty("CodeLike")
	public String getCodeLike() {
		return codeLike;
	}
	public void setCodeLike(String codeLike) {
		this.codeLike = codeLike;
	}
	@JsonProperty("NameLike")
	public String getNameLike() {
		return nameLike;
	}
	public void setNameLike(String nameLike) {
		this.nameLike = nameLike;
	}
	@JsonProperty("Offset")
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	@JsonProperty("Limit")
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
}
