package com.mycompany.app.dao.mongo;

import com.mongodb.client.MongoDatabase;
import com.mycompany.app.dao.CategoryDAO;
import com.mycompany.app.dao.DAOFactory;
import com.mycompany.app.dao.GoodDAO;
import com.mycompany.app.dao.OrderDAO;
import com.mycompany.app.dao.ReviewDAO;
import com.mycompany.app.dao.UserDAO;

public class MongoDAOFactory implements DAOFactory {
	private final MongoDatabase database;

	public MongoDAOFactory(MongoDatabase database) {
		this.database = database;
	}

	@Override
	public UserDAO createUserDAO() {
		return new UserMongoDAO(database);
	}

	@Override
	public OrderDAO createOrderDAO() {
		return new OrderMongoDAO(database);
	}

	@Override
	public CategoryDAO createCategoryDAO() {
		return new CategoryMongoDAO(database);
	}

	@Override
	public GoodDAO createGoodDAO() {
		return new GoodMongoDAO(database);
	}

	@Override
	public ReviewDAO createReviewDAO() {
		return new ReviewMongoDAO(database);
	}
}