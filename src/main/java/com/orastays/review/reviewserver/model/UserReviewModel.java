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
public class UserReviewModel extends CommonModel {

	private String userReviewId;
	private String userId;
	private String bookingId;
	private String comment;
	private String languageId;
	private String parentId;
	private List<BookingVsRatingModel> bookingVsRatingModels;

}
