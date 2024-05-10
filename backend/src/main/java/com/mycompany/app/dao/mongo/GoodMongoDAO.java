package com.mycompany.app.dao.mongo;

import java.util.Arrays;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Updates;
import com.mycompany.app.dao.GoodDAO;
import com.mycompany.app.filters.GoodFilter;
import com.mycompany.app.models.Good;

public class GoodMongoDAO extends BaseMongoDAO<Good> implements GoodDAO {
	public GoodMongoDAO(MongoDatabase database) {
		super(database, "goods_collection", Good.class);
	}

	@Override
	protected ObjectId getId(Good entity) {
		return entity.getId();
	}

	@Override
	protected Bson createUpdateOperation(Good entity) {
		return Updates.combine(
				Updates.set("categories_id", entity.getCategories()),
				Updates.set("cost", entity.getCost()),
				Updates.set("description", entity.getDescription()),
				Updates.set("images", entity.getImages()),
				Updates.set("name", entity.getName()),
				Updates.set("rate", entity.getRate()),
				Updates.set("remaining", entity.getRemaining()));
	}

	@Override
	public Iterable<Good> findByFilter(GoodFilter filter) {
		Document query = new Document();
		return collection.find(filter.createMongoQuery(query));
	}

	@Override
	public Double getMaxCost() {
		return collection.aggregate(Arrays.asList(
				Aggregates.group(null, Accumulators.max("maxPrice", "$cost"))), Document.class).first()
				.get("maxPrice", Double.class);
	}

	@Override
	public Double getMinCost() {
		return collection.aggregate(Arrays.asList(
				Aggregates.group(null, Accumulators.min("minPrice", "$cost"))), Document.class).first()
				.get("minPrice", Double.class);
	}
}
