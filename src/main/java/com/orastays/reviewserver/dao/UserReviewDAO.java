package com.orastays.reviewserver.dao;

import org.springframework.stereotype.Repository;

import com.orastays.reviewserver.entity.UserReviewEntity;

@Repository
public class UserReviewDAO extends GenericDAO<UserReviewEntity, Long> {

	private static final long serialVersionUID = 8409163169527763776L;

	public UserReviewDAO() {
		super(UserReviewEntity.class);

	}
}
