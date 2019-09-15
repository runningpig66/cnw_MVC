package com.example.cnw_mvc.model;

import java.util.List;

public class Repo {

    /**
     * author : commanderx16
     * name : x16-emulator
     * avatar : https://github.com/commanderx16.png
     * url : https://github.com/commanderx16/x16-emulator
     * description : Emulator for the Commander X16 8-bit computer
     * language : C
     * languageColor : #555555
     * stars : 192
     * forks : 14
     * currentPeriodStars : 48
     * builtBy : [{"username":"mist64","href":"https://github.com/mist64","avatar":"https://avatars1.githubusercontent.com/u/869384"},{"username":"autismuk","href":"https://github.com/autismuk","avatar":"https://avatars3.githubusercontent.com/u/1783032"},{"username":"sebastianvog","href":"https://github.com/sebastianvog","avatar":"https://avatars1.githubusercontent.com/u/26048030"},{"username":"Nullious","href":"https://github.com/Nullious","avatar":"https://avatars1.githubusercontent.com/u/30197659"},{"username":"rsbohn12","href":"https://github.com/rsbohn12","avatar":"https://avatars3.githubusercontent.com/u/3286766"}]
     */

    private String author;
    private String name;
    private String avatar;
    private String url;
    private String description;
    private String language;
    private String languageColor;
    private int stars;
    private int forks;
    private int currentPeriodStars;
    private List<BuiltByBean> builtBy;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLanguageColor() {
        return languageColor;
    }

    public void setLanguageColor(String languageColor) {
        this.languageColor = languageColor;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public int getForks() {
        return forks;
    }

    public void setForks(int forks) {
        this.forks = forks;
    }

    public int getCurrentPeriodStars() {
        return currentPeriodStars;
    }

    public void setCurrentPeriodStars(int currentPeriodStars) {
        this.currentPeriodStars = currentPeriodStars;
    }

    public List<BuiltByBean> getBuiltBy() {
        return builtBy;
    }

    public void setBuiltBy(List<BuiltByBean> builtBy) {
        this.builtBy = builtBy;
    }

    public static class BuiltByBean {
        /**
         * username : mist64
         * href : https://github.com/mist64
         * avatar : https://avatars1.githubusercontent.com/u/869384
         */

        private String username;
        private String href;
        private String avatar;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getHref() {
            return href;
        }

        public void setHref(String href) {
            this.href = href;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }
    }
}
