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
public class BookingVsRatingModel extends CommonModel {

	@JsonProperty("bookingRatingId")
	private String bookingRatingId;
	
	@JsonProperty("propertyId")
	private String propertyId;
	
	@JsonProperty("rating")
	private String rating;
	
	@JsonProperty("ratingS")
	private RatingModel ratingModel;
	
	@JsonProperty("userReview")
	private UserReviewModel userReviewModel;
}
