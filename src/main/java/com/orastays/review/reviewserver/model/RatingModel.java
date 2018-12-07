package com.orastays.review.reviewserver.model;

import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
public class RatingModel extends CommonModel {
	
	private String ratingId;
	private String ratingName;
	private List<BookingVsRatingModel> bookingVsRatingModels;

}
