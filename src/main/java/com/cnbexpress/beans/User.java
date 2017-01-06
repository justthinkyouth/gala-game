package com.cnbexpress.beans;

/**
 * Created by tom on 17-1-3.
 */
public class User {
    private Game game;
    private String openId;
    private String nickName;
    private String avatarUrl;
    private long times;
    private long num;

    public User(String openId, String nickName, String avatarUrl) {
        this.openId = openId;
        this.nickName = nickName;
        this.avatarUrl = avatarUrl;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public long getTimes() {
        return times;
    }

    public void setTimes(long times) {
        this.times = times;
    }

    public long getNum() {
        return num;
    }

    public void setNum(long num) {
        this.num = num;
    }
}
