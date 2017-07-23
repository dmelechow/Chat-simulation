package dmelechow.testtask;


import android.support.annotation.NonNull;

import java.util.ArrayList;

import dmelechow.testtask.event.PingEvent;
import dmelechow.testtask.interactor.ChatGeneratorInteractor;
import dmelechow.testtask.model.Chat;
import dmelechow.testtask.view.ChatRenderView;
import dmelechow.testtask.view.ChatState;

/**
 * Created by a1 on 7/23/17.
 */

public class ChatPresenter {

    private final ChatGeneratorInteractor interactor;
    private ArrayList<Chat> chats = new ArrayList<>();
    private final ChatRenderView view;

    public ChatPresenter(@NonNull ChatRenderView view,
                         @NonNull ChatGeneratorInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }


    public void initDefaultState() {
        chats = interactor.getChats();
        view.render(new ChatState.DefaultChatState(chats));
    }

    public void updateOneChat() {
        chats = interactor.updateRandomChat(chats);
        view.render(new ChatState.DefaultChatState(chats));
    }

    public void updateActionBar(PingEvent event) {
        view.render(new ChatState.ActionBarState(event.getStatusTitle()));
    }
}
