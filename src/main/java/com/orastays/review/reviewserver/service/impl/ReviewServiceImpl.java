package com.orastays.review.reviewserver.service.impl;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.orastays.review.reviewserver.entity.UserReviewEntity;
import com.orastays.review.reviewserver.exceptions.FormExceptions;
import com.orastays.review.reviewserver.model.UserReviewModel;
import com.orastays.review.reviewserver.service.ReviewService;

@Service
@Transactional
public class ReviewServiceImpl extends BaseServiceImpl implements ReviewService {
	
	private static final Logger logger = LogManager.getLogger(ReviewServiceImpl.class);

	@Override
	public UserReviewModel addReview(UserReviewModel userReviewModel) throws FormExceptions {

		if (logger.isInfoEnabled()) {
			logger.info("addReview Service-- START");
		}
		
		reviewValidation.validateAddReview(userReviewModel);
		UserReviewEntity userReviewEntity = userReviewConverter.modelToEntity(userReviewModel);
		Long userReviewId = (Long) userReviewDAO.save(userReviewEntity);
		/*
		UserEntity userEntity = userConverter.modelToEntity(userModel);
		userEntity.setCountryEntity(countryEntity);
		Long userId = (Long) userDAO.save(userEntity);
		UserTypeEntity userTypeEntity = userTypeDAO.find(Long.parseLong(String.valueOf(UserType.USER.ordinal())));
		UserVsTypeEntity userVsTypeEntity = new UserVsTypeEntity();
		UserEntity userEntity2 = userDAO.find(userId);
		userVsTypeEntity.setUserEntity(userEntity2);
		userVsTypeEntity.setUserTypeEntity(userTypeEntity);
		userVsTypeEntity.setStatus(Status.INACTIVE.ordinal());
		userVsTypeEntity.setCreatedBy(Long.parseLong(String.valueOf(Status.ZERO.ordinal())));
		userVsTypeEntity.setCreatedDate(Util.getCurrentDateTime());
		userVsTypeDAO.save(userVsTypeEntity);
		userModel = userConverter.entityToModel(userEntity2);
		smsHelper.sendSMS(userModel);
		mailHelper.sendMail(userModel);*/
		
		if (logger.isInfoEnabled()) {
			logger.info("addReview Service-- END");
		}
		return userReviewModel;
	}

	@Override
	public UserReviewModel fetchReviewedUserIdPropertyId(String userId, String propertyId) {
		
		if (logger.isInfoEnabled()) {
			logger.info("fetchReviewedUserIdPropertyId -- START");
		}

		UserReviewModel userReviewModel = null;
		try {
			Map<String, String> innerMap1 = new LinkedHashMap<>();
			innerMap1.put("userId", userId);
			innerMap1.put("propertyId", propertyId);
	
			Map<String, Map<String, String>> outerMap1 = new LinkedHashMap<>();
			outerMap1.put("eq", innerMap1);
	
			Map<String, Map<String, Map<String, String>>> alliasMap = new LinkedHashMap<>();
			alliasMap.put(entitymanagerPackagesToScan+".UserReviewEntity", outerMap1);
	
			userReviewModel = userReviewConverter.entityToModel(userReviewDAO.fetchObjectBySubCiteria(alliasMap));
		} catch (Exception e) {
			logger.info("Exception occured in fetchReviewedUserIdPropertyId");
		}
		if (logger.isInfoEnabled()) {
			logger.info("fetchReviewedUserIdPropertyId -- END");
		}
		
		return userReviewModel;
	}
}
