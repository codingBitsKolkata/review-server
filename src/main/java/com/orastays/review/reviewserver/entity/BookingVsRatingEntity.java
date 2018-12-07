package com.orastays.review.reviewserver.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "booking_vs_rating")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class BookingVsRatingEntity extends CommonEntity {

	private static final long serialVersionUID = 6401988080736350238L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "booking_rating_id")
	private Long bookingRatingId;

	@Column(name = "property_id")
	private Long propertyId;

	@Column(name = "rating")
	private String rating;

	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
	@JoinColumn(name = "user_review_id", nullable = false)
	private UserReviewEntity userReviewEntity;

	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
	@JoinColumn(name = "rating_id", nullable = false)
	private RatingEntity ratingEntity;

	@Override
	public String toString() {
		return Long.toString(bookingRatingId);

	}
}
