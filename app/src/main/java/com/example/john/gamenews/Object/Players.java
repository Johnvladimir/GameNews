package com.example.john.gamenews.Object;

public class Players {

    private int _id;
    private String name;
    private String biografia;
    private String avatar;
    private String game;

    public Players(int _id, String name, String biografia, String avatar, String game) {
        this._id = _id;
        this.name = name;
        this.biografia = biografia;
        this.avatar = avatar;
        this.game = game;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }
}
