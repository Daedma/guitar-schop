package com.mycompany.app.filters;

import org.bson.Document;
import org.bson.conversions.Bson;

public class GuitarFilter extends GoodFilter {
	private String guitarForm; // форм-фактор
	private String guitarFrets; // количество ладов

	@Override
	public Bson createMongoQuery(Document query) {
		super.createMongoQuery(query);
		if (guitarForm != null) {
			query.put("guitarForm", guitarForm);
		}
		if (guitarFrets != null) {
			query.put("guitarFrets", guitarFrets);
		}
		return query;
	}

	public String getGuitarForm() {
		return guitarForm;
	}

	public void setGuitarForm(String guitarForm) {
		this.guitarForm = guitarForm;
	}

	public String getGuitarFrets() {
		return guitarFrets;
	}

	public void setGuitarFrets(String guitarFrets) {
		this.guitarFrets = guitarFrets;
	}

	@Override
	public String toString() {
		return "GuitarFilter [guitarForm=" + guitarForm + ", guitarFrets=" + guitarFrets + ", getSortBy()="
				+ getSortBy() + ", getSortAsc()=" + getSortOrder() + ", getMinCost()=" + getMinCost()
				+ ", getMaxCost()="
				+ getMaxCost() + ", getSearchQuery()=" + getSearchQuery() + ", getMinimalRate()=" + getMinimalRate()
				+ ", getInStock()=" + getInStock() + ", getType()=" + getType() + ", getCategories()=" + getCategories()
				+ "]";
	}

}
