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

	public StringsMongoDAO(MongoDatabase database) {
		super(database, "goods_collection", Strings.class);
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
				//Updates.set("images", entity.getImages()),
				Updates.set("name", entity.getName()),
				Updates.set("rate", entity.getRate()),
				Updates.set("remaining", entity.getRemaining()),
				Updates.set("gauge", entity.getStringGauge()),
				Updates.set("material", entity.getStringMaterial()));
	}

	@Override
	public Iterable<Strings> findByFilter(StringsFilter filter) {
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

	@Override
	public Iterable<String> getStringGauges() {
		return collection.distinct("stringGauges", String.class);
	}

	@Override
	public Iterable<String> getStringMaterials() {
		return collection.distinct("stringMaterial", String.class);
	}

}
