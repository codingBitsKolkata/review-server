package com.orastays.review.reviewserver.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.orastays.review.reviewserver.converter.RatingConverter;
import com.orastays.review.reviewserver.converter.UserReviewConverter;
import com.orastays.review.reviewserver.dao.RatingDAO;
import com.orastays.review.reviewserver.dao.UserReviewDAO;
import com.orastays.review.reviewserver.validation.ReviewValidation;

public abstract class BaseServiceImpl {

	@Value("${entitymanager.packagesToScan}")
	protected String entitymanagerPackagesToScan;
	
	@Autowired
	protected ReviewValidation reviewValidation;
	
	@Autowired
	protected UserReviewConverter userReviewConverter;
	
	@Autowired
	protected UserReviewDAO userReviewDAO;
	
	@Autowired
	protected RatingConverter ratingConverter;
	
	@Autowired
	protected RatingDAO ratingDAO;
}
