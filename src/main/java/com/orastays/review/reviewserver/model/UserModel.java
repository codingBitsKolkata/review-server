/**
 * @author Abhideep
 */
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
public class UserModel extends CommonModel {

	@JsonProperty("userId")
	private String userId;
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("mobileNumber")
	private String mobileNumber;
	
	@JsonProperty("emailId")
	private String emailId;
}