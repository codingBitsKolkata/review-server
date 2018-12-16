package com.orastays.review.reviewserver.service;

import java.util.List;

import com.orastays.review.reviewserver.exceptions.FormExceptions;
import com.orastays.review.reviewserver.model.RatingModel;
import com.orastays.review.reviewserver.model.UserReviewModel;

public interface ReviewService {
	
	void addReview(UserReviewModel userReviewModel) throws FormExceptions;

	List<UserReviewModel> fetchReview(UserReviewModel userReviewModel) throws FormExceptions;

	List<RatingModel> fetchRating(String userToken) throws FormExceptions;
}