package com.mycompany.app.dao.mongo;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Updates;
import com.mycompany.app.dao.GoodDAO;
import com.mycompany.app.filters.GoodFilter;
import com.mycompany.app.models.Good;

public class GoodMongoDAO extends BaseMongoDAO<Good> implements GoodDAO {
	public GoodMongoDAO(MongoDatabase database) {
		super(database, "Goods", Good.class);
	}

	@Override
	protected ObjectId getId(Good entity) {
		return entity.getId();
	}

	@Override
	protected Bson createUpdateOperation(Good entity) {
		return Updates.combine(
				Updates.set("categories", entity.getCategories()),
				Updates.set("cost", entity.getCost()),
				Updates.set("description", entity.getDescription()),
				Updates.set("images", entity.getImages()),
				Updates.set("name", entity.getName()),
				Updates.set("rate", entity.getRate()),
				Updates.set("ramaining", entity.getRemaining()));
	}

	@Override
	public Iterable<Good> findByFilter(GoodFilter filter) {
		Document query = new Document();
		return collection.find(filter.createMongoQuery(query));
	}

}
