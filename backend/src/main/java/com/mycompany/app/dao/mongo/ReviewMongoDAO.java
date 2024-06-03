package com.mycompany.app.dao.mongo;

import java.util.Arrays;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
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
				Updates.set("author_id", entity.getAuthor()),
				Updates.set("content", entity.getContent()),
				Updates.set("goods_id", entity.getGoods()),
				Updates.set("photos", entity.getPhotos()),
				Updates.set("publiching_date", entity.getPublishingDate()),
				Updates.set("rate", entity.getRate()));
	}

	@Override
	public Double getRateOfGood(String goodId) {
		Document avgResult = collection.aggregate(
				Arrays.asList(
						Aggregates.match(Filters.eq("goods_id", new ObjectId(goodId))),
						Aggregates.group("$goods_id", Accumulators.avg("averageRate", "$rate"))),
				Document.class)
				.first();

		Double averageRate = avgResult != null ? avgResult.getDouble("averageRate") : 0.0;
		return averageRate;
	}
}
