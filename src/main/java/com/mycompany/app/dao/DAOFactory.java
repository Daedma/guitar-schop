package com.mycompany.app.dao;

public interface DAOFactory {
	UserDAO createUserDAO();

	OrderDAO createOrderDAO();

	CategoryDAO createCategoryDAO();

	GoodDAO createGoodDAO();

	ReviewDAO createReviewDAO();
}
