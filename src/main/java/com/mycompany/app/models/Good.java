package com.mycompany.app.models;

import java.io.File;
import java.util.List;

import org.bson.Document;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

public class Good {
    @BsonId
    private ObjectId id;
    @BsonProperty("name")
    private String name;
    @BsonProperty("description")
    private String description;
    @BsonProperty("cost")
    private Float cost;
    @BsonProperty("images")
    private List<File> images;
    @BsonProperty("remaining")
    private Integer remaining;
    @BsonProperty("rate")
    private Float rate;
    @BsonProperty("categories_id")
    private List<ObjectId> categories;
    @BsonProperty("type")
    private List<ObjectId> type;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getCost() {
        return cost;
    }

    public void setCost(Float cost) {
        this.cost = cost;
    }

    public List<File> getImages() {
        return images;
    }

    public void setImages(List<File> images) {
        this.images = images;
    }

    public Integer getRemaining() {
        return remaining;
    }

    public void setRemaining(Integer remaining) {
        this.remaining = remaining;
    }

    public Float getRate() {
        return rate;
    }

    public void setRate(Float rate) {
        this.rate = rate;
    }

    public List<ObjectId> getCategories() {
        return categories;
    }

    public void setCategories(List<ObjectId> categories) {
        this.categories = categories;
    }

    public List<ObjectId> getType() {
        return type;
    }

    public void setType(List<ObjectId> type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Good{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", cost=" + cost +
                ", images=" + images +
                ", remaining=" + remaining +
                ", rate=" + rate +
                ", categories=" + categories +
                ", type=" + type +
                '}';
    }

    public static class Guitar extends Good {
        @BsonProperty("form")
        String guitarForm;  // форм-фактор
        @BsonProperty("frets")
        String guitarFrets; // количество ладов

        private String getGuitarForm() {
            return guitarForm;
        }

        private void setGuitarForm(String guitarForm) {
            this.guitarForm = guitarForm;
        }

        private String getGuitarFrets() {
            return guitarFrets;
        }

        private void setGuitarFrets(String guitarFrets) {
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
                    ", type=" +getType() +
                    '}';
        }
    }

    public static class Strings extends Good {
        @BsonProperty("gauge")
        String stringGauge;  // натяжение (обычно пишут что-то вроде "10-46")
        @BsonProperty("material")
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
                    ", type=" +getType() +
                    '}';
        }
    }
}

