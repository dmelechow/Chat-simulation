package dmelechow.testtask.model;



/**
 * Created by a1 on 7/23/17.
 */

public class Chat {


    private String name;

    private String iconChat;

    private Message lastMessage;

    public Chat(String name, Message lastMessage, String iconChat) {
        this.name = name;
        this.lastMessage = lastMessage;
        this.iconChat = iconChat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIconChat() {
        return iconChat;
    }

    public void setIconChat(String iconChat) {
        this.iconChat = iconChat;
    }

    public Message getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(Message lastMessage) {
        this.lastMessage = lastMessage;
    }
}
