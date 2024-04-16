package com.mycompany.app.models;

import java.util.List;

import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.*;

public class User {
	@BsonId
	private ObjectId id;
	@BsonProperty("login")
	private String login;
	@BsonProperty("password")
	private String password;
	@BsonProperty("role")
	private String role;
	@BsonProperty("cart")
	private List<ObjectId> cart;

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

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
		return "User{" +
				"id=" + id +
				", login='" + login + '\'' +
				", password='" + password + '\'' +
				", role='" + role + '\'' +
				", cart=" + cart +
				'}';
	}
}
