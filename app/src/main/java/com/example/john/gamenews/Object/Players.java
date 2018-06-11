package com.example.john.gamenews.Object;

public class Players {

    private String avatar;
    private String _id;
    private String name;
    private String biografia;
    private String game;
    private int __v;

    public Players(String avatar, String _id, String name, String biografia, String game, int __v) {
        this.avatar = avatar;
        this._id = _id;
        this.name = name;
        this.biografia = biografia;
        this.game = game;
        this.__v = __v;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public int get__v() {
        return __v;
    }

    public void set__v(int __v) {
        this.__v = __v;
    }
}
