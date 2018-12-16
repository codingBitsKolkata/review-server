package com.orastays.review.reviewserver.service.impl;

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
}