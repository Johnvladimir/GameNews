package com.example.john.gamenews.Object;

public class News {

    private String _id;
    private String title;
    private String body;
    private String game;
    private String createdDate;
    private String coverImage;
    private String description;
    private int __v;

    public News(String _id, String title, String body, String game, String coverImage, String description, String createdDate, int __v) {
        this._id = _id;
        this.title = title;
        this.body = body;
        this.game = game;
        this.coverImage = coverImage;
        this.description = description;
        this.createdDate = createdDate;
        this.__v = __v;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int get__v() {
        return __v;
    }

    public void set__v(int __v) {
        this.__v = __v;
    }
}