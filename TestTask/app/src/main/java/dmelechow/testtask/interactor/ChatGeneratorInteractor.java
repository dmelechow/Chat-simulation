package dmelechow.testtask.interactor;

import java.util.ArrayList;
import java.util.Random;

import dmelechow.testtask.model.Chat;
import dmelechow.testtask.model.Message;

/**
 * Created by a1 on 7/23/17.
 */

public class ChatGeneratorInteractor {

    Random random = new Random();

    public ArrayList<Chat> getChats() {
        ArrayList<Chat> chats = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            Message message = new Message(buildMessage(), random.nextBoolean(), buildDate());
            Chat chat = new Chat(buildName(), message, buildUrlIcon());
            chats.add(chat);
        }
        return chats;
    }

    private String buildMessage() {
        int numberMessage = random.nextInt(5);
        switch (numberMessage) {
            case 0: {
                return "Привет, как дела? бла бла бла бла бла бла бла ";
            }
            case 1: {
                return "Сегодня поеду домой";
            }

            case 2: {
                return "Как твоё настроение?";
            }

            case 3: {
                return "Крибли крабли бум";
            }

            default: {
                return "Я не приду";
            }
        }
    }

    private String buildDate() {
        int numberDate = random.nextInt(5);
        switch (numberDate) {
            case 0: {
                return "Thu";
            }
            case 1: {
                return "21.03.2017";
            }

            case 2: {
                return "Today";
            }

            case 3: {
                return "Sun";
            }

            default: {
                return "22.05.2017";
            }
        }
    }

    private String buildName() {
        int numberNameChat = random.nextInt(5);
        switch (numberNameChat) {
            case 0: {
                return "Анастасия М.";
            }
            case 1: {
                return "Олег Вульф";
            }

            case 2: {
                return "Мама";
            }

            case 3: {
                return "Брат";
            }

            default: {
                return "Ремонт Холодильников";
            }
        }
    }

    private String buildUrlIcon() {
        int iconAvaChat = random.nextInt(5);
        switch (iconAvaChat) {
            case 0: {
                return "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRx1qtutkE5iDvWk-8uWLAnGGXmw69r3xLfZQKaNdttnK80Ia8K";
            }
            case 1: {
                return "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTWjcHiGu7ZXoGVPjMWSylOFB_rxJD8vbttM-HtCvHYvtpKoRUC-Q";
            }

            case 2: {
                return "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT6qgKbiRdQ7S_-aryW1DAG8s-h_WVSdEL9WmQX-fxDIeSW8xE6";
            }

            case 3: {
                return "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSxsfZwBEeJsMhAVq4sQRr_33XsOx8qShqAvaWLYVJzKxvJa5m2yQ";
            }

            default: {
                return "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQyE294JmZeFTi99vy2n3JbMq-_KpRFDmP6iT1DifuAGsU5YT3t";
            }
        }
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
