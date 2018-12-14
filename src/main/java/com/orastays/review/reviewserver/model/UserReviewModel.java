package com.orastays.review.reviewserver.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
public class UserReviewModel extends CommonModel {

	@JsonProperty("userReviewId")
	private String userReviewId;
	
	@JsonProperty("userId")
	private String userId;
	
	@JsonProperty("bookingId")
	private String bookingId;
	
	@JsonProperty("propertyId")
	private String propertyId;
	
	@JsonProperty("comment")
	private String comment;
	
	@JsonProperty("languageId")
	private String languageId;
	
	@JsonProperty("parentId")
	private String parentId;
	
	@JsonProperty("bookingVsRatings")
	private List<BookingVsRatingModel> bookingVsRatingModels;
}
