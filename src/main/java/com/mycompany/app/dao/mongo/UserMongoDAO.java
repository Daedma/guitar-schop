package com.mycompany.app.dao.mongo;

import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import com.mongodb.client.MongoDatabase;
import com.mycompany.app.dao.UserDAO;
import com.mycompany.app.models.User;

public class UserMongoDAO extends BaseMongoDAO<User> implements UserDAO {
	public UserMongoDAO(MongoDatabase database) {
		super(database, "Users", User.class);
	}

	@Override
	protected ObjectId getId(User entity) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'getId'");
	}

	@Override
	protected Bson createUpdateOperation(User entity) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'createUpdateOperation'");
	}
}
