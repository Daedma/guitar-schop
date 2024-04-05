package com.mycompany.app.filters;

public class GuitarFilter extends GoodFilter {
	private String guitarForm; // форм-фактор
	private String guitarFrets; // количество ладов

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
				+ getSortBy() + ", getSortAsc()=" + getSortAsc() + ", getMinCost()=" + getMinCost() + ", getMaxCost()="
				+ getMaxCost() + ", getSearchQuery()=" + getSearchQuery() + ", getMinimalRate()=" + getMinimalRate()
				+ ", getInStock()=" + getInStock() + ", getType()=" + getType() + ", getCategories()=" + getCategories()
				+ "]";
	}

}
