package com.mycompany.app.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.mycompany.app.dao.DAOFactory;
import com.mycompany.app.dao.mongo.MongoDAOFactory;
import com.mycompany.app.util.MongoDBConnection;

public class BaseServlet extends HttpServlet {
	protected static final Gson GSON = new Gson();

	protected DAOFactory daoFactory;

	@Override
	public void init() throws ServletException {
		MongoDBConnection connection = new MongoDBConnection();
		daoFactory = new MongoDAOFactory(connection.getDatabase());
		super.init();
	}

	protected void writeObject(HttpServletResponse resp, int code, Object obj) throws IOException {
		resp.setStatus(code);
		PrintWriter out = resp.getWriter();
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		out.print(GSON.toJson(obj));
		out.flush();
	}

	protected void writeError(HttpServletResponse resp, int code, String msg) throws IOException {
		resp.setStatus(code);
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		resp.getWriter().println(GSON.toJson(GSON.toJsonTree(String.format("{error: \"%s\"}", msg))));
		return;
	}

	protected String getJsonFromRequest(HttpServletRequest req) throws IOException {
		Scanner scanner = new Scanner(req.getInputStream(), "UTF-8");
		String jsonData = scanner.useDelimiter("\\A").next();
		scanner.close();
		return jsonData;
	}
}
