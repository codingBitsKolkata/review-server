package com.orastays.reviewserver.service;

import java.util.List;

import com.orastays.reviewserver.exceptions.FormExceptions;
import com.orastays.reviewserver.model.RatingModel;
import com.orastays.reviewserver.model.UserReviewModel;

public interface ReviewService {
	
	void addReview(UserReviewModel userReviewModel) throws FormExceptions;

	List<UserReviewModel> fetchReview(UserReviewModel userReviewModel) throws FormExceptions;

	List<RatingModel> fetchRating(String userTypeId) throws FormExceptions;
}