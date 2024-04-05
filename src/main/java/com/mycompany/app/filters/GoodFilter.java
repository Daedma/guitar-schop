package com.mycompany.app.filters;

import java.util.List;

public class GoodFilter {
	private String sortBy;
	private Boolean sortAsc;
	private Float minCost;
	private Float maxCost;
	private String searchQuery;
	private Float minimalRate;
	private Boolean inStock;
	private String type;
	private List<String> categories;

	@Override
	public String toString() {
		return "GoodFilter [sortBy=" + sortBy + ", sortAsc=" + sortAsc + ", minCost=" + minCost + ", maxCost=" + maxCost
				+ ", searchQuery=" + searchQuery + ", minimalRate=" + minimalRate + ", inStock=" + inStock + ", type="
				+ type + ", categories=" + categories + "]";
	}

	public String getSortBy() {
		return sortBy;
	}

	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}

	public Boolean getSortAsc() {
		return sortAsc;
	}

	public void setSortAsc(Boolean sortAsc) {
		this.sortAsc = sortAsc;
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
