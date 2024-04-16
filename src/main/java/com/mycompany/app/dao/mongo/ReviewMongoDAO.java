package com.mycompany.app.dao.mongo;

import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mycompany.app.dao.ReviewDAO;
import com.mycompany.app.models.Review;

public class ReviewMongoDAO extends BaseMongoDAO<Review> implements ReviewDAO {
	public ReviewMongoDAO(MongoDatabase database) {
		super(database, "reviews_collection", Review.class);
	}

	@Override
	public Iterable<Review> getAllReviewsOfGood(String goodId) {
		return collection.find(Filters.eq("goods_id", new ObjectId(goodId)));
	}

	@Override
	protected ObjectId getId(Review entity) {
		return entity.getId();
	}

	@Override
	protected Bson createUpdateOperation(Review entity) {
		return Updates.combine(
				Updates.set("autor", entity.getAuthor()),
				Updates.set("content", entity.getContent()),
				Updates.set("goods", entity.getGoods()),
				Updates.set("photos", entity.getPhotos()),
				Updates.set("publichingDate", entity.getPublishingDate()),
				Updates.set("rate", entity.getRate()));

		///	FIXME
	}
}
