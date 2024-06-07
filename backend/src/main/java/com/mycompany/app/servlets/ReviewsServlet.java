package com.mycompany.app.servlets;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections4.IterableUtils;
import org.bson.types.ObjectId;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.mycompany.app.dao.GoodDAO;
import com.mycompany.app.dao.ReviewDAO;
import com.mycompany.app.dao.UserDAO;
import com.mycompany.app.models.Good;
import com.mycompany.app.models.Review;
import com.mycompany.app.models.User;

@WebServlet(name = "reviews-servlet", urlPatterns = { "/api/reviews/*", "/api/reviews" })
public class ReviewsServlet extends BaseServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String goodsId = req.getParameter("goods_id");
		if (goodsId == null || goodsId.isEmpty()) {
			writeError(resp, HttpServletResponse.SC_BAD_REQUEST, "Invalid item id");
			return;
		}

		ReviewDAO reviewDAO = daoFactory.createReviewDAO();
		List<Review> reviews = IterableUtils.toList(reviewDAO.getAllReviewsOfGood(goodsId));
		UserDAO userDAO = daoFactory.createUserDAO();
		JsonArray reviewsJsonArray = new JsonArray();
		for (Review review : reviews) {
			JsonObject reviewJsonObject = new JsonObject();
			reviewJsonObject.addProperty("id", review.getId().toHexString());

			User user = userDAO.findById(review.getAuthor().toHexString());
			JsonObject authorJsonObject = new JsonObject();
			authorJsonObject.addProperty("id", user.getId().toHexString());
			authorJsonObject.addProperty("name", user.getLogin());
			reviewJsonObject.add("author", authorJsonObject);

			reviewJsonObject.addProperty("goods", review.getGoods().toHexString());
			reviewJsonObject.addProperty("publishingDate", review.getPublishingDate().toString());
			reviewJsonObject.addProperty("content", review.getContent());
			reviewJsonObject.addProperty("rate", review.getRate());
			reviewsJsonArray.add(reviewJsonObject);
		}
		writeJsonElement(resp, HttpServletResponse.SC_OK, reviewsJsonArray);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Review review = gson.fromJson(getJsonFromRequest(req), Review.class);
		review.setPublishingDate(new Date());
		if (review.getAuthor() == null || review.getGoods() == null || review.getContent() == null
				|| review.getRate() == null || review.getRate() < 1 || review.getRate() > 5) {
			writeError(resp, HttpServletResponse.SC_BAD_REQUEST,
					"Invalid item id or missing required field in request body");
			return;
		}

		ReviewDAO reviewDAO = daoFactory.createReviewDAO();
		UserDAO userDAO = daoFactory.createUserDAO();
		GoodDAO goodDAO = daoFactory.createGoodDAO();
		User author = userDAO.findById(review.getAuthor().toHexString());
		Good goods = goodDAO.findById(review.getGoods().toHexString());

		if (author == null || goods == null) {
			writeError(resp, HttpServletResponse.SC_BAD_REQUEST,
					"Invalid item id or missing required field in request body");
			return;
		}

		review.setId(new ObjectId(reviewDAO.save(review)));

		goods.setRate(reviewDAO.getRateOfGood(goods.getId().toHexString()));
		goodDAO.update(goods);

		JsonObject reviewJsonObject = new JsonObject();
		reviewJsonObject.addProperty("id", review.getId().toHexString());

		JsonObject authorJsonObject = new JsonObject();
		authorJsonObject.addProperty("id", review.getAuthor().toHexString());
		authorJsonObject.addProperty("name", author.getLogin());
		reviewJsonObject.add("author", authorJsonObject);

		reviewJsonObject.addProperty("goods", review.getGoods().toHexString());
		reviewJsonObject.addProperty("publishingDate", review.getPublishingDate().toString());

		reviewJsonObject.addProperty("content", review.getContent());
		reviewJsonObject.addProperty("rate", review.getRate());

		writeJsonElement(resp, HttpServletResponse.SC_CREATED, reviewJsonObject);
	}
}
