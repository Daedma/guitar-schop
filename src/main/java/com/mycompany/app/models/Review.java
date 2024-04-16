package com.mycompany.app.models;

import java.util.Date;
import java.util.List;

import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

public class Review {
	@BsonId
	private ObjectId id;
	@BsonProperty("author_id")
	private ObjectId author;
	@BsonProperty("goods_id")
	private ObjectId goods;
	//FIXME: @BsonProperty("publishing_date")
	private Date publishingDate;
	@BsonProperty("content")
	private String content;
	@BsonProperty("rate")
	private Double rate;
	@BsonProperty("photos")
	private List<String> photos;

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public ObjectId getAuthor() {
		return author;
	}

	public void setAuthor(ObjectId author) {
		this.author = author;
	}

	public ObjectId getGoods() {
		return goods;
	}

	public void setGoods(ObjectId goods) {
		this.goods = goods;
	}

	public Date getPublishingDate() {
		return publishingDate;
	}

	public void setPublishingDate(Date publishingDate) {
		this.publishingDate = publishingDate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public List<String> getPhotos() {
		return photos;
	}

	public void setPhotos(List<String> photos) {
		this.photos = photos;
	}

	@Override
	public String toString() {
		return "Review{" +
				"id=" + id +
				", author=" + author +
				", goods=" + goods +
				", publishingDate=" + publishingDate +
				", content='" + content + '\'' +
				", rate=" + rate +
				", photos=" + photos +
				'}';
	}
}
