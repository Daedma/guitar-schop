package com.mycompany.app.dao.mongo;

import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Updates;
import com.mycompany.app.dao.OrderDAO;
import com.mycompany.app.models.Order;

public class OrderMongoDAO extends BaseMongoDAO<Order> implements OrderDAO {
	public OrderMongoDAO(MongoDatabase database) {
		super(database, "orders_collection", Order.class);
	}

	@Override
	protected ObjectId getId(Order entity) {
		return entity.getId();
	}

	@Override
	protected Bson createUpdateOperation(Order entity) {
		return Updates.combine(
				Updates.set("address", entity.getAddress()),
				Updates.set("cost", entity.getCost()),
				Updates.set("date", entity.getDate()),
				Updates.set("full_name", entity.getFullName()),
				Updates.set("goods", entity.getGoods()),
				Updates.set("status", entity.getStatus()));
	}
}
