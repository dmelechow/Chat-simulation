package dmelechow.testtask.injection;

import dagger.Module;
import dagger.Provides;
import dmelechow.testtask.ChatPresenter;
import dmelechow.testtask.interactor.ChatGeneratorInteractor;
import dmelechow.testtask.view.ChatRenderView;

/**
 * Created by a1 on 7/23/17.
 */

@Module
public class ChatPresenterModule {

    final ChatRenderView view;

    public ChatPresenterModule(ChatRenderView view) {
        this.view = view;
    }

    @Provides
    ChatPresenter provideChatPresenter(ChatGeneratorInteractor interactor){
        return new ChatPresenter(view, interactor);
    }
}
