package com.orastays.reviewserver.entity;

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

import com.fasterxml.jackson.annotation.JsonProperty;

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
	@JsonProperty("userReviewId")
	private Long userReviewId;

	@Column(name = "user_id")
	@JsonProperty("userId")
	private Long userId;

	@Column(name = "property_id")
	@JsonProperty("propertyId")
	private Long propertyId;

	@Column(name = "booking_id")
	@JsonProperty("bookingId")
	private Long bookingId;

	@Column(name = "comment")
	@JsonProperty("comment")
	private String comment;

	@Column(name = "language_id")
	@JsonProperty("languageId")
	private Long languageId;

	@Column(name = "parent_id")
	@JsonProperty("parentId")
	private Long parentId;
	
	@Column(name = "user_type_id")
	@JsonProperty("userTypeId")
	private String userTypeId;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userReviewEntity", cascade = { CascadeType.ALL })
	@JsonProperty("bookingVsRatings")
	private List<BookingVsRatingEntity> bookingVsRatingEntities;

	@Override
	public String toString() {
		return Long.toString(userReviewId);

	}
}
