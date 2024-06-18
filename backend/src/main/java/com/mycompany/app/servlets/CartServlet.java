package com.mycompany.app.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections4.Bag;
import org.apache.commons.collections4.bag.HashBag;
import org.bson.types.ObjectId;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mycompany.app.dao.GoodDAO;
import com.mycompany.app.dao.UserDAO;
import com.mycompany.app.models.Good;
import com.mycompany.app.models.User;

import javax.servlet.annotation.WebServlet;

@WebServlet(name = "cart-servlet", urlPatterns = { "/api/cart" })
public class CartServlet extends BaseServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getRequestURI().endsWith("")) { // /api/cart
			addItemToCart(req, resp);
		} else {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getRequestURI().endsWith("")) { // /api/cart
			getItemsFromCart(req, resp);
		} else {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
	}

	private void addItemToCart(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		JsonObject newItem = JsonParser.parseString(getJsonFromRequest(req)).getAsJsonObject();
		String goodId = newItem.get("itemId").getAsString();
		int quantity = newItem.get("quantity").getAsInt();
		GoodDAO goodDAO = daoFactory.createGoodDAO();
		Good good = goodDAO.findById(goodId);

		if (quantity <= 0) {
			writeError(resp, HttpServletResponse.SC_BAD_REQUEST, "Invalid quantity");
			return;
		}

		if (good == null) {
			writeError(resp, HttpServletResponse.SC_BAD_REQUEST, "Invalid item id");
			return;
		}

		if (good.getRemaining() < quantity) {
			writeError(resp, HttpServletResponse.SC_BAD_REQUEST, "Not enough items in stock");
			return;
		}

		HttpSession session = req.getSession(false);
		if (session == null || session.getAttribute("id") == null) {
			writeError(resp, HttpServletResponse.SC_BAD_REQUEST, "User not logged in");
			return;
		}

		String userId = (String) session.getAttribute("id");
		UserDAO userDAO = daoFactory.createUserDAO();
		User user = userDAO.findById(userId);

		for (int i = 0; i != quantity; ++i) {
			user.getCart().add(good.getId());
		}
		userDAO.update(user);

		good.setRemaining(good.getRemaining() - quantity);
		goodDAO.update(good);

		newItem.addProperty("name", good.getName());
		newItem.addProperty("cost", good.getCost());

		writeJsonElement(resp, HttpServletResponse.SC_CREATED, newItem);
	}

	private void getItemsFromCart(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		HttpSession session = req.getSession(false);
		if (session == null || session.getAttribute("id") == null) {
			writeError(resp, HttpServletResponse.SC_BAD_REQUEST, "User not logged in");
			return;
		}

		String userId = (String) session.getAttribute("id");
		UserDAO userDAO = daoFactory.createUserDAO();
		User user = userDAO.findById(userId);

		JsonArray cart = new JsonArray();
		GoodDAO goodDAO = daoFactory.createGoodDAO();
		Bag<ObjectId> cartBag = new HashBag<ObjectId>(user.getCart());

		for (ObjectId goodId : cartBag.uniqueSet()) {
			Good good = goodDAO.findById(goodId.toString());
			JsonObject goodJsonObject = new JsonObject();
			goodJsonObject.addProperty("id", goodId.toHexString());
			goodJsonObject.addProperty("name", good.getName());
			goodJsonObject.addProperty("cost", good.getCost());
			goodJsonObject.addProperty("quantity", cartBag.getCount(goodId));
			cart.add(goodJsonObject);
		}
		writeJsonElement(resp, HttpServletResponse.SC_OK, cart);
	}
}
