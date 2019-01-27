package com.orastays.review.reviewserver.validation;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.google.gson.Gson;
import com.orastays.review.reviewserver.entity.RatingEntity;
import com.orastays.review.reviewserver.exceptions.FormExceptions;
import com.orastays.review.reviewserver.helper.Status;
import com.orastays.review.reviewserver.helper.Util;
import com.orastays.review.reviewserver.model.BookingModel;
import com.orastays.review.reviewserver.model.BookingVsRatingModel;
import com.orastays.review.reviewserver.model.PropertyModel;
import com.orastays.review.reviewserver.model.ResponseModel;
import com.orastays.review.reviewserver.model.UserModel;
import com.orastays.review.reviewserver.model.UserReviewModel;
import com.orastays.review.reviewserver.model.UserVsTypeModel;

@Component
@Transactional
public class ReviewValidation extends AuthorizeUserValidation {

	private static final Logger logger = LogManager.getLogger(ReviewValidation.class);

	//Validation while adding review
	public UserReviewModel validateAddReview(UserReviewModel userReviewModel) throws FormExceptions {
		
		if (logger.isInfoEnabled()) {
			logger.info("validateAddReview -- Start");
		}
		
		Map<String, Exception> exceptions = new LinkedHashMap<>();
		UserModel userModel = getUserDetails(userReviewModel.getUserToken());
		
		if(Objects.nonNull(userReviewModel) && Objects.nonNull(userModel)) {
			
			// Check User Type Id
			if(StringUtils.isBlank(userReviewModel.getUserTypeId())) {
				exceptions.put(messageUtil.getBundle("usertype.id.null.code"), new Exception(messageUtil.getBundle("usertype.id.null.message")));
			} else {
				if(!CollectionUtils.isEmpty(userModel.getUserVsTypes())) {
					UserVsTypeModel result1 = userModel.getUserVsTypes().stream() .filter(x -> userReviewModel.getUserTypeId().equals(x.getUserType().getUserTypeId())).findAny().orElse(null);
					if(Objects.isNull(result1)) {
						exceptions.put(messageUtil.getBundle("usertype.invalid.null.code"), new Exception(messageUtil.getBundle("usertype.invalid.null.message")));
					}
				}
			}
			
			//Check propertyId for null
			if(StringUtils.isBlank(userReviewModel.getPropertyId())) {
				exceptions.put(messageUtil.getBundle("property.id.null.code"), new Exception(messageUtil.getBundle("property.id.null.message")));
			} else {
				//Check propertyId for number
				if(!Util.isNumeric(userReviewModel.getPropertyId())){
					exceptions.put(messageUtil.getBundle("property.id.invalid.code"), new Exception(messageUtil.getBundle("property.id.invalid.message")));
				} 
			}
			
			if (exceptions.size() > 0)
				throw new FormExceptions(exceptions);
			
			//Check bookingId for null and userId for null
			if(StringUtils.isBlank(userReviewModel.getBookingId())) {
				exceptions.put(messageUtil.getBundle("booking.id.null.code"), new Exception(messageUtil.getBundle("booking.id.null.message")));
			} else {
				if(!Util.isNumeric(userReviewModel.getBookingId())) {
					exceptions.put(messageUtil.getBundle("booking.id.invalid.code"), new Exception(messageUtil.getBundle("booking.id.invalid.message")));
				} else {
					userReviewModel.setUserId(userModel.getUserId());
					validateBooking(userReviewModel);
				}
			}
			
			if (exceptions.size() > 0)
				throw new FormExceptions(exceptions);
			
			//validate comment in review for null
			if(StringUtils.isBlank(userReviewModel.getComment())) {
				exceptions.put(messageUtil.getBundle("comment.null.code"), new Exception(messageUtil.getBundle("comment.null.message")));
			}
			
			//validate languageID
			validateLanguage(userReviewModel.getLanguageId());
			
			if (exceptions.size() > 0)
				throw new FormExceptions(exceptions);
			
			//Validate BookingVsRating
			//Validate rating and ratingId
			if(Objects.isNull(userReviewModel.getBookingVsRatingModels())) {
				exceptions.put(messageUtil.getBundle("rating.null.code"), new Exception(messageUtil.getBundle("rating.null.message")));
			} else {
				for(BookingVsRatingModel bookingVsRatingModel:userReviewModel.getBookingVsRatingModels()) {
					
					if(Objects.nonNull(bookingVsRatingModel)) {
						//Check rating for null
						if(StringUtils.isBlank(bookingVsRatingModel.getRating())){
							exceptions.put(messageUtil.getBundle("rating.null.code"), new Exception(messageUtil.getBundle("rating.null.message")));
						} else {
							if(!Util.isNumeric(bookingVsRatingModel.getRating())) {
								exceptions.put(messageUtil.getBundle("rating.invalid.code"), new Exception(messageUtil.getBundle("rating.invalid.message")));
							} else {
								if(Objects.isNull(bookingVsRatingModel.getRatingModel())) {
									exceptions.put(messageUtil.getBundle("rating.id.null.code"), new Exception(messageUtil.getBundle("rating.id.null.message")));
								} else {
									if(StringUtils.isBlank(bookingVsRatingModel.getRatingModel().getRatingId())) {
										exceptions.put(messageUtil.getBundle("rating.id.null.code"), new Exception(messageUtil.getBundle("rating.id.null.message")));
									} else {
										if(!Util.isNumeric(bookingVsRatingModel.getRatingModel().getRatingId())) {
											exceptions.put(messageUtil.getBundle("rating.id.null.code"), new Exception(messageUtil.getBundle("rating.id.null.message")));
										} else {
											RatingEntity ratingEntity = ratingDAO.find(Long.parseLong(bookingVsRatingModel.getRatingModel().getRatingId()));
											if(Objects.isNull(ratingEntity)) {
												exceptions.put(messageUtil.getBundle("rating.invalid.code"), new Exception(messageUtil.getBundle("rating.invalid.message")));
											} else {
												if(ratingEntity.getStatus() != Status.ACTIVE.ordinal()) {
													exceptions.put(messageUtil.getBundle("rating.notactive.code"), new Exception(messageUtil.getBundle("rating.notactive.message")));
												}
											}
										}
									}
								}
							}
						}
					} else {
						exceptions.put(messageUtil.getBundle("rating.null.code"), new Exception(messageUtil.getBundle("rating.null.message")));
					}
				}
			}
			
			//Check whether the user has already given review for that particular property
			if(checkReviewForUser(userReviewModel.getPropertyId(), userReviewModel.getUserId())) {
				exceptions.put(messageUtil.getBundle("review.present.code"), new Exception(messageUtil.getBundle("review.present.message")));
			}
		}
		
		if (exceptions.size() > 0)
			throw new FormExceptions(exceptions);
		
		if (logger.isInfoEnabled()) {
			logger.info("validateAddReview -- End");
		}	
		return userReviewModel;
	}

