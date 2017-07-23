package dmelechow.testtask.event;

/**
 * Created by a1 on 7/23/17.
 */

public class PingEvent {
    private String statusTitle;

    public PingEvent(String statusTitle) {
        this.statusTitle = statusTitle;
    }

    public String getStatusTitle() {
        return statusTitle;
    }
}
