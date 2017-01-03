package cnb.express.beans;

/**
 * Created by tom on 17-1-3.
 */
public class GameBean {
    private String gameName;
    private int status;
    private long rollTimes;

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

    public long getRollTimes() {
        return rollTimes;
    }

    public void setRollTimes(long rollTimes) {
        this.rollTimes = rollTimes;
    }
}
