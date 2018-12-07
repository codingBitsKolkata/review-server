package com.orastays.review.reviewserver.converter;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.orastays.review.reviewserver.entity.UserReviewEntity;
import com.orastays.review.reviewserver.helper.Util;
import com.orastays.review.reviewserver.model.UserReviewModel;

@Component
public class UserReviewConverter extends CommonConverter implements BaseConverter<UserReviewEntity, UserReviewModel> {

	private static final long serialVersionUID = 3884426657377245322L;
	private static final Logger logger = LogManager.getLogger(UserReviewConverter.class);

	@Override
	public UserReviewEntity modelToEntity(UserReviewModel m) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserReviewModel entityToModel(UserReviewEntity e) {

		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- START");
		}

		UserReviewModel userReviewModel = new UserReviewModel();
		userReviewModel = (UserReviewModel) Util.transform(modelMapper, e, userReviewModel);

		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- END");
		}

		return userReviewModel;
	}

	@Override
	public List<UserReviewModel> entityListToModelList(List<UserReviewEntity> es) {

		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- START");
		}

		List<UserReviewModel> userReviewModels = null;
		if (!CollectionUtils.isEmpty(es)) {
			userReviewModels = new ArrayList<>();
			for (UserReviewEntity userReviewEntity : es) {
				userReviewModels.add(entityToModel(userReviewEntity));
			}
		}

		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- END");
		}

		return userReviewModels;
	}

}
