package com.mycompany.app.dao.mongo;

import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import com.mongodb.client.MongoDatabase;
import com.mycompany.app.dao.GuitarDAO;
import com.mycompany.app.models.Guitar;

public class GuitarMongoDAO extends BaseMongoDAO<Guitar> implements GuitarDAO {

	public GuitarMongoDAO(MongoDatabase database) {
		super(database, "Goods", Guitar.class);
	}

	@Override
	protected ObjectId getId(Guitar entity) {
		throw new UnsupportedOperationException("Unimplemented method 'getId'");
	}

	@Override
	protected Bson createUpdateOperation(Guitar entity) {
		throw new UnsupportedOperationException("Unimplemented method 'createUpdateOperation'");
	}

}
