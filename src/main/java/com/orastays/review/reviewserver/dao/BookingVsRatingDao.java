package com.orastays.review.reviewserver.dao;

import org.springframework.stereotype.Repository;

import com.orastays.review.reviewserver.entity.BookingVsRatingEntity;

@Repository
public class BookingVsRatingDao extends GenericDAO<BookingVsRatingEntity, Long> {

	private static final long serialVersionUID = 7465659978138206955L;

	public BookingVsRatingDao() {
		super(BookingVsRatingEntity.class);

	}
}
