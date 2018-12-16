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
public class BookingModel extends CommonModel {

	@JsonProperty("bookingId")
	private String bookingId;

	@JsonProperty("orabookingId")
	private String orabookingId;

	@JsonProperty("userId")
	private String userId;

	@JsonProperty("propertyId")
	private String propertyId;

	@JsonProperty("checkinDate")
	private String checkinDate;

	@JsonProperty("checkoutDate")
	private String checkoutDate;

	@JsonProperty("numOfDays")
	private String numOfDays;

	@JsonProperty("totalPaybleWithoutGST")
	private String totalPaybleWithoutGST;

	@JsonProperty("totalPaybleWithGST")
	private String totalPaybleWithGST;

	@JsonProperty("grandTotal")
	private String grandTotal;

}