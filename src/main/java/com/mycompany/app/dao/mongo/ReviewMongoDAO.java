package com.mycompany.app.dao.mongo;

import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import com.mongodb.client.MongoDatabase;
import com.mycompany.app.dao.ReviewDAO;
import com.mycompany.app.models.Review;

public class ReviewMongoDAO extends BaseMongoDAO<Review> implements ReviewDAO {
	public ReviewMongoDAO(MongoDatabase database) {
		super(database, "Reviews", Review.class);
	}

	@Override
	protected ObjectId getId(Review entity) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'getId'");
	}

	@Override
	protected Bson createUpdateOperation(Review entity) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'createUpdateOperation'");
	}
}
