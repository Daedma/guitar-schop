package com.mycompany.app.dao.mongo;

import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import com.mongodb.client.MongoDatabase;
import com.mycompany.app.dao.GoodDAO;
import com.mycompany.app.models.Good;

public class GoodMongoDAO extends BaseMongoDAO<Good> implements GoodDAO {
	public GoodMongoDAO(MongoDatabase database) {
		super(database, "Goods", Good.class);
	}

	@Override
	protected ObjectId getId(Good entity) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'getId'");
	}

	@Override
	protected Bson createUpdateOperation(Good entity) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'createUpdateOperation'");
	}
}
