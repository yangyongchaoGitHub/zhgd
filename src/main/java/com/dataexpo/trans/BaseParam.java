package com.dataexpo.trans;

import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/*@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "typeName")
@JsonSubTypes({ @JsonSubTypes.Type(value = Person.class, name = "Person"),
		@JsonSubTypes.Type(value = UserCode.class, name = "UserCode"),
		@JsonSubTypes.Type(value = UserDeleteResBody.class, name = "UserDeleteResBody")})
@JsonInclude(JsonInclude.Include.NON_NULL)*/
@Deprecated
public class BaseParam {

}
