package com.mycompany.app.models;

import com.mycompany.app.models.Good;

public class Guitar extends Good {
    private String guitarForm;  // форм-фактор
    private String guitarFrets; // количество ладов

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