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
@Table(name = "master_rating")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class RatingEntity extends CommonEntity {

	private static final long serialVersionUID = -6888486102054620409L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "rating_id")
	@JsonProperty("ratingId")
	private Long ratingId;

	@Column(name = "rating_name")
	@JsonProperty("ratingName")
	private String ratingName;
	
	@Column(name = "user_type_id")
	@JsonProperty("userTypeId")
	private String userTypeId;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "ratingEntity", cascade = { CascadeType.ALL })
	@JsonProperty("bookingVsRatings")
	private List<BookingVsRatingEntity> bookingVsRatingEntities;
	

	@Override
	public String toString() {
		return Long.toString(ratingId);

	}
}
