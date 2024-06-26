package com.mycompany.app.servlets;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.JsonObject;
import com.mycompany.app.dao.UserDAO;
import com.mycompany.app.models.User;

@WebServlet(name = "users-servlet", urlPatterns = { "/api/users/register", "/api/users/login", "/api/users/logout" })
public class UsersServlet extends BaseServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();
		if (uri.endsWith("/register")) {
			register(req, resp);
		} else if (uri.endsWith("/login")) {
			login(req, resp);
		} else if (uri.endsWith("/logout")) {
			logout(req, resp);
		} else {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
	}

	private void logout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		HttpSession session = req.getSession(false);
		if (session == null || session.getAttribute("id") == null) {
			writeError(resp, HttpServletResponse.SC_UNAUTHORIZED, "Invalid session");
			return;
		}
		session.invalidate();
		writeJsonElement(resp, HttpServletResponse.SC_OK, new JsonObject());
	}

	private void login(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		User enteredData = gson.fromJson(getJsonFromRequest(req), User.class);

		UserDAO userDAO = daoFactory.createUserDAO();

		User user = userDAO.findUserByLogin(enteredData.getLogin());
		if (user == null || !user.getPassword().equals(enteredData.getPassword())) {
			writeError(resp, HttpServletResponse.SC_UNAUTHORIZED, "Invalid login or password");
			return;
		}

		HttpSession session = req.getSession(true);
		session.setAttribute("id", user.getId().toHexString());
		session.setAttribute("login", user.getLogin());
		session.setAttribute("role", user.getRole());
		writeObject(resp, HttpServletResponse.SC_OK, user);
	}

	private void register(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		User newUser = gson.fromJson(getJsonFromRequest(req), User.class);

		if (newUser.getLogin().isEmpty() || newUser.getPassword().isEmpty()) {
			writeError(resp, HttpServletResponse.SC_BAD_REQUEST, "Login and password are required");
			return;
		}

		if (newUser.getPassword().length() < 8) {
			writeError(resp, HttpServletResponse.SC_BAD_REQUEST, "Password must be at least 8 characters long");
			return;
		}

		UserDAO userDAO = daoFactory.createUserDAO();

		if (userDAO.findUserByLogin(newUser.getLogin()) != null) {
			writeError(resp, HttpServletResponse.SC_CONFLICT, "Login is already taken");
			return;
		}

		newUser.setCart(new LinkedList<>());
		newUser.setRole("customer");

		String id = userDAO.save(newUser);
		newUser = userDAO.findById(id);

		writeObject(resp, HttpServletResponse.SC_CREATED, newUser);
	}
}
