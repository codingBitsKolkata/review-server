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
public class RatingModel extends CommonModel {
	
	@JsonProperty("ratingId")
	private String ratingId;
	
	@JsonProperty("ratingName")
	private String ratingName;
	
	@JsonProperty("bookingVsRatings")
	private List<BookingVsRatingModel> bookingVsRatingModels;

}
