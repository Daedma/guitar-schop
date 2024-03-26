package com.mycompany.app.dao.mongo;

import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import com.mongodb.client.MongoDatabase;
import com.mycompany.app.dao.CategoryDAO;
import com.mycompany.app.models.Category;

public class CategoryMongoDAO extends BaseMongoDAO<Category> implements CategoryDAO {
	public CategoryMongoDAO(MongoDatabase database) {
		super(database, "Categories", Category.class);
	}

	@Override
	protected ObjectId getId(Category entity) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'getId'");
	}

	@Override
	protected Bson createUpdateOperation(Category entity) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'createUpdateOperation'");
	}
}
