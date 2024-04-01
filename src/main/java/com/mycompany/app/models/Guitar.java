package com.mycompany.app.models;

import org.bson.codecs.pojo.annotations.BsonDiscriminator;

@BsonDiscriminator(value = "guitar", key = "type")
public class Guitar extends Good {
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
		return "Guitar{" +
				"guitarForm='" + guitarForm + '\'' +
				", guitarFrets='" + guitarFrets + '\'' +
				", name='" + getName() + '\'' +
				", description='" + getDescription() + '\'' +
				", cost=" + getCost() +
				", images=" + getImages() +
				", remaining=" + getRemaining() +
				", rate=" + getRate() +
				", categories=" + getCategories() +
				'}';
	}
}