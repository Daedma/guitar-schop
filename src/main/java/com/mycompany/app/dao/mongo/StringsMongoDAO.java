package com.mycompany.app.dao.mongo;

import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import com.mongodb.client.MongoDatabase;
import com.mycompany.app.dao.StringsDAO;
import com.mycompany.app.models.Strings;

public class StringsMongoDAO extends BaseMongoDAO<Strings> implements StringsDAO {

	public StringsMongoDAO(MongoDatabase database, String collectionName, Class<Strings> entityClass) {
		super(database, "Goods", Strings.class);
	}

	@Override
	protected ObjectId getId(Strings entity) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'getId'");
	}

	@Override
	protected Bson createUpdateOperation(Strings entity) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'createUpdateOperation'");
	}

}
