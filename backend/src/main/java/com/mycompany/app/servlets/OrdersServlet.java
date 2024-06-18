package com.mycompany.app.servlets;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections4.Bag;
import org.apache.commons.collections4.IterableUtils;
import org.apache.commons.collections4.bag.HashBag;
import org.bson.types.ObjectId;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mycompany.app.dao.GoodDAO;
import com.mycompany.app.dao.OrderDAO;
import com.mycompany.app.dao.UserDAO;
import com.mycompany.app.models.Good;
import com.mycompany.app.models.Order;
import com.mycompany.app.models.User;

@WebServlet(name = "orders-servlet", urlPatterns = { "/api/orders", "/api/orders/*" })
public class OrdersServlet extends BaseServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getRequestURI().endsWith("")) { // /api/orders
			createOrder(req, resp);
		} else {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getRequestURI().endsWith("")) { // /api/orders
			getOrders(req, resp);
		} else {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String pathInfo = req.getPathInfo();

		if (pathInfo != null && pathInfo.matches("^/[0-9a-fA-F]{24}$*")) { // /api/orders/{id}
			updateOrderStatus(req, resp);
		} else {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
	}

	private void createOrder(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		HttpSession session = req.getSession(false);
		if (session == null || session.getAttribute("id") == null) {
			writeError(resp, HttpServletResponse.SC_BAD_REQUEST, "User not logged in");
			return;
		}
		Order order = gson.fromJson(getJsonFromRequest(req), Order.class);
		order.setUserId((new ObjectId((String) session.getAttribute("id"))));
		order.setDate(new Date());
		order.setStatus("new");

		OrderDAO orderDAO = daoFactory.createOrderDAO();
		UserDAO userDAO = daoFactory.createUserDAO();
		User user = userDAO.findById(order.getUserId().toHexString());

		if (user == null) {
			writeError(resp, HttpServletResponse.SC_BAD_REQUEST, "Invalid user id");
			return;
		}

		order.setGoods(user.getCart());

		GoodDAO goodDAO = daoFactory.createGoodDAO();

		double actualCost = 0;
		Bag<ObjectId> goodsBag = new HashBag<ObjectId>(order.getGoods());
		for (ObjectId goodId : goodsBag.uniqueSet()) {
			Good good = goodDAO.findById(goodId.toHexString());
			if (good == null) {
				writeError(resp, HttpServletResponse.SC_BAD_REQUEST, "Invalid item id");
				return;
			}
			if (good.getRemaining() < goodsBag.getCount(goodId)) {
				writeError(resp, HttpServletResponse.SC_BAD_REQUEST, "Not enough items in stock");
				return;
			}
			actualCost += good.getCost() * goodsBag.getCount(goodId);
		}

		order.setCost(actualCost);
		order.setId(new ObjectId(orderDAO.save(order)));

		for (ObjectId goodId : goodsBag.uniqueSet()) {
			Good good = goodDAO.findById(goodId.toHexString());
			good.setRemaining(good.getRemaining() - goodsBag.getCount(goodId));
			goodDAO.update(good);
		}

		user.getCart().clear();
		userDAO.update(user);

		writeObject(resp, HttpServletResponse.SC_CREATED, order);
	}

	private void getOrders(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		HttpSession session = req.getSession(false);
		if (session == null || session.getAttribute("id") == null || session.getAttribute("role") == null
				|| !session.getAttribute("role").equals("admin")) {
			writeError(resp, HttpServletResponse.SC_FORBIDDEN, "Only administrators can perform this operation");
			return;
		}
		OrderDAO orderDAO = daoFactory.createOrderDAO();
		List<Order> orders = IterableUtils.toList(orderDAO.findAll());
		writeObject(resp, HttpServletResponse.SC_OK, orders);
	}

	private void updateOrderStatus(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String orderId = req.getPathInfo().substring(1);
		if (orderId == null || orderId.isEmpty()) {
			writeError(resp, HttpServletResponse.SC_BAD_REQUEST, "Invalid order id");
			return;
		}

		OrderDAO orderDAO = daoFactory.createOrderDAO();
		Order order = orderDAO.findById(orderId);
		if (order == null) {
			writeError(resp, HttpServletResponse.SC_BAD_REQUEST, "Invalid order id");
			return;
		}

		HttpSession session = req.getSession(false);
		if (session == null || session.getAttribute("id") == null || session.getAttribute("role") == null
				|| !session.getAttribute("role").equals("admin")) {
			writeError(resp, HttpServletResponse.SC_FORBIDDEN, "Only administrators can perform this operation");
			return;
		}

		JsonObject statusJson = JsonParser.parseString(getJsonFromRequest(req)).getAsJsonObject();
		String status = statusJson.get("status").getAsString();
		order.setStatus(status);
		orderDAO.update(order);

		writeObject(resp, HttpServletResponse.SC_OK, order);
	}
}
