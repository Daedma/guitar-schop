package com.mycompany.app.models;

import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;

import java.util.Date;
import java.util.List;

public class Order {
	@BsonId
	private ObjectId id;
	private String fullName;
	private List<ObjectId> goods;
	private Float cost;
	private Address address;
	private Date date;
	private String status;

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public List<ObjectId> getGoods() {
		return goods;
	}

	public void setGoods(List<ObjectId> goods) {
		this.goods = goods;
	}

	public Float getCost() {
		return cost;
	}

	public void setCost(Float cost) {
		this.cost = cost;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Order{" +
				"id=" + id +
				", fullName='" + fullName + '\'' +
				", goods=" + goods +
				", cost=" + cost +
				", address=" + address +
				", date=" + date +
				", status='" + status + '\'' +
				'}';
	}
}
