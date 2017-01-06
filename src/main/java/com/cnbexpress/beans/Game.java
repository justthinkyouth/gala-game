package com.cnbexpress.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by tom on 17-1-3.
 */
public class Game {
    private String gameName;
    /* 0、1.开始、 2结束**/
    private int status;
    private Long rollTimes;
    private Long gameTimes;
    private Date startTime;
    private Avatar winAvatar;
    private List<Avatar> shortlist = new ArrayList<Avatar>();

    public List<Avatar> getShortlist() {
        return shortlist;
    }

    public void setShortlist(List<Avatar> shortlist) {
        this.shortlist = shortlist;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Long getRollTimes() {
        return rollTimes;
    }

    public void setRollTimes(Long rollTimes) {
        this.rollTimes = rollTimes;
    }

    public Long getGameTimes() {
        return gameTimes;
    }

    public void setGameTimes(Long gameTimes) {
        this.gameTimes = gameTimes;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Avatar getWinAvatar() {
        return winAvatar;
    }

    public void setWinAvatar(Avatar winAvatar) {
        this.winAvatar = winAvatar;
    }
}
