package com.mycompany.app.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bson.types.ObjectId;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.mycompany.app.dao.DAOFactory;
import com.mycompany.app.dao.mongo.MongoDAOFactory;
import com.mycompany.app.util.MongoDBConnection;

public class BaseServlet extends HttpServlet {
	protected Gson gson;

	protected DAOFactory daoFactory;

	@Override
	public void init() throws ServletException {
		MongoDBConnection connection = new MongoDBConnection();
		daoFactory = new MongoDAOFactory(connection.getDatabase());

		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(ObjectId.class, new TypeAdapter<ObjectId>() {
			@Override
			public void write(JsonWriter out, ObjectId value) throws IOException {
				if (value == null) {
					out.nullValue();
				} else {
					out.value(value.toHexString());
				}
			}

			@Override
			public ObjectId read(JsonReader in) throws IOException {
				if (in.peek() == JsonToken.NULL) {
					in.nextNull();
					return null;
				} else {
					return new ObjectId(in.nextString());
				}
			}
		});
		gson = gsonBuilder.create();

		super.init();
	}

	protected void writeObject(HttpServletResponse resp, int code, Object obj) throws IOException {
		resp.setStatus(code);
		PrintWriter out = resp.getWriter();
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		out.print(gson.toJson(obj));
		out.flush();
	}

	protected void writeJsonElement(HttpServletResponse resp, int code, JsonElement obj) throws IOException {
		resp.setStatus(code);
		PrintWriter out = resp.getWriter();
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		out.print(obj.toString());
		out.flush();
	}

	protected void writeError(HttpServletResponse resp, int code, String msg) throws IOException {
		resp.setStatus(code);
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		resp.getWriter().println(gson.toJson(gson.toJsonTree(String.format("{error: \"%s\"}", msg))));
		return;
	}

	protected String getJsonFromRequest(HttpServletRequest req) throws IOException {
		Scanner scanner = new Scanner(req.getInputStream(), "UTF-8");
		String jsonData = scanner.useDelimiter("\\A").next();
		scanner.close();
		return jsonData;
	}
}
