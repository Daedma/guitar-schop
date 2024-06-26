package com.mycompany.app.dao.mongo;

import java.util.Arrays;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Updates;
import com.mycompany.app.dao.GuitarDAO;
import com.mycompany.app.filters.GuitarFilter;
import com.mycompany.app.models.Guitar;

public class GuitarMongoDAO extends BaseMongoDAO<Guitar> implements GuitarDAO {

	public GuitarMongoDAO(MongoDatabase database) {
		super(database, "goods_collection", Guitar.class);
	}

	@Override
	protected ObjectId getId(Guitar entity) {
		return entity.getId();
	}

	@Override
	protected Bson createUpdateOperation(Guitar entity) {
		return Updates.combine(
				Updates.set("categories_id", entity.getCategories()),
				Updates.set("cost", entity.getCost()),
				Updates.set("description", entity.getDescription()),
				Updates.set("form", entity.getGuitarForm()),
				Updates.set("frets", entity.getGuitarFrets()),
				Updates.set("images", entity.getImages()),
				Updates.set("name", entity.getName()),
				Updates.set("rate", entity.getRate()),
				Updates.set("remaining", entity.getRemaining()));
	}

	@Override
	public Iterable<Guitar> findByFilter(GuitarFilter filter) {
		Document query = new Document();
		return collection.find(filter.createMongoQuery(query));
	}

	@Override
	public Iterable<String> getGuitarForms() {
		return collection.distinct("guitarForm", String.class);
	}

	@Override
	public Iterable<String> getGuitarFrets() {
		return collection.distinct("guitarFrets", String.class);
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