	//Call Booking server with bookingId and userId
	private void validateBooking(UserReviewModel userReviewModel) throws FormExceptions {
		
		if (logger.isInfoEnabled()) {
			logger.info("validateBooking -- START");
		}
		
		Map<String, Exception> exceptions = new LinkedHashMap<>();
		BookingModel bookingModel = null;
		try {
			ResponseModel responseModel = restTemplate.postForObject(messageUtil.getBundle("booking.server.url") + "validate-booking", userReviewModel, ResponseModel.class);
			Gson gson = new Gson();
			String jsonString = gson.toJson(responseModel.getResponseBody());
			bookingModel = gson.fromJson(jsonString, BookingModel.class);
			if(Objects.isNull(bookingModel)) {
				exceptions.put(messageUtil.getBundle("booking.id.invalid.code"), new Exception(messageUtil.getBundle("booking.id.invalid.message")));
			} else {
				if(!StringUtils.equals(bookingModel.getPropertyId(), userReviewModel.getPropertyId())) {
					exceptions.put(messageUtil.getBundle("property.id.invalid.code"), new Exception(messageUtil.getBundle("property.id.invalid.message")));
				}
			}
		} catch (Exception e) {
				//e.printStackTrace();
				// Disabled the below line to pass the Token Validation
				exceptions.put(messageUtil.getBundle("booking.id.invalid.code"), new Exception(messageUtil.getBundle("booking.id.invalid.message")));
			}
		if (exceptions.size() > 0)
			throw new FormExceptions(exceptions);
		
		if (logger.isInfoEnabled()) {
			logger.info("validateBooking -- END");
		}
	}

	//Call property-list server with propertyId
	public void validateProperty(String propertyId) throws FormExceptions {
		
		if (logger.isInfoEnabled()) {
			logger.info("validateProperty -- START");
		}
		
		Map<String, Exception> exceptions = new LinkedHashMap<>();
		//Check propertyId for null
		if(StringUtils.isBlank(propertyId)) {
			exceptions.put(messageUtil.getBundle("property.id.null.code"), new Exception(messageUtil.getBundle("property.id.null.message")));
		} else {
			//Check propertyId for number
			if(!Util.isNumeric(propertyId)){
				exceptions.put(messageUtil.getBundle("property.id.invalid.code"), new Exception(messageUtil.getBundle("property.id.invalid.message")));
			} else {
				
				//Get the property model with respective fields
				PropertyModel propertyModel = null;
				try {
					ResponseModel responseModel = restTemplate.getForObject(messageUtil.getBundle("propertylist.server.url") +"fetch-property-by-id?propertyId="+propertyId, ResponseModel.class);
					Gson gson = new Gson();
					String jsonString = gson.toJson(responseModel.getResponseBody());
					propertyModel = gson.fromJson(jsonString, PropertyModel.class);
					if(Objects.isNull(propertyModel)) {
						exceptions.put(messageUtil.getBundle("property.id.invalid.code"), new Exception(messageUtil.getBundle("property.id.invalid.message")));
					} 
				} catch (Exception e) {
					//e.printStackTrace();
					// Disabled the below line to pass the Token Validation
					exceptions.put(messageUtil.getBundle("property.id.invalid.code"), new Exception(messageUtil.getBundle("property.id.invalid.message")));
				}
				
				if (exceptions.size() > 0)
					throw new FormExceptions(exceptions);
			}
		}
		
		if (logger.isInfoEnabled()) {
			logger.info("validateProperty -- END");
		}
	}
	
	private boolean checkReviewForUser(String propertyId, String userId) {
		
		if (logger.isInfoEnabled()) {
			logger.info("validateProperty -- START");
		}
		
		try {
			Map<String, String> innerMap1 = new LinkedHashMap<>();
			innerMap1.put("status", String.valueOf(Status.ACTIVE.ordinal()));
			innerMap1.put("userId", userId);
			innerMap1.put("propertyId", propertyId);
	
			Map<String, Map<String, String>> outerMap1 = new LinkedHashMap<>();
			outerMap1.put("eq", innerMap1);
	
			Map<String, Map<String, Map<String, String>>> alliasMap = new LinkedHashMap<>();
			alliasMap.put(entitymanagerPackagesToScan+".UserReviewEntity", outerMap1);
	
			if(Objects.isNull(userReviewDAO.fetchObjectBySubCiteria(alliasMap))) {
				return false;
			}
		} catch (Exception e) {
		}
		
		if (logger.isInfoEnabled()) {
			logger.info("validateProperty -- END");
		}
		
		return true;
	}
}