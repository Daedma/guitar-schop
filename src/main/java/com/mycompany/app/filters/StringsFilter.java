package com.mycompany.app.filters;

public class StringsFilter extends GoodFilter {
	private String stringGauge; // натяжение (обычно пишут что-то вроде "10-46")
	private String stringMaterial; // материал струн

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
				+ getSortBy() + ", getSortAsc()=" + getSortAsc() + ", getMinCost()=" + getMinCost() + ", getMaxCost()="
				+ getMaxCost() + ", getSearchQuery()=" + getSearchQuery() + ", getMinimalRate()=" + getMinimalRate()
				+ ", getInStock()=" + getInStock() + ", getType()=" + getType() + ", getCategories()=" + getCategories()
				+ "]";
	}

}
