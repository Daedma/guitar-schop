package com.mycompany.app.models;

import org.bson.codecs.pojo.annotations.BsonDiscriminator;

@BsonDiscriminator(value = "strings", key = "type")
public class Strings extends Good {
	String stringGauge; // натяжение (обычно пишут что-то вроде "10-46")
	String stringMaterial; // материал струн

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
		return "Strings{" +
				"stringGauge='" + stringGauge + '\'' +
				", stringMaterial='" + stringMaterial + '\'' +
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