package com.mycompany.app.dao.mongo;

import java.util.Arrays;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Updates;
import com.mycompany.app.dao.StringsDAO;
import com.mycompany.app.filters.StringsFilter;
import com.mycompany.app.models.Strings;

public class StringsMongoDAO extends BaseMongoDAO<Strings> implements StringsDAO {

	public StringsMongoDAO(MongoDatabase database, String collectionName, Class<Strings> entityClass) {
		super(database, "Goods", Strings.class);
	}

	@Override
	protected ObjectId getId(Strings entity) {
		return entity.getId();
	}

	@Override
	protected Bson createUpdateOperation(Strings entity) {
		return Updates.combine(
				Updates.set("categories", entity.getCategories()),
				Updates.set("cost", entity.getCost()),
				Updates.set("description", entity.getDescription()),
				Updates.set("images", entity.getImages()),
				Updates.set("name", entity.getName()),
				Updates.set("rate", entity.getRate()),
				Updates.set("remaining", entity.getRemaining()),
				Updates.set("stringGauge", entity.getStringGauge()),
				Updates.set("stringMaterial", entity.getStringMaterial()));
	}

	@Override
	public Iterable<Strings> findByFilter(StringsFilter filter) {
		Document query = new Document();
		return collection.find(filter.createMongoQuery(query));
	}

	@Override
	public Float getMaxCost() {
		return collection.aggregate(Arrays.asList(
				Aggregates.group(null, Accumulators.max("maxPrice", "$cost"))), Document.class).first()
				.get("maxPrice", Float.class);
	}

	@Override
	public Float getMinCost() {
		return collection.aggregate(Arrays.asList(
				Aggregates.group(null, Accumulators.min("minPrice", "$cost"))), Document.class).first()
				.get("minPrice", Float.class);
	}

	@Override
	public Iterable<String> getStringGauges() {
		return collection.distinct("stringGauges", String.class);
	}

	@Override
	public Iterable<String> getStringMaterials() {
		return collection.distinct("stringMaterial", String.class);
	}

}
