package dmelechow.testtask.view;

import java.util.ArrayList;

import dmelechow.testtask.model.Chat;

/**
 * Created by a1 on 7/23/17.
 */

public interface ChatState {


    final class ActionBarState implements ChatState {

        final String title;

        public ActionBarState(String title) {
            this.title = title;
        }

        public String getTitle() {
            return title;
        }
    }

    final class DefaultChatState implements ChatState {

        final ArrayList<Chat> chats;

        public DefaultChatState(ArrayList<Chat> chats) {
            this.chats = chats;
        }

        public ArrayList<Chat> getChats() {
            return chats;
        }
    }
}
