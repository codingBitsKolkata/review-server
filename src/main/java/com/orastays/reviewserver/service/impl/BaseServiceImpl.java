package com.orastays.reviewserver.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.orastays.reviewserver.converter.RatingConverter;
import com.orastays.reviewserver.converter.UserReviewConverter;
import com.orastays.reviewserver.dao.BookingVsRatingDAO;
import com.orastays.reviewserver.dao.RatingDAO;
import com.orastays.reviewserver.dao.UserReviewDAO;
import com.orastays.reviewserver.validation.ReviewValidation;

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
	
	@Autowired
	protected BookingVsRatingDAO bookingVsRatingDAO;
}
