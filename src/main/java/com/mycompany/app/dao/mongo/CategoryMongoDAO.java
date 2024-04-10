package com.mycompany.app.dao.mongo;

import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Updates;
import com.mycompany.app.dao.CategoryDAO;
import com.mycompany.app.models.Category;

public class CategoryMongoDAO extends BaseMongoDAO<Category> implements CategoryDAO {
	public CategoryMongoDAO(MongoDatabase database) {
		super(database, "Categories", Category.class);
	}

	@Override
	protected ObjectId getId(Category entity) {
		return entity.getId();
	}

	@Override
	protected Bson createUpdateOperation(Category entity) {
		return Updates.combine(
				Updates.set("goods", entity.getGoods()),
				Updates.set("name", entity.getName()));
	}
}
