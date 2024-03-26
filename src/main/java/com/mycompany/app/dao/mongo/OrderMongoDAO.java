package com.mycompany.app.dao.mongo;

import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import com.mongodb.client.MongoDatabase;
import com.mycompany.app.dao.OrderDAO;
import com.mycompany.app.models.Order;

public class OrderMongoDAO extends BaseMongoDAO<Order> implements OrderDAO {
	public OrderMongoDAO(MongoDatabase database) {
		super(database, "Orders", Order.class);
	}

	@Override
	protected ObjectId getId(Order entity) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'getId'");
	}

	@Override
	protected Bson createUpdateOperation(Order entity) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'createUpdateOperation'");
	}
}
