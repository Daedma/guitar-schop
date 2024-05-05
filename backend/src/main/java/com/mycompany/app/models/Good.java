package com.mycompany.app.models;

import java.io.File;
import java.util.List;

import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

public class Good {
	@BsonId
	private ObjectId id;
	@BsonProperty("name")
	private String name;
	@BsonProperty("description")
	private String description;
	@BsonProperty("cost")
	private Double cost;
	@BsonProperty("images")
	private List<File> images;
	@BsonProperty("remaining")
	private Integer remaining;
	@BsonProperty("rate")
	private Double rate;
	@BsonProperty("categories")
	private List<ObjectId> categories;
	@BsonProperty("type")
	private String type;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public List<File> getImages() {
		return images;
	}

	public void setImages(List<File> images) {
		this.images = images;
	}

	public Integer getRemaining() {
		return remaining;
	}

	public void setRemaining(Integer remaining) {
		this.remaining = remaining;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public List<ObjectId> getCategories() {
		return categories;
	}

	public void setCategories(List<ObjectId> categories) {
		this.categories = categories;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Good{" +
				"id=" + id +
				", name='" + name + '\'' +
				", description='" + description + '\'' +
				", cost=" + cost +
				", images=" + images +
				", remaining=" + remaining +
				", rate=" + rate +
				", categories=" + categories +
				", type='" + type + '\'' +
				'}';
	}
}
