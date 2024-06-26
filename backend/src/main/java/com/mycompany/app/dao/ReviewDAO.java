package com.mycompany.app.dao;

import com.mycompany.app.models.Review;

public interface ReviewDAO extends BaseDAO<Review> {
	Iterable<Review> getAllReviewsOfGood(String goodId);

	Double getRateOfGood(String goodId);
}
