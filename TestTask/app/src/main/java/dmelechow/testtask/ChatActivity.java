package dmelechow.testtask;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dmelechow.testtask.event.UpdateChatEvent;
import dmelechow.testtask.injection.ChatGeneratorInteractorModule;
import dmelechow.testtask.injection.ChatPresenterModule;
import dmelechow.testtask.injection.DaggerChatComponent;
import dmelechow.testtask.model.Chat;
import dmelechow.testtask.view.ChatRenderView;
import dmelechow.testtask.view.ChatState;
import dmelechow.testtask.event.PingEvent;
import dmelechow.testtask.view.adapter.ChatAdapter;

public class ChatActivity extends AppCompatActivity implements ChatRenderView {

    @BindView(R.id.action_bar_subtitle)
    TextView statusSubtitle;

    @BindView(R.id.recycler)
    RecyclerView recycler;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Inject
    ChatPresenter presenter;

    @Inject
    ChatAdapter adapter;

    @Inject
    MockEventHandler mockEventHandler;

    @Inject
    LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initViews();
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
        mockEventHandler.initHandlers();
    }

    @Override
    public void onStop() {
        super.onStop();
        mockEventHandler.onStop();
        EventBus.getDefault().unregister(this);
    }

    private void initViews() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        DaggerChatComponent.builder()
                .chatGeneratorInteractorModule(new ChatGeneratorInteractorModule(this))
                .chatPresenterModule(new ChatPresenterModule(this))
                .build()
                .inject(this);


        recycler.setLayoutManager(layoutManager);
        recycler.setAdapter(adapter);
        presenter.initDefaultState();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onUpdateChatEvent(UpdateChatEvent event) {
        presenter.updateOneChat();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onPingEvent(PingEvent event) {
        presenter.updateActionBar(event);
    }

    @Override
    public void render(ChatState state) {
        if (state instanceof ChatState.DefaultChatState) {
            renderDefaultChatState(((ChatState.DefaultChatState) state).getChats());
        } else if (state instanceof ChatState.ActionBarState) {
            renderActionBarState(((ChatState.ActionBarState) state).getTitle());
        }
    }

    private void renderDefaultChatState(ArrayList<Chat> chats) {
        adapter.updateChat(chats);
    }

    private void renderActionBarState(String title) {
        statusSubtitle.setText(title);
    }

}
