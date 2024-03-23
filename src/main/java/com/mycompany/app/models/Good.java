package com.mycompany.app.models;

import java.io.File;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;

public class Good {
    private String name;
    private String description;
    private Float cost;
    private List<File> images;
    private Integer remaining;
    private Float rate;
    private List<ObjectId> categories;



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
                '}';
    }

    public static class Guitar extends Good {
        String guitarForm;  // форм-фактор
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
                    '}';
        }
    }

    public static class Strings extends Good {
        String stringGauge;  // натяжение (обычно пишут что-то вроде "10-46")
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
}

