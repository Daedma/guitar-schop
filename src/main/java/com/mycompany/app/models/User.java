package com.mycompany.app.models;

import java.util.List;

import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.*;

public class User {
	@BsonId
	private ObjectId id;
	private String login;
	private String password;
	private String role;
	private List<ObjectId> cart;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<ObjectId> getCart() {
		return cart;
	}

	public void setCart(List<ObjectId> cart) {
		this.cart = cart;
	}

	@Override
	public String toString() {
		return "User [\n  login=" + login + ",\n  password=" + password + ",\n  role=" + role + ",\n  cart=" + cart
				+ "\n]";
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}
}
