package com.orastays.review.reviewserver.validation;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.orastays.review.reviewserver.exceptions.FormExceptions;
import com.orastays.review.reviewserver.helper.ReviewConstant;
import com.orastays.review.reviewserver.helper.Util;
import com.orastays.review.reviewserver.model.BookingVsRatingModel;
import com.orastays.review.reviewserver.model.RatingModel;
import com.orastays.review.reviewserver.model.ResponseModel;
import com.orastays.review.reviewserver.model.UserModel;
import com.orastays.review.reviewserver.model.UserReviewModel;

@Component
@Transactional
public class ReviewValidation extends AuthorizeUserValidation {

	private static final Logger logger = LogManager.getLogger(ReviewValidation.class);

	//Validation while adding review
	public UserReviewModel validateAddReview(UserReviewModel userReviewModel) throws FormExceptions {
		
		if (logger.isDebugEnabled()) {
			logger.debug("validateAddReview -- Start");
		}
		
		Util.printLog(userReviewModel, ReviewConstant.INCOMING, "Review Add", request);
		Map<String, Exception> exceptions = new LinkedHashMap<>();
		
		if(Objects.nonNull(userReviewModel)) {
			
			//Get the user-id and call auth server via parent id
			//Check userToken for null
			if(StringUtils.isBlank(userReviewModel.getUserToken())) {
				exceptions.put(messageUtil.getBundle("token.null.code"), new Exception(messageUtil.getBundle("token.null.code")));
			} else {
				//Validate the token
				getUserDetails(userReviewModel.getUserToken());
			}
			
			//Check propertyId for null
			if(StringUtils.isBlank(userReviewModel.getPropertyId())) {
				exceptions.put(messageUtil.getBundle("propertyId.null.code"), new Exception(messageUtil.getBundle("propertyId.null.code")));
			} else {
				//Check propertyId for number
				if(Util.isNumeric(userReviewModel.getPropertyId())){
					exceptions.put(messageUtil.getBundle("propertyId.number.invalid.code"), new Exception(messageUtil.getBundle("propertyId.number.invalid.code")));
				} 
				//Call property-list server using Rest template
				validatePropertyInAddReview(userReviewModel.getPropertyId());
				}
			
			//Check bookingId for null and userId for null
			if(StringUtils.isBlank(userReviewModel.getUserId())) {
				exceptions.put(messageUtil.getBundle("user.null.code"), new Exception(messageUtil.getBundle("user.null.code")));
				if(StringUtils.isBlank(userReviewModel.getBookingId())) {
					exceptions.put(messageUtil.getBundle("bookingId.null.code"), new Exception(messageUtil.getBundle("bookingId.null.code")));
				} 
			} else {
				//Check booking-id for number
				if(Util.isNumeric(userReviewModel.getBookingId())){
					exceptions.put(messageUtil.getBundle("bookingId.number.invalid.code"), new Exception(messageUtil.getBundle("bookingId.number.invalid.code")));
				} 
				//Call booking server using Rest template with bookingID and userID
				validateBookingInAddReview(userReviewModel.getBookingId(), userReviewModel.getUserId());
			}			
			
			//validate comment in review for null
			if(StringUtils.isBlank(userReviewModel.getComment())) {
				exceptions.put(messageUtil.getBundle("comment.null.code"), new Exception(messageUtil.getBundle("comment.null.message")));
			}
			
			//validate languageID
			if(StringUtils.isBlank(userReviewModel.getLanguageId())) {
				exceptions.put(messageUtil.getBundle("language.id.null.code"), new Exception(messageUtil.getBundle("language.id.null.message")));
			} else {
				validateLanguage(userReviewModel.getLanguageId());
			}
			
			//Validate BookingVsRating
			//Validate rating and ratingId
			if(Objects.isNull(userReviewModel.getBookingVsRatingModels())) {
				exceptions.put(messageUtil.getBundle("rating.null.code"), new Exception(messageUtil.getBundle("rating.null.message")));
			} else {
				for(BookingVsRatingModel bookingVsRatingModel:userReviewModel.getBookingVsRatingModels()){
					//Check rating for null
					if(StringUtils.isBlank(bookingVsRatingModel.getRating())){
						exceptions.put(messageUtil.getBundle("rating.null.code"), new Exception(messageUtil.getBundle("rating.null.message")));
					}
					//Check rating for number
					if(Util.isNumeric(bookingVsRatingModel.getRating())){
						exceptions.put(messageUtil.getBundle("rating.number.invalid.code"), new Exception(messageUtil.getBundle("rating.number.invalid.message")));
					}
					//Check ratingId for null
					if(StringUtils.isBlank(bookingVsRatingModel.getBookingRatingId())){
						exceptions.put(messageUtil.getBundle("ratingId.null.code"), new Exception(messageUtil.getBundle("ratingID.null.message")));
					}
					//Check ratingId for number
					if(Util.isNumeric(bookingVsRatingModel.getBookingRatingId())){
						exceptions.put(messageUtil.getBundle("ratingId.number.invalid.code"), new Exception(messageUtil.getBundle("ratingID.number.invalid.message")));
					} else {
						//Check ratingId for active and present in DB
						//rating Id from Ui, Check if that id is present in db and then check if its status is 1
						RatingModel ratingModel2 = reviewService.fetchRatingStatus(bookingVsRatingModel.getRatingModel().getRatingId());
						if(Objects.isNull(ratingModel2)) {
							exceptions.put(messageUtil.getBundle("rating.present.invalid.code"), new Exception(messageUtil.getBundle("rating.present.invalid.message")));
						} else if (ratingModel2.getStatus().equals(ReviewConstant.STATUS_INACTIVE) ) {
							exceptions.put(messageUtil.getBundle("rating.active.invalid.code"), new Exception(messageUtil.getBundle("rating.active.invalid.message")));
						}
					}
				}
			}
			//Check whether the user has already given review for that particular property
			UserReviewModel userReviewModel2 = reviewService.fetchReviewedUserIdPropertyId(userReviewModel.getUserId(), userReviewModel.getPropertyId());
			if(Objects.nonNull(userReviewModel2)){
				exceptions.put(messageUtil.getBundle("review.present.code"), new Exception(messageUtil.getBundle("review.present.message")));
			}
		}
		
		if (exceptions.size() > 0)
			throw new FormExceptions(exceptions);
		
		if (logger.isDebugEnabled()) {
			logger.debug("validateAddReview -- End");
		}	
		return userReviewModel;
	}

