package com.mycompany.app.filters;

import org.bson.Document;
import org.bson.conversions.Bson;

public class StringsFilter extends GoodFilter {
	private String stringGauge; // натяжение (обычно пишут что-то вроде "10-46")
	private String stringMaterial; // материал струн

	@Override
	public Bson createMongoQuery(Document query) {
		super.createMongoQuery(query);
		if (stringGauge != null) {
			query.put("stringGauge", stringGauge);
		}
		if (stringMaterial != null) {
			query.put("stringMaterial", stringMaterial);
		}
		return query;
	}

	public String getStringGauge() {
		return stringGauge;
	}

	public void setStringGauge(String stringGauge) {
		this.stringGauge = stringGauge;
	}

	public String getStringMaterial() {
		return stringMaterial;
	}

	public void setStringMaterial(String stringMaterial) {
		this.stringMaterial = stringMaterial;
	}

	@Override
	public String toString() {
		return "StringsFilter [stringGauge=" + stringGauge + ", stringMaterial=" + stringMaterial + ", getSortBy()="
				+ getSortBy() + ", getSortAsc()=" + getSortOrder() + ", getMinCost()=" + getMinCost()
				+ ", getMaxCost()="
				+ getMaxCost() + ", getSearchQuery()=" + getSearchQuery() + ", getMinimalRate()=" + getMinimalRate()
				+ ", getInStock()=" + getInStock() + ", getType()=" + getType() + ", getCategories()=" + getCategories()
				+ "]";
	}

}
