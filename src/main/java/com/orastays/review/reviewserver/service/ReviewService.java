package com.orastays.review.reviewserver.service;

import com.orastays.review.reviewserver.exceptions.FormExceptions;
import com.orastays.review.reviewserver.model.UserReviewModel;

public interface ReviewService {
	
	UserReviewModel addReview(UserReviewModel userReviewModel) throws FormExceptions;
	
	UserReviewModel fetchReviewedUserIdPropertyId(String userId, String PropertyId);
}