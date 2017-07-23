package dmelechow.testtask.model;

import java.util.Date;


/**
 *
 * Created by a1 on 7/23/17.
 */

public class Message {

    private String text;

    private boolean isUnread;

    private String date;

    public Message(String text, boolean isUnread, String dateLastMessage) {
        this.text = text;
        this.isUnread = isUnread;
        this.date = dateLastMessage;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isUnread() {
        return isUnread;
    }

    public void setUnread(boolean unread) {
        isUnread = unread;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String dateLastMessage) {
        this.date = dateLastMessage;
    }
}
