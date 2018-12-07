package com.orastays.review.reviewserver.dao;

import org.springframework.stereotype.Repository;

import com.orastays.review.reviewserver.entity.UserReviewEntity;

@Repository
public class UserReviewDao extends GenericDAO<UserReviewEntity, Long> {

	private static final long serialVersionUID = 8409163169527763776L;

	public UserReviewDao() {
		super(UserReviewEntity.class);

	}
}
