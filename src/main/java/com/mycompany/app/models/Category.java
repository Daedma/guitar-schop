package com.mycompany.app.models;

import java.util.List;

import org.bson.types.ObjectId;

public class Category {
	private String name;
	private List<ObjectId> goods;

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
