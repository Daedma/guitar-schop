package com.mycompany.app.filters;

import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;

public class GoodFilter {
	private String sortBy;
	private Integer sortOrder = 1;
	private Float minCost;
	private Float maxCost;
	private String searchQuery;
	private Float minimalRate;
	private Boolean inStock;
	private String type;
	private List<String> categories;

	public Bson createMongoQuery(Document query) {
		if (getSortBy() != null && getSortOrder() != null) {
			query.put("$sort", new Document(getSortBy(), getSortOrder()));
		}

		if (getMinCost() != null) {
			query.put("cost", new Document("$gte", getMinCost()));
		}

		if (getMaxCost() != null) {
			query.put("cost", new Document("$lte", getMinCost()));
		}
		// db.stores.createIndex( { name: "text", description: "text" } )
		if (getSearchQuery() != null) {
			query.put("$text", new Document("$search", getSearchQuery()));
		}

		if (getMinimalRate() != null) {
			query.put("rate", new Document("$gte", getMinimalRate()));
		}

		if (getType() != null) {
			query.put("type", getType());
		}

		if (getInStock() != null) {
			query.put("remaining", new Document("gte", 0));
		}

		if (getCategories() != null) {
			query.put("categories", new Document("$in", getCategories()));
		}

		return query;
	}

	@Override
	public String toString() {
		return "GoodFilter [sortBy=" + sortBy + ", sortAsc=" + sortOrder + ", minCost=" + minCost + ", maxCost="
				+ maxCost
				+ ", searchQuery=" + searchQuery + ", minimalRate=" + minimalRate + ", inStock=" + inStock + ", type="
				+ type + ", categories=" + categories + "]";
	}

	public String getSortBy() {
		return sortBy;
	}

	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}

	public Integer getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(Integer sortAsc) {
		this.sortOrder = sortAsc;
	}

	public Float getMinCost() {
		return minCost;
	}

	public void setMinCost(Float minCost) {
		this.minCost = minCost;
	}

	public Float getMaxCost() {
		return maxCost;
	}

	public void setMaxCost(Float maxCost) {
		this.maxCost = maxCost;
	}

	public String getSearchQuery() {
		return searchQuery;
	}

	public void setSearchQuery(String searchQuery) {
		this.searchQuery = searchQuery;
	}

	public Float getMinimalRate() {
		return minimalRate;
	}

	public void setMinimalRate(Float minimalRate) {
		this.minimalRate = minimalRate;
	}

	public Boolean getInStock() {
		return inStock;
	}

	public void setInStock(Boolean inStock) {
		this.inStock = inStock;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<String> getCategories() {
		return categories;
	}

	public void setCategories(List<String> categories) {
		this.categories = categories;
	}

}
