package com.example.web_viewtask2.models;

public class FlagQuiz {
    private String capital;
    private int image;
    private String country;

    public FlagQuiz(String capital, int image, String country) {
        this.capital = capital;
        this.image = image;
        this.country = country;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public int getImage() {
        return image;
    }

    public void setUrl(String url) {
        this.image = image;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
