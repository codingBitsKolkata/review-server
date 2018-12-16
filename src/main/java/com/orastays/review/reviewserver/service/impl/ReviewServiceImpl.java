package com.orastays.review.reviewserver.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.orastays.review.reviewserver.entity.BookingVsRatingEntity;
import com.orastays.review.reviewserver.entity.UserReviewEntity;
import com.orastays.review.reviewserver.exceptions.FormExceptions;
import com.orastays.review.reviewserver.helper.Status;
import com.orastays.review.reviewserver.helper.Util;
import com.orastays.review.reviewserver.model.BookingVsRatingModel;
import com.orastays.review.reviewserver.model.RatingModel;
import com.orastays.review.reviewserver.model.UserReviewModel;
import com.orastays.review.reviewserver.service.ReviewService;

@Service
@Transactional
public class ReviewServiceImpl extends BaseServiceImpl implements ReviewService {
	
	private static final Logger logger = LogManager.getLogger(ReviewServiceImpl.class);

	@Override
	public void addReview(UserReviewModel userReviewModel) throws FormExceptions {

		if (logger.isInfoEnabled()) {
			logger.info("addReview -- START");
		}
		
		reviewValidation.validateAddReview(userReviewModel);
		UserReviewEntity userReviewEntity = userReviewConverter.modelToEntity(userReviewModel);
		Long userReviewId = (Long) userReviewDAO.save(userReviewEntity);
		
		for(BookingVsRatingModel bookingVsRatingModel:userReviewModel.getBookingVsRatingModels()) {
			BookingVsRatingEntity bookingVsRatingEntity = new BookingVsRatingEntity();
			bookingVsRatingEntity.setRatingEntity(ratingDAO.find(Long.parseLong(bookingVsRatingModel.getRatingModel().getRatingId())));
			bookingVsRatingEntity.setUserReviewEntity(userReviewDAO.find(userReviewId));
			bookingVsRatingEntity.setRating(bookingVsRatingModel.getRating());
			bookingVsRatingEntity.setStatus(Status.ACTIVE.ordinal());
			bookingVsRatingEntity.setCreatedBy(Long.parseLong(userReviewModel.getUserId()));
			bookingVsRatingEntity.setCreatedDate(Util.getCurrentDateTime());
			bookingVsRatingDAO.save(bookingVsRatingEntity);
		}
		
		if (logger.isInfoEnabled()) {
			logger.info("addReview -- END");
		}
	}

	@Override
	public List<UserReviewModel> fetchReview(UserReviewModel userReviewModel) throws FormExceptions {
		
		if (logger.isInfoEnabled()) {
			logger.info("addReview -- START");
		}
		
		List<UserReviewModel> userReviewModels = null;
		try {
			Map<String, String> innerMap1 = new LinkedHashMap<>();
			innerMap1.put("status", String.valueOf(Status.ACTIVE.ordinal()));
			innerMap1.put("propertyId", userReviewModel.getPropertyId());
	
			Map<String, Map<String, String>> outerMap1 = new LinkedHashMap<>();
			outerMap1.put("eq", innerMap1);
	
			Map<String, Map<String, Map<String, String>>> alliasMap = new LinkedHashMap<>();
			alliasMap.put(entitymanagerPackagesToScan+".UserReviewEntity", outerMap1);
			
			userReviewModels = new ArrayList<>();
			userReviewModels = userReviewConverter.entityListToModelList(userReviewDAO.fetchListBySubCiteria(alliasMap));
			
		} catch (Exception e) {
		}
		
		if (logger.isInfoEnabled()) {
			logger.info("addReview -- END");
		}
		
		return userReviewModels;
	}

	@Override
	public List<RatingModel> fetchRating(String userToken) throws FormExceptions {
		
		if (logger.isInfoEnabled()) {
			logger.info("fetchRating -- START");
		}
		
		reviewValidation.getUserDetails(userToken);
		List<RatingModel> ratingModels = null;
		try {
			Map<String, String> innerMap1 = new LinkedHashMap<>();
			innerMap1.put("status", String.valueOf(Status.ACTIVE.ordinal()));
	
			Map<String, Map<String, String>> outerMap1 = new LinkedHashMap<>();
			outerMap1.put("eq", innerMap1);
	
			Map<String, Map<String, Map<String, String>>> alliasMap = new LinkedHashMap<>();
			alliasMap.put(entitymanagerPackagesToScan+".RatingEntity", outerMap1);
			
			ratingModels = new ArrayList<>();
			ratingModels = ratingConverter.entityListToModelList(ratingDAO.fetchListBySubCiteria(alliasMap));
			
		} catch (Exception e) {
		}
		
		if (logger.isInfoEnabled()) {
			logger.info("fetchRating -- END");
		}
		
		return ratingModels;
	}
}