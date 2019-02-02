package com.orastays.reviewserver.dao;

import org.springframework.stereotype.Repository;

import com.orastays.reviewserver.entity.BookingVsRatingEntity;

@Repository
public class BookingVsRatingDAO extends GenericDAO<BookingVsRatingEntity, Long> {

	private static final long serialVersionUID = 7465659978138206955L;

	public BookingVsRatingDAO() {
		super(BookingVsRatingEntity.class);

	}
}
