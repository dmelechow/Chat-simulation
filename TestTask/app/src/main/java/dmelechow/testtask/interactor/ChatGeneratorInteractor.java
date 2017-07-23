package dmelechow.testtask.interactor;

import android.content.Context;

import java.util.ArrayList;
import java.util.Random;

import dmelechow.testtask.R;
import dmelechow.testtask.model.Chat;
import dmelechow.testtask.model.Message;

/**
 * Created by a1 on 7/23/17.
 */

public class ChatGeneratorInteractor {

    Random random = new Random();
    private Context context;

    public ChatGeneratorInteractor(Context context) {
        this.context = context;
    }

    public ArrayList<Chat> getChats() {
        ArrayList<Chat> chats = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Message message = new Message(buildMessage(), random.nextBoolean(), buildDate());
            Chat chat = new Chat(buildName(), message, buildUrlIcon());
            chats.add(chat);
        }
        return chats;
    }

    private String buildMessage() {
        String[] message = context.getResources().getStringArray(R.array.array_message);
        return message[random.nextInt(5)];
    }

    private String buildDate() {
        String[] message = context.getResources().getStringArray(R.array.array_date);
        return message[random.nextInt(5)];
    }

    private String buildName() {
        String[] array = context.getResources().getStringArray(R.array.array_name);
        return array[random.nextInt(10)];
    }

    private String buildUrlIcon() {
        String[] array = context.getResources().getStringArray(R.array.array_url_icon);
        return array[random.nextInt(5)];
    }

    public ArrayList<Chat> updateRandomChat(ArrayList<Chat> chats) {
        int numberUpdate = random.nextInt(chats.size());
        int whatUpdate = random.nextInt(3);
        switch (whatUpdate) {
            // непрочитанность
            case 0: {
                chats.get(numberUpdate).getLastMessage().setUnread(!chats.get(numberUpdate).getLastMessage().isUnread());
            }
            break;

            // картинка
            case 1: {
                chats.get(numberUpdate).setIconChat(buildUrlIcon());
            }
            break;

            // сообщение
            default: {
                chats.get(numberUpdate).getLastMessage().setText(buildMessage());
            }
        }
        return chats;
    }
}