	//Call Booking server with bookingId and userId
	private void validateBookingInAddReview(String bookingId, String userId) throws FormExceptions {
		
		if (logger.isInfoEnabled()) {
			logger.info("validateBookingInAddReview -- START");
		}
		
		Map<String, Exception> exceptions = new LinkedHashMap<>();
		UserModel userModel = null;
		try {
			ResponseModel responseModel = restTemplate.getForObject("http://BOOKING-SERVER/api/check-booking?bookingId="+bookingId+"&userId="+userId, ResponseModel.class);
			userModel = (UserModel) responseModel.getResponseBody();
			if(Objects.isNull(userModel)) {
				exceptions.put(messageUtil.getBundle("booking.invalid.code"), new Exception(messageUtil.getBundle("booking.invalid.message")));
			} 
		} catch (Exception e) {
				e.printStackTrace();
			}
		if (exceptions.size() > 0)
			throw new FormExceptions(exceptions);
		
		if (logger.isInfoEnabled()) {
			logger.info("validateBookingInAddReview -- END");
		}
	}

	//Call property-list server with propertyId
	private void validatePropertyInAddReview(String propertyId) throws FormExceptions {
		
		if (logger.isInfoEnabled()) {
			logger.info("validatePropertyInAddReview -- START");
		}
		
		Map<String, Exception> exceptions = new LinkedHashMap<>();
		//Get the property model with respective fields
		UserModel userModel = null;
		try {
			ResponseModel responseModel = restTemplate.getForObject("http://PROPERTY-LIST-SERVER/api/check-property?propertyId="+propertyId, ResponseModel.class);
			userModel = (UserModel) responseModel.getResponseBody();
			if(Objects.isNull(userModel)) {
				exceptions.put(messageUtil.getBundle("property.invalid.code"), new Exception(messageUtil.getBundle("property.invalid.message")));
			} 
		} catch (Exception e) {
				e.printStackTrace();
			}
		if (exceptions.size() > 0)
			throw new FormExceptions(exceptions);
		
		if (logger.isInfoEnabled()) {
			logger.info("validatePropertyInAddReview -- END");
		}
	}
}