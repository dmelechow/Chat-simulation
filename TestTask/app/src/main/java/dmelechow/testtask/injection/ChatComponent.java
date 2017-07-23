package dmelechow.testtask.injection;

import dagger.Component;
import dmelechow.testtask.ChatActivity;

/**
 * Created by a1 on 7/23/17.
 */

@Component(modules = {ChatPresenterModule.class, ChatAdapterModule.class, ChatGeneratorInteractorModule.class, MockEventHandlerModule.class})
public interface ChatComponent {

    void inject(ChatActivity activity);

}
