package com.mycompany.app.models;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;

public class Review {
	ObjectId autor;
	ObjectId goods;
	Date publichingDate;
	String content;
	Float rate;
	List<String> photos;

	public ObjectId getAutor() {
		return autor;
	}

	public void setAutor(ObjectId autor) {
		this.autor = autor;
	}

	public ObjectId getGoods() {
		return goods;
	}

	public void setGoods(ObjectId goods) {
		this.goods = goods;
	}

	public Date getPublichingDate() {
		return publichingDate;
	}

	public void setPublichingDate(Date publichingDate) {
		this.publichingDate = publichingDate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Float getRate() {
		return rate;
	}

	public void setRate(Float rate) {
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
		return "Review [\n  autor=" + autor + ",\n  goods=" + goods + ",\n  publishingDate=" + publichingDate
				+ ",\n  content=" + content + ",\n rate=" + rate + "\n, photos=" + photos + "\n]";
	}
}
