package com.mycompany.app.dao.mongo;

import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import com.mongodb.client.MongoDatabase;
import com.mycompany.app.dao.UserDAO;
import com.mycompany.app.models.User;
import com.mongodb.client.model.*;;

public class UserMongoDAO extends BaseMongoDAO<User> implements UserDAO {
	public UserMongoDAO(MongoDatabase database) {
		super(database, "users_collection", User.class);
	}

	@Override
	protected ObjectId getId(User entity) {
		return entity.getId();
	}

	@Override
	protected Bson createUpdateOperation(User entity) {
		return Updates.combine(
				Updates.set("login", entity.getLogin()),
				Updates.set("password", entity.getPassword()),
				Updates.set("role", entity.getRole()),
				Updates.set("cart", entity.getCart()));
	}

	@Override
	public User findUserByLogin(String login) {
		return collection.find(Filters.eq("login", login)).first();
	}
}
