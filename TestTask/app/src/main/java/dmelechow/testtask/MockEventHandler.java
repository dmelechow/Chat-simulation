package dmelechow.testtask;

import org.greenrobot.eventbus.EventBus;

import java.util.concurrent.TimeUnit;

import dmelechow.testtask.event.PingEvent;
import dmelechow.testtask.event.UpdateChatEvent;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by a1 on 7/24/17.
 */

public class MockEventHandler {

    private final int PING_CHAT_DELAY = 20_000;
    private final int UPDATE_CHAT_INTERVAL = 3000;
    private final int PING_CHAT_INTERVAL = 1000;
    private final int NUMBER_PING = 60;

    private int numberPing = 0;
    private Subscription subscriptionUpdateChat;
    private Subscription subscriptionPing;
    private Subscription subscriptionTimer;

    public void onStop() {
        if (subscriptionUpdateChat != null) {
            subscriptionUpdateChat.unsubscribe();
        }
        if (subscriptionPing != null) {
            subscriptionPing.unsubscribe();
        }
        if (subscriptionTimer != null) {
            subscriptionTimer.unsubscribe();
        }
    }

    public void initHandlers() {
        initUpdateChat();
        initPingChat();
    }

    private void initUpdateChat() {
        subscriptionUpdateChat = Observable.interval(UPDATE_CHAT_INTERVAL, UPDATE_CHAT_INTERVAL, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Long>() {
                    public void call(Long aLong) {
                        EventBus.getDefault().post(new UpdateChatEvent());
                    }
                });
    }

    private void initPingChat() {
        subscriptionPing = Observable.interval(0, PING_CHAT_INTERVAL, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Long>() {
                    public void call(Long aLong) {
                        numberPing++;
                        if (numberPing > NUMBER_PING) {
                            numberPing = 0;
                            EventBus.getDefault().post(new PingEvent("Offline"));
                            initTimer();
                            subscriptionPing.unsubscribe();
                        } else {
                            EventBus.getDefault().post(new PingEvent("Online"));
                        }
                    }
                });

    }

    private void initTimer() {
        subscriptionTimer = Observable.timer(PING_CHAT_DELAY, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Long>() {
                    public void call(Long aLong) {
                        initPingChat();
                    }
                });
    }

}
