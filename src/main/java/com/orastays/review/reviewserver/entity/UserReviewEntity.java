package com.orastays.review.reviewserver.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "master_user_review")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class UserReviewEntity extends CommonEntity {

	
	private static final long serialVersionUID = -6995023550914743935L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_review_id")
	private Long userReviewId;

	@Column(name = "user_id")
	private Long userId;

	@Column(name = "property_id")
	private Long propertyId;

	@Column(name = "booking_id")
	private Long bookingId;

	@Column(name = "comment")
	private String comment;

	@Column(name = "language_id")
	private Long languageId;

	@Column(name = "parent_id")
	private Long parentId;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userReviewEntity", cascade = { CascadeType.ALL })
	private List<BookingVsRatingEntity> bookingVsRatingEntities;

	@Override
	public String toString() {
		return Long.toString(userReviewId);

	}
}
