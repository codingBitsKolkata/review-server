package com.orastays.review.reviewserver.converter;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.orastays.review.reviewserver.entity.RatingEntity;
import com.orastays.review.reviewserver.helper.Util;
import com.orastays.review.reviewserver.model.RatingModel;

@Component
public class RatingConverter extends CommonConverter implements BaseConverter<RatingEntity, RatingModel> {

	private static final long serialVersionUID = 63301011990813995L;
	private static final Logger logger = LogManager.getLogger(RatingConverter.class);

	@Override
	public RatingEntity modelToEntity(RatingModel m) {
		return null;
	}

	@Override
	public RatingModel entityToModel(RatingEntity e) {

		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- START");
		}

		RatingModel ratingModel = new RatingModel();
		ratingModel = (RatingModel) Util.transform(modelMapper, e, ratingModel);

		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- END");
		}

		return ratingModel;
	}

	@Override
	public List<RatingModel> entityListToModelList(List<RatingEntity> es) {

		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- START");
		}

		List<RatingModel> ratingModels = null;
		if (!CollectionUtils.isEmpty(es)) {
			ratingModels = new ArrayList<>();
			for (RatingEntity ratingEntity : es) {
				ratingModels.add(entityToModel(ratingEntity));
			}
		}

		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- END");
		}

		return ratingModels;
	}

}
