package com.example.ec.explorecali.domain;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class TourRating {

	@Id
	private String id;

	private String tourId;

	@NotNull
	private Integer customerId;

	@Min(value = 0)
	@Max(value = 5)
	private Integer score;

	@Size(max = 255)
	private String comment;

	public TourRating(String tourId, Integer customerId, Integer score, String comment) {
		super();
		this.tourId = tourId;
		this.customerId = customerId;
		this.score = score;
		this.comment = comment;
	}

	protected TourRating() {
		super();
	}

	public String getId() {
		return id;
	}

	public String getTourId() {
		return tourId;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public Integer getScore() {
		return score;
	}

	public String getComment() {
		return comment;
	}

	public void setScore(Integer score) {
        this.score = score;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((comment == null) ? 0 : comment.hashCode());
		result = prime * result + ((customerId == null) ? 0 : customerId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((score == null) ? 0 : score.hashCode());
		result = prime * result + ((tourId == null) ? 0 : tourId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TourRating other = (TourRating) obj;
		if (comment == null) {
			if (other.comment != null)
				return false;
		} else if (!comment.equals(other.comment))
			return false;
		if (customerId == null) {
			if (other.customerId != null)
				return false;
		} else if (!customerId.equals(other.customerId))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (score == null) {
			if (other.score != null)
				return false;
		} else if (!score.equals(other.score))
			return false;
		if (tourId == null) {
			if (other.tourId != null)
				return false;
		} else if (!tourId.equals(other.tourId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TourRating [id=" + id + ", tourId=" + tourId + ", customerId=" + customerId + ", score=" + score
				+ ", comment=" + comment + "]";
	}

}