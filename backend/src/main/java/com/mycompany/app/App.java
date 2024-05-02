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
import com.mycompany.app.models.Review;
import com.mycompany.app.models.Strings;
import com.mycompany.app.util.MongoDBConnection;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		MongoDBConnection connection = new MongoDBConnection("mongodb://localhost:27017", "guitar_shop_DB");
		DAOFactory daoFactory = new MongoDAOFactory(connection.getDatabase());

		CategoryDAO categoryDAO = daoFactory.createCategoryDAO();

		GoodDAO goodDAO = daoFactory.createGoodDAO();

		GuitarDAO guitarDAO = daoFactory.createGuitarDAO();

		OrderDAO orderDAO = daoFactory.createOrderDAO();

		ReviewDAO reviewDAO = daoFactory.createReviewDAO();

		StringsDAO stringsDAO = daoFactory.createStringsDAO();

		UserDAO userDAO = daoFactory.createUserDAO();

//		List<Good> goods = connection.getDatabase().getCollection("Goods", Good.class);
		Iterable<Good> goods = goodDAO.findAll();

		System.out.println("LIST OF GOODS:");
//		System.out.println(goods);
		for (Good g:
			 goods) {
			System.out.println(g);
		}

		System.out.println("Max cost: " + goodDAO.getMaxCost());

		System.out.println("List of guitar frets:");
		for (String f:
			 guitarDAO.getGuitarFrets()) {
			System.out.println(f);
		}

		StringsFilter filter = new StringsFilter();
		filter.setStringGauge("10-46");
//		filter.setSortBy("");
		Strings strings = stringsDAO.findByFilter(filter).iterator().next();

		System.out.println("Find string by filter: " + filter);
		System.out.println(strings);

		System.out.println("Reviews of this strings:");
//		System.out.println(reviewDAO.getAllReviewsOfGood(strings.getId().toString()));
		for (Review review:
				reviewDAO.getAllReviewsOfGood(strings.getId().toString())) {
			System.out.println(review);
		}
	}

}
