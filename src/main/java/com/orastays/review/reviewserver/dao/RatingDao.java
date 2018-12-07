package com.orastays.review.reviewserver.dao;

import org.springframework.stereotype.Repository;

import com.orastays.review.reviewserver.entity.RatingEntity;

@Repository
public class RatingDao extends GenericDAO<RatingEntity, Long> {

	private static final long serialVersionUID = -6366319022310192764L;

	public RatingDao() {
		super(RatingEntity.class);

	}
}
