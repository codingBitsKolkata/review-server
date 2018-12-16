package com.orastays.review.reviewserver.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
public class PropertyModel extends CommonModel {

	@JsonProperty("propertyId")
	private Long propertyId;

	@JsonProperty("name")
	private String name;

	@JsonProperty("oraname")
	private String oraname;
}
