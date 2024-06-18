package com.mycompany.app.dao.mongo;

import java.util.ArrayList;

import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mycompany.app.dao.CategoryDAO;
import com.mycompany.app.models.Category;

public class CategoryMongoDAO extends BaseMongoDAO<Category> implements CategoryDAO {
	public CategoryMongoDAO(MongoDatabase database) {
		super(database, "categories_collection", Category.class);
	}

	@Override
	protected ObjectId getId(Category entity) {
		return entity.getId();
	}

	@Override
	protected Bson createUpdateOperation(Category entity) {
		return Updates.combine(
				Updates.set("goods_id", entity.getGoods()),
				Updates.set("name", entity.getName()));
	}

	@Override
	public String addGood(String category, String goodId) {
		Category document = collection.find(Filters.eq("name", category)).first();
		if (document == null) {
			document = new Category();
			document.setGoods(new ArrayList<ObjectId>());
			document.setName(category);
			document.setId(new ObjectId(save(document)));
		}
		collection.updateOne(Filters.eq("name", category), Updates.push("goods_id", new ObjectId(goodId)));
		return document.getId().toString();
	}
}
