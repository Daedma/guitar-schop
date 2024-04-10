package com.mycompany.app;

import com.mycompany.app.dao.CategoryDAO;
import com.mycompany.app.dao.DAOFactory;
import com.mycompany.app.dao.GoodDAO;
import com.mycompany.app.dao.GuitarDAO;
import com.mycompany.app.dao.OrderDAO;
import com.mycompany.app.dao.ReviewDAO;
import com.mycompany.app.dao.StringsDAO;
import com.mycompany.app.dao.UserDAO;
import com.mycompany.app.dao.mongo.CategoryMongoDAO;
import com.mycompany.app.dao.mongo.MongoDAOFactory;
import com.mycompany.app.filters.StringsFilter;
import com.mycompany.app.models.Good;
import com.mycompany.app.models.Guitar;
import com.mycompany.app.models.Strings;
import com.mycompany.app.util.MongoDBConnection;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		MongoDBConnection connection = new MongoDBConnection(null, null);
		DAOFactory daoFactory = new MongoDAOFactory(connection.getDatabase());

		CategoryDAO categoryDAO = daoFactory.createCategoryDAO();

		GoodDAO goodDAO = daoFactory.createGoodDAO();

		GuitarDAO guitarDAO = daoFactory.createGuitarDAO();

		OrderDAO orderDAO = daoFactory.createOrderDAO();

		ReviewDAO reviewDAO = daoFactory.createReviewDAO();

		StringsDAO stringsDAO = daoFactory.createStringsDAO();

		UserDAO userDAO = daoFactory.createUserDAO();

		Iterable<Good> goods = goodDAO.findAll();

		System.out.println("LIST OF GOODS:");
		System.out.println(goods);

		System.out.println("Max cost: " + goodDAO.getMaxCost());

		System.out.println("List of guitar fets:" + guitarDAO.getGuitarFrets());

		StringsFilter filter = new StringsFilter();
		filter.setStringGauge("10-46");
		Strings strings = stringsDAO.findByFilter(filter).iterator().next();

		System.out.println("Find string by filter: " + filter);
		System.out.println(strings);

		System.out.println("Reviews of this strings:");
		System.out.println(reviewDAO.getAllReviewsOfGood(strings.getId().toString()));
	}
}
