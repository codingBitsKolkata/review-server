package com.orastays.reviewserver.converter;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.orastays.reviewserver.entity.BookingVsRatingEntity;
import com.orastays.reviewserver.helper.Util;
import com.orastays.reviewserver.model.BookingVsRatingModel;

@Component
public class BookingVsRatingConverter extends CommonConverter
		implements BaseConverter<BookingVsRatingEntity, BookingVsRatingModel> {

	private static final long serialVersionUID = 8133759655680477064L;
	private static final Logger logger = LogManager.getLogger(BookingVsRatingConverter.class);

	@Override
	public BookingVsRatingEntity modelToEntity(BookingVsRatingModel m) {
		return null;
	}

	@Override
	public BookingVsRatingModel entityToModel(BookingVsRatingEntity e) {

		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- START");
		}

		BookingVsRatingModel bookingVsRatingModel = new BookingVsRatingModel();
		bookingVsRatingModel = (BookingVsRatingModel) Util.transform(modelMapper, e, bookingVsRatingModel);
		bookingVsRatingModel.setRatingModel(ratingConverter.entityToModel(e.getRatingEntity()));

		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- END");
		}

		return bookingVsRatingModel;
	}

	@Override
	public List<BookingVsRatingModel> entityListToModelList(List<BookingVsRatingEntity> es) {

		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- START");
		}

		List<BookingVsRatingModel> bookingVsRatingModels = null;
		if (!CollectionUtils.isEmpty(es)) {
			bookingVsRatingModels = new ArrayList<>();
			for (BookingVsRatingEntity bookingVsRatingEntity : es) {
				bookingVsRatingModels.add(entityToModel(bookingVsRatingEntity));
			}
		}

		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- END");
		}

		return bookingVsRatingModels;
	}

}
