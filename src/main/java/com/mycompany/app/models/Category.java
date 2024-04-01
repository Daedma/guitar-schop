package com.mycompany.app.models;

import java.util.List;

import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;

public class Category {
	@BsonId
	private ObjectId id;
	private String name;
	private List<ObjectId> goods;

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ObjectId> getGoods() {
		return goods;
	}

	public void setGoods(List<ObjectId> goods) {
		this.goods = goods;
	}

	@Override
	public String toString() {
		return "Category [\n  name=" + name + ",\n  goods=" + goods + "\n]";
	}
}
