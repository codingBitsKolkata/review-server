package com.orastays.review.reviewserver.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
public class BookingVsRatingModel extends CommonModel {

	private String bookingRatingId;
	private String propertyId;
	private String rating;
	private RatingModel ratingModel;
	private UserReviewModel userReviewModel;
}
