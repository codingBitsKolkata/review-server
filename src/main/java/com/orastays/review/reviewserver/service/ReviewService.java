package com.orastays.review.reviewserver.service;

import com.orastays.review.reviewserver.exceptions.FormExceptions;
import com.orastays.review.reviewserver.model.UserReviewModel;

public interface ReviewService {
	
	void addReview(UserReviewModel userReviewModel) throws FormExceptions;
}